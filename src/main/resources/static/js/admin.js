// Call the dataTables jQuery plugin
$(document).ready(function () {
    alert("Pestaña administrador")
    cargarStudents();

    $('#students').DataTable();
})

$(document).ready(function () {
    cargarTeachers();

    $('#teachers').DataTable();
});
$(document).ready(function () {
    cargarUsuarios();
    $('#usuarios').DataTable();
    actualizarEmailUsuario();
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
async function eliminarUsuario(id) {
    if (!confirm('¿Desea eliminar el usuario?')) {
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



