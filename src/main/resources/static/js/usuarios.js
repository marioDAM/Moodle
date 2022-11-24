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
        let botonAñadir = '  <a href="#" onclick="añadirUsuario(' + usuario.id + ')" class="btn btn-success btn-circle btn-sm">  <i class="fas fa-check"></i></a>';
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

//Método que muestra los alumnos añadidos en la base de datos del proyecto
async function cargarStudents() {
    const request = await fetch('students', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
    });
    const usuarios = await request.json();


    let listadoHtml = '';

    for (let usuario of usuarios) {
        let botonEliminar = '<a href="#" onclick="eliminarUsuario(' + usuario.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
        let botonAñadir = '  <a href="#" onclick="añadirUsuario(' + usuario.id + ')" class="btn btn-success btn-circle btn-sm">  <i class="fas fa-check"></i></a>';
        let usuarioHtml =
            '<tr><td>' + usuario.id +
            '</td><td>' + usuario.name + ' ' + usuario.surname +
            '</td><td>' + usuario.username +
            '</td><td>' + usuario.email +
            '</td><td>' + usuario.dni +
            '</td><td>' + usuario.subjects +
            '</td><td>' + botonEliminar + '</td></tr>'
        listadoHtml += usuarioHtml;
    }
    document.querySelector('#students tbody').outerHTML = listadoHtml
}

async function eliminarUsuario(id) {
    if (!confirm('¿Desea eliminar el usuario?')) {
        return;
    }
    const request = await fetch('student/' + id, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
    });
    location.reload()
}

async function añadirUsuario(id) {
    if (!confirm('¿Desea añadir el usuario?')) {
        return;
    }
    const request = await fetch('addStudent/' + id, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
    });
    location.reload()
}

//Método que muestra los profesores añadidos en la base de datos del proyecto
async function cargarTeachers() {
    const request = await fetch('teachers', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
    });
    const usuarios = await request.json();
    let listadoHtml = '';
    for (let usuario of usuarios) {
        let usuarioHtml =
            '<tr><td>' + usuario.id +
            '</td><td>' + usuario.name +
            '</td><td>' + usuario.surname +
            '</td><td>' + usuario.username +
            '</td><td>' + usuario.email +
            '</td><td>' + usuario.course +
            '</td><td>' + usuario.entryDate + '</td><td><a href="#" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a></td></tr>'
        listadoHtml += usuarioHtml;
    }
    document.querySelector('#teachers tbody').outerHTML = listadoHtml
}

