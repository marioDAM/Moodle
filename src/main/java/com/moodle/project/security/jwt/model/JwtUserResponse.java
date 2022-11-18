package com.moodle.project.security.jwt.model;

import com.moodle.project.dto.usuarios.GetUsuarioDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

// Respuesta al loguearte con los datos del usuario, es el JSON...
@Getter
@Setter
@NoArgsConstructor
public class JwtUserResponse extends GetUsuarioDTO {

    @NotNull(message = "El token no puede ser nulo")
    private String token;

    @Builder(builderMethodName = "jwtUserResponseBuilder") // Lo llamos así por tener dos builder en dos clases.
    // Le añadimos el token
    public JwtUserResponse(String username, String avatar, String fullName, String email, String dni, LocalDateTime entryDate, Set<String> roles, String token) {
        super(username, avatar, fullName, email, dni, entryDate, roles);
        this.token = token;
    }
}