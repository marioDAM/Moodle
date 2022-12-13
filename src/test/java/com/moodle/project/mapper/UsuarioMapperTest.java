package com.moodle.project.mapper;

import com.moodle.project.dto.CreateUsuarioDTO;
import com.moodle.project.dto.GetUsuarioDTO;
import com.moodle.project.service.UsuarioService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = {UsuarioMapper.class})
public class UsuarioMapperTest {
    private CreateUsuarioDTO dto;
    private UsuarioMapper mapper;
    @InjectMocks

    private UsuarioService service;

    @BeforeAll
    void init() {
        dto = new CreateUsuarioDTO();
        dto.setFullname("Test");
        dto.setPassword("123");
        dto.setPassword2("123");
        dto.setDni("768976789D");
        dto.setEmail("test@gmail.com");
        dto.setUsername("testuser");

        mapper = new UsuarioMapper();
    }

    @Test
    void ContadorMapperTest() {
        GetUsuarioDTO ans = mapper.toDTO(service.nuevoAlumno(dto));

        assertAll(
                () -> assertNotNull(ans),
                () -> assertEquals("Test", ans.getFullName()),
                () -> assertEquals("testuser", ans.getUsername()),
                () -> assertEquals("768976789D", ans.getDni())

        );
    }
}
