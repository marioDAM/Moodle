package com.moodle.project.repository;

import com.moodle.project.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.moodle.project.enums.Role.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {
    @Autowired
    private UsuariosRepository repository;

    private Usuario usuario1;

    @BeforeEach
    void setUp() {
        usuario1 = Usuario.builder()
                .fullName("William")
                .email("william@hotmail.com")
                .username("willy")
                .password("123")
                .dni("8765456765F")
                .entryDate(LocalDateTime.now())
                .isTerminated(true)
                .build();
    }

    @DisplayName("Test para guardar un alumno")
    @Test
    void testNuevoAlumno() {
        Usuario usuario = Usuario.builder()
                .fullName("Tate")
                .email("tata@hotmail.com")
                .username("tati")
                .password("123")
                .dni("34564323456D")
                .roles(Collections.singleton(STUDE))
                .entryDate(LocalDateTime.now())
                .isTerminated(true)
                .build();

        Usuario newUsuario = repository.save(usuario);
        assertThat(newUsuario).isNotNull();
        assertThat(newUsuario.getId()).isGreaterThan(0);

    }

    @DisplayName("Test para guardar un profesor")
    @Test
    void testNuevoProfesor() {
        Usuario usuario = Usuario.builder()
                .fullName("Emilio")
                .email("emilio@hotmail.com")
                .username("emi")
                .password("123")
                .dni("54456543F")
                .roles(Collections.singleton(TEACH))
                .entryDate(LocalDateTime.now())
                .build();

        Usuario newUsuario = repository.save(usuario);
        assertThat(newUsuario).isNotNull();
        assertThat(newUsuario.getId()).isGreaterThan(0);

    }

    @DisplayName("Test para listar los usuarios")
    @Test
    void testListarUsuarios() {
        Usuario usuario = Usuario.builder()
                .fullName("Emilio")
                .email("emilio@hotmail.com")
                .username("emi")
                .password("123")
                .dni("54456543F")
                .roles(Collections.singleton(TEACH))
                .entryDate(LocalDateTime.now())
                .build();

        repository.save(usuario);
        repository.save(usuario1);

        List<Usuario> usuarios = repository.findAll();
        assertThat(usuarios).isNotNull();
        assertThat(usuarios.size()).isEqualTo(6);
    }

    @DisplayName("Test para listar un usuario por su id")
    @Test
    void testBuscarUsuarioById() {
        repository.save(usuario1);
        Usuario usuario = repository.findById(usuario1.getId()).get();
        assertThat(usuario).isNotNull();
    }

    @DisplayName("Test para actualizar un usuario")
    @Test
    void testActualizarUsuario() {
        repository.save(usuario1);
        Usuario usuario = repository.findById(usuario1.getId()).get();
        usuario.setUsername("Antonio Rodríguez");
        usuario.setPassword("1234");
        Usuario putUsuario = repository.save(usuario);

        assertThat(putUsuario.getUsername()).isEqualTo("Antonio Rodríguez");
        assertThat(putUsuario.getPassword()).isEqualTo("1234");

    }

    @DisplayName("Test para eliminar un usuario")
    @Test
    void testEliminarUsuario() {
        repository.save(usuario1);
        repository.deleteById(usuario1.getId());

        Optional<Usuario> usuario = repository.findById(usuario1.getId());
        assertThat(usuario).isEmpty();

    }
}
