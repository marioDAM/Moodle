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
        const request = await fetch('usuarios/login', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                "Content-Type": "application/json",
                "Authorization": "Bearer" + localStorage.token
            },
            body: JSON.stringify(dates)
        });
        const u = await request.json();
        let roles = u.roles;
        token = u.token;
        console.log(token);

        if (request.status === 200 && roles.includes("ADMIN") || roles.includes("TEACH")) {
            console.log("Algo va bien");
            localStorage.token = token;
            console.log(localStorage.token);
            alert("hhh")
            localStorage.username = dates.username;
            window.location.href = '/admin'
            return u;

        } else if (request.status === 200 && roles.includes("STUDE")) {
            window.location.href = '/alumno'

        } else {
            alert("Las credenciales son incorrectas")
        }
    }
}

