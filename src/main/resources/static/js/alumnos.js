$(document).ready(function () {

    actualizarEmailUsuario();
});
function actualizarEmailUsuario() {
    document.getElementById("span").outerHTML = localStorage.username;
}
function a (){
    document.getElementById("content").outerHTML = localStorage.username;

    var mensaje;
    var opcion = prompt("Introduzca su nombre:", "Aner Barrena");

    if (opcion == null || opcion == "") {
        mensaje = "Has cancelado o introducido el nombre vacío";
    } else {
        mensaje = "Hola " + opcion;
    }
    document.getElementById("ejemplo").innerHTML = mensaje;
}