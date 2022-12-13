package com.moodle.project.service;

import com.moodle.project.dto.CreateUsuarioDTO;
import com.moodle.project.entity.Usuario;
import com.moodle.project.repository.UsuariosRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UsuarioServiceTest {
    @Autowired

    private UsuariosRepository repository;
    @Autowired
    private UsuarioService service;

    @DisplayName("Test para añadir un alumno")

    @Test
    void añadirAlumno() {
        CreateUsuarioDTO dto = CreateUsuarioDTO.builder()
                .fullname("jacobo")
                .username("jac")
                .email("jac@hotmail.com")
                .password("123")
                .password2("123")
                .dni("43543D")
                .build();
        service.nuevoAlumno(dto);
        assertThat(dto).isNotNull();
        assertThat(dto.getDni().equals("43543D"));
    }

    @DisplayName("Test para añadir un profesor")

    @Test
    void añadirProfesor() {
        CreateUsuarioDTO dto = CreateUsuarioDTO.builder()
                .fullname("xavier")
                .username("xavi")
                .email("xavi@hotmail.com")
                .password("123")
                .password2("123")
                .dni("56789876A")
                .build();
        service.nuevoAlumno(dto);
        assertThat(dto).isNotNull();
        assertThat(dto.getDni().equals("56789876A"));
    }

    @DisplayName("Test para eliminar un usuario")
    @Test
    void testEliminarUsuario() {
        List<Usuario> usuarios = service.getUsers();
        Usuario usuario = usuarios.get(0);
        service.deleteUsuario(usuario.getId());
        assertThat(usuario.getDni().equals("56789876A"));


    }

    @DisplayName("Test para listar todos los usuarios")
    @Test
    void testListarUsuarios() {
        List<Usuario> usuarios = service.getUsers();
        assertEquals(5, usuarios.size());

    }
}
