package com.moodle.project.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUsuarioDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String username;

    private String avatar;

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    private String fullName;

    @Email(regexp = ".*@.*\\..*", message = "Email debe ser válido")
    private String email;

    @NotBlank(message = "El dni no puede estar vacío")
    private String dni;

    private LocalDateTime entryDate = LocalDateTime.now();

    private Set<String> roles;


}
