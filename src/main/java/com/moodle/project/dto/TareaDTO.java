package com.moodle.project.dto;

import com.moodle.project.entity.Usuario;
import lombok.Data;

@Data
public class TareaDTO {

    private String titulo;

    private String descripcion;

    private Usuario usuario;
}
