$(document).ready(function () {

})

async function validate() {
    let dates = {};
    dates.username = document.getElementById("username").value;
    dates.password = document.getElementById("password").value;

    let error = document.getElementById("error");
    if (username.value == "" || password.value == "") {
        error.textContent = "Debes rellenar los campos";
    } else {
        // error.hidden = true;
        //email.style.borderColor = "black";
        const request = await fetch('usuarios/login', {
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
            localStorage.token = usurious;
            localStorage.username = dates.username;
            window.location.href = '/admin'
        } else {
            alert("Las credenciales son incorrectas")
        }
    }
}

