package com.moodle.project.repository;

import com.moodle.project.entity.Tarea;

import static org.assertj.core.api.Assertions.assertThat;

import com.moodle.project.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class TareaRepositoryTest {
    @Autowired
    private TareaRepository repository;
    @Autowired
    private UsuariosRepository usuariosRepository;

    private Tarea tarea1;


    @BeforeEach
    void setUp() {
        Usuario usuario = usuariosRepository.findAll().get(3);
        tarea1 = Tarea.builder()
                .descripcion("Completa esta comentario de texto")
                .titulo("Realiza este ejercicio")
                .usuario(usuario)
                .build();
    }


    @DisplayName("Test para guardar una tarea")
    @Test
    void testNuevoTarea() {
        Usuario usuario = new Usuario();
        usuario.setId(11L);
        Tarea tarea = Tarea.builder()
                .descripcion("DescripcionTest")
                .titulo("EjercicioTest")
                .usuario(usuario)
                .build();

        Tarea newTarea = repository.save(tarea);
        assertThat(newTarea).isNotNull();
        assertThat(newTarea.getId()).isGreaterThan(0);

    }

    @DisplayName("Test para listar las tareas")
    @Test
    void testListarTarea() {


        repository.save(tarea1);

        List<Tarea> tareas = repository.findAll();
        assertThat(tareas).isNotNull();
        assertThat(tareas.size()).isEqualTo(4);
    }

    @DisplayName("Test para listar una tarea por su id")
    @Test
    void testBuscarTareaById() {
        repository.save(tarea1);
        Tarea tarea = repository.findById(tarea1.getId()).get();
        assertThat(tarea).isNotNull();
    }

    @DisplayName("Test para actualizar una tarea")
    @Test
    void testActualizarTarea() {
        repository.save(tarea1);
        Tarea tarea = repository.findById(tarea1.getId()).get();
        tarea.setTitulo("Antonio Rodríguez");
        tarea.setDescripcion("1234");
        Tarea putUsuario = repository.save(tarea);

        assertThat(putUsuario.getTitulo()).isEqualTo("Antonio Rodríguez");
        assertThat(putUsuario.getDescripcion()).isEqualTo("1234");

    }

    @DisplayName("Test para eliminar una tarea")
    @Test
    void testEliminarTarea() {
        repository.save(tarea1);
        repository.deleteById(tarea1.getId());

        Optional<Tarea> tarea = repository.findById(tarea1.getId());
        assertThat(tarea).isEmpty();

    }
}
