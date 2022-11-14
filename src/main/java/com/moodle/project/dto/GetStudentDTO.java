package com.moodle.project.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetStudentDTO {
    @NotBlank(message = "El nombre no puede estar vacío")
    private String username;

    private String avatar;

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    private String fullName;

    @Email(regexp = ".*@.*\\..*", message = "Email debe ser válido")
    private String email;


}
