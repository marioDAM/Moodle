async function buscarUsuarioNombre(username) {
    let dates = {};
    dates.username = document.getElementById("barra").value;
    username = dates.username;

    if (!confirm('¿Desea buscar el usuario? ' + username)) {
        return;
    }
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
    // alert('h')
    let botonEliminar = '<a href="#" onclick="eliminarUsuario(' + usuario.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

    let listadoHtml = '';
    let usuarioHtml =
        '<tr><td>' + usuario.id +
        '</td><td>' + usuario.fullName +
        '</td><td>' + usuario.dni +
        '</td><td>' + usuario.subjects +
        '</td><td>' + usuario.tareas +
        '</td><td>' + botonEliminar + '</td></tr>'

    listadoHtml += usuarioHtml;

    document.querySelector('#usuarios tbody').outerHTML = listadoHtml

    // location.reload()
}