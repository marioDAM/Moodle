 $(document).ready(function() {

 })
  async function validate(){
let dates = {};
dates.username = document.getElementById("username").value;
dates.password = document.getElementById("password").value;

let error = document.getElementById("error");
 if(username.value == "" || password.value == ""){
    error.textContent = "El usuario o contraseña, es incorrecto";
 }else{
     // error.hidden = true;
      //email.style.borderColor = "black";
        console.log("Algo va bien");
        const request = await fetch('usuarios/login',{
        method: 'POST',
         headers: {
                'Accept': 'application/json',
                "Content-Type": "application/json"
            },
                body: JSON.stringify(dates)
        });
            const usurious = await request.json();
        }
    }

//async function clinicianSession(){
//let dates = {};
//dates.email = document.getElementById("email").value;
//dates.password = document.getElementById("password").value;
//
//cons request = await fetch('/login',{
//method: 'POST',
// headers: {
//        'Accept': 'application/json',
//        "Content-Type": "application/json"
//    },
//        body: JSON.stringify(dates)
//});
//    const usurious = await request.son();
//}