$(document).ready(function () {

})

async function validate() {
    let dates = {};
    dates.fullname = document.getElementById("fullname").value
    dates.username = document.getElementById("username").value;
    dates.password = document.getElementById("password").value;
    dates.password2 = document.getElementById("password2").value;
    dates.email = document.getElementById("email").value;
    dates.dni = document.getElementById("dni").value;

    let error = document.getElementById("error");
    if (username.value == "" || password.value == "" || email.value == "") {
        error.textContent = "Debes rellenar los campos";
    } else {
        const request = await fetch('usuarios/', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                "Content-Type": "application/json"
            },
            body: JSON.stringify(dates)
        });
        const usurious = await request.json();
        if (request.status === 200) {
            console.log("Algo va bien");
            alert("Se ha registrado correctamente el alumno ")
            window.location.href = '/admin'
        } else {
            alert("Las credenciales son incorrectas")
        }
    }
}
