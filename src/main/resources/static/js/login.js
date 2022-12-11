$(document).ready(function () {

})

async function validate() {
    let token;
    let dates = {};

    dates.username = document.getElementById("username").value;
    dates.password = document.getElementById("password").value;

    let error = document.getElementById("error");
    if (username.value == "" || password.value == "") {

        error.textContent = "Debes rellenar los campos";

    } else {

        const request = await fetch('usuarios/authentication', {
            method: 'POST', headers: {
                'Accept': 'application/json', "Content-Type": "application/json"
            }, body: JSON.stringify(dates)
        });
        const u = await request.json();
        let roles = u.roles;
        token = u.token;

        if (request.status === 200 && roles.includes("ADMIN")) {
            console.log("Algo va bien");
            localStorage.token = token;

            localStorage.username = dates.username;
            return admin(localStorage.token);


        } else if (request.status === 200 && roles.includes("TEACH")) {
            window.location.href = '/profesor', 'refresh'


        } else if (request.status === 200 && roles.includes("STUDE")) {
            window.location.href = '/alumno', 'refresh'

        } else {
            alert("Las credenciales son incorrectas")
        }
    }

    async function admin(h) {

        console.log("token", h)
        const header = {
            "Accept": 'application/json',
            "Content-Type": 'application/json',
            "Redirect": 'follow',
            "Authorization": `Bearer ${h}`
        }
        alert("hhh")
        console.log("header", header)

        const response = fetch('/admin', {
            method: 'GET', headers: header
        });

        const respuesta = await response;
        console.log(" respuesta", respuesta.text());

        console.log("Funciona bien")
        window.location.href = '/admin', 'refresh';
        return respuesta
    }
}


