// Call the dataTables jQuery plugin
$(document).ready(function() {
alert("Pestaña administrador")
    cargarStudents();

  $('#usuarios').DataTable();
})

$(document).ready(function() {
    cargarTeachers();

  $('#teachers').DataTable();
});

  async function cargarStudents(){
   const request = await fetch('students', {
     method: 'GET',
     headers: {
       'Accept': 'application/json',
       'Content-Type': 'application/json'
     },
   });
   const usuarios = await request.json();


   let listadoHtml = '';

      for (let usuario of usuarios){
         let botonEliminar = '<a href="#" onclick="eliminarUsuario('+usuario.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

      let usuarioHtml =
      '<tr><td>'+usuario.id+
      '</td><td>'+usuario.name+ ' ' +usuario.surname+
      '</td><td>'+usuario.username+
      '</td><td>'+usuario.email+
     '</td><td>'+usuario.dni+
     '</td><td>'+usuario.subjects+
     '</td><td>' + botonEliminar + '</td></tr>'
      listadoHtml +=usuarioHtml;
      }
          document.querySelector('#usuarios tbody').outerHTML = listadoHtml
 }
  async function eliminarUsuario(id){
  if(!confirm('¿Desea eliminar el usuario?')){
    return;
  }
     const request = await fetch('student/' + id, {
       method: 'DELETE',
       headers: {
         'Accept': 'application/json',
         'Content-Type': 'application/json'
       },
     });
    location.reload()
  }

 async function cargarTeachers(){
    const request = await fetch('teachers', {
      method: 'GET',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
    });
    const usuarios = await request.json();
    let listadoHtml = '';
       for (let usuario of usuarios){
       let usuarioHtml =
       '<tr><td>'+usuario.id+
       '</td><td>'+usuario.name+
       '</td><td>'+usuario.surname+
       '</td><td>'+usuario.username+
       '</td><td>'+usuario.email+
      '</td><td>'+usuario.course+
      '</td><td>'+usuario.entryDate+'</td><td><a href="#" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a></td></tr>'
       listadoHtml +=usuarioHtml;
       }
           document.querySelector('#teachers tbody').outerHTML = listadoHtml
  }

