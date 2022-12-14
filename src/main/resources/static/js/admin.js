// Call the dataTables jQuery plugin
/**
 * Esta será la lógica de la vista que tendrá el administrador
 *
 */
$(document).ready(function () {
    alert("Pestaña administrador")
    cargarUsuarios();
    actualizarEmailUsuario();
    $('#usuarios').DataTable();
});

function actualizarEmailUsuario() {
    document.getElementById("email-usuario").outerHTML = localStorage.username;
}

async function me() {
    const request = await fetch('usuarios/me', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            "Authorization": "Bearer" + localStorage.token
        },
    });
    const usuarios = await request.json();
     let nombre = usuarios.name;

}

/**
 * Tabla que contiene todos los usuarios de la aplicación
 * @returns {Promise<void>}
 */
async function cargarUsuarios() {
    const request = await fetch('usuarios/getAll', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            "Authorization": "Bearer" + localStorage.token
        },
    });
    const usuarios = await request.json();
    localStorage.token = usuarios;
    let listadoHtml = '';

    for (let usuario of usuarios) {
        let botonEliminar = '<a href="#" onclick="eliminarUsuario(' + usuario.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
        let usuarioHtml =
            '<tr><td>' + usuario.id +
            '</td><td>' + usuario.fullName +
            '</td><td>' + usuario.username +
            '</td><td>' + usuario.email +
            '</td><td>' + usuario.dni +
            '</td><td>' + usuario.roles +
            '</td><td>' + usuario.entryDate +
            '</td><td>' + botonEliminar + '</td></tr>'
        listadoHtml += usuarioHtml;
    }
    document.querySelector('#usuarios tbody').outerHTML = listadoHtml
}

/**
 * Lógica que tendrá el botón de eliminar un usuario
 * @param id
 * @returns {Promise<void>}
 */
async function eliminarUsuario(id) {
    if (!confirm('¿Desea eliminar el usuario con id ?' + id)) {
        return;
    }
    const request = await fetch('usuarios/' + id, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
    });
    location.reload()
}

/**
 * Método que busca un usuario por su nombre de usuario, la barra de search
 * que se encuentra en la vista
 * @param username
 * @returns {Promise<void>}
 */
async function buscarUsuarioNombre(username) {
    let dates = {};
    dates.username = document.getElementById("barra").value;
    username = dates.username;

    console.log(username);
    const request = await fetch('usuarios/user/' + username, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
    });
    const usuario = await request.json();
    console.log(usuario)
    let botonEliminar = '<a href="#" onclick="eliminarUsuario(' + usuario.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

    let listadoHtml = '';
        let usuarioHtml =
            '<tr><td>' + usuario.id +
            '</td><td>' + usuario.fullName +
            '</td><td>' + usuario.username +
            '</td><td>' + usuario.email +
            '</td><td>' + usuario.dni +
            '</td><td>' + usuario.roles +
            '</td><td>' + usuario.entryDate +
            '</td><td>' + botonEliminar + '</td></tr>'

        listadoHtml += usuarioHtml;

    document.querySelector('#usuarios tbody').outerHTML = listadoHtml

    // location.reload()
}


