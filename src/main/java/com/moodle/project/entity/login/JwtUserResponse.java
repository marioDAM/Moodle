package com.moodle.project.entity.login;

import com.moodle.project.dto.GetStudentDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

// Respuesta al loguearte con los datos del usuario, es el JSON...
@Getter
@Setter
@NoArgsConstructor
public class JwtUserResponse extends GetStudentDTO {

    @NotNull(message = "El token no puede ser nulo")
    private String token;

    @Builder(builderMethodName = "jwtUserResponseBuilder") // Lo llamos así por tener dos builder en dos clases.
    // Le añadimos el token

    public JwtUserResponse(@NotBlank(message = "El nombre no puede estar vacío") String username, String avatar, @NotBlank(message = "El nombre de usuario no puede estar vacío") String fullName, @Email(regexp = ".*@.*\\..*", message = "Email debe ser válido") String email, String token) {
        super(username, avatar, fullName, email);
        this.token = token;
    }
}