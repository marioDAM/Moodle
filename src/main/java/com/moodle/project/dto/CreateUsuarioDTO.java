package com.moodle.project.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUsuarioDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String username;

    private String avatar;

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    private String fullname;

    @Email(regexp = ".*@.*\\..*", message = "Email debe ser válido")
    private String email;
    @NotBlank(message = "El dni no puede estar vacío")
    private String dni;

    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;

    @NotBlank(message = "La contraseña2 no puede estar vacía")
    private String password2;

    public LocalDateTime entryDate = LocalDateTime.now();


}
