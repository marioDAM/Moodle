package com.moodle.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Clase controladora, que nos define la ruta donde se va a visualizar nuestro
 * front-end en HTML
 *
 * @author Mario Valverde
 */
@Controller
public class LoginController {
    /**
     * La ruta que vemos en @GetMapping, será donde veamos la manera de indentificar a los usuarios
     *
     * @return El login que hemos diseñado en la carpeta templates
     */
    @GetMapping(path = {"/login", "/"})
    public String login() {
        return "Login";
    }

    /**
     * La ruta que vemos en @GetMapping, será donde veamos la interfaz del profesor(administrador), donde podrá realizar sus acciones
     *
     * @return La vista donde se encentra la interfaz del administrador
     */
    @GetMapping(path = {"/admin"})
    public String admin() {
        return "usuarios";
    }

    @GetMapping(path = {"/register"})
    public String register() {
        return "register";
    }

    @GetMapping(path = {"/alumno"})
    public String alumno() {
        return "alumnos";
    }
}

