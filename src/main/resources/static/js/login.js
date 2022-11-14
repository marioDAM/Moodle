function validate(){
console.log("Hago click");
let error = document.getElementById("error");
 let email = document.getElementById("email");
 let password = document.getElementById("password");
 if(email.value == ""){
    error.textContent = "El usuario o contraseña, es incorrecto";
 }else{
      error.hidden = true;
      email.style.borderColor = "black";
    }
}