package com.moodle.project.entity.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    @NotNull(message = "El campo username no puede estar vacío")
    private String username;
    @NotNull(message = "El campo password no puede estar vacío")
    private String password;
}
