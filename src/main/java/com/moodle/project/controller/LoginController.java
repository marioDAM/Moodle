package com.moodle.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping(path = {"/login", "/"})
    public String login() {
        return "login";
    }

    @GetMapping(path = {"/admin"})
    public String admin() {
        return "usuarios";
    }
}

