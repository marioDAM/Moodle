package com.moodle.project.service;

import com.moodle.project.entity.Tarea;
import com.moodle.project.entity.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TareaServiceTest {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TareaService service;

    @DisplayName("Test para añadir una tarea")
    @Test
    void añadirTarea() {
        Usuario usuario = usuarioService.getAlumnos().get(0);
        Tarea tarea = Tarea.builder().titulo("TituloTest").descripcion("DescripcionTest").usuario(usuario).build();
        service.createTarea(tarea);
        assertThat(tarea).isNotNull();
        assertThat(tarea.getDescripcion().equals("DescripcionTest"));
    }


    @DisplayName("Test para eliminar una tarea")
    @Test
    void testEliminarTarea() throws Exception {
        List<Tarea> tareas = service.getTareas();
        Tarea tarea = tareas.get(0);
        service.modificaTarea(tarea.getId(), tarea.getTitulo());
        assertThat(tarea).isNotNull();


    }

    @DisplayName("Test para listar todos las tareas")
    @Test
    void testListarTareas() {
        List<Tarea> tareas = service.getTareas();
        assertEquals(1, tareas.size());

    }
}
