alert("Bienvenido a la Autoevaluación correspondiente al primer mes \n\nSeleccione las respuestas que crea correctas, teniendo en " +
    "\ncuenta las siguientes consideraciones \n\n1. Los aciertos tienen el valor de un punto" +
    " \n\n2. Las respuestas no contestadas ni suman ni restan puntos.")


var resp = new Array;
var faite = new Array;
var score = 0;

const data = document.querySelector("content");
data.innerHTML = "<h1>Tema 1</h1>";

resp[1] = "a";
resp[2] = "b";
resp[3] = "c";
resp[4] = "b";
resp[5] = "c";
resp[6] = "c";
resp[7] = "b";
resp[8] = "c";
resp[9] = "b";
resp[10] = "a";

function Engine(question, repose) {
    if (repose != resp[question]) {
        if (!faite[question]) {
            faite[question] = -1;
        }
    } else {

        if (!faite[question]) {
            faite[question] = -1;
            score++;
        }

    }
}

function Nivel() {

    if (score >= 9 && score < 10) {
        alert(score + "/10 " + "Muy bien, prueba a superarlo");
        p.innerText = "Esto es un texto simple"
        p.innerHTML = "<h2>Esto es inserción de <code>html</code></h2>"
    }
    if (score >= 7 && score < 8) {
        alert(score + "/10 " + "Bien, pero puedes hacerlo mejor");
        p.innerText = "Esto es un texto simple"
        p.innerHTML = "<h2>Esto es inserción de <code>html</code></h2>"
    }
    if (score >= 5 && score < 6) {
        alert(score + "/10 " + "Aprobado por los pelos. No te fíes");
        p.innerText = "Esto es un texto simple"
        p.innerHTML = "<h2>Esto es inserción de <code>html</code></h2>"
    }
    if (score >= 3 && score < 4) {
        alert(score + "/10 " + "Insuficiente. Has de estudiar más");
        p.innerText = "Esto es un texto simple"
        p.innerHTML = "<h2>Esto es inserción de <code>html</code></h2>"
    }
    if (score < 2) {
        alert("Su puntuación es: " + score
            + "\nLa puntuación máxima que podía obtener es 10"
            + "\nTiene que reviazar nuevamente sus lecciones"
            + "\nSu porcentaje de aciertos es menos del 10%");
        p.innerText = "Esto es un texto simple"

    }
}
