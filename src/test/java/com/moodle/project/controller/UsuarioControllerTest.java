package com.moodle.project.controller;

import com.moodle.project.dto.CreateUsuarioDTO;
import com.moodle.project.dto.GetUsuarioDTO;
import com.moodle.project.entity.Tarea;
import com.moodle.project.entity.Usuario;
import com.moodle.project.mapper.UsuarioMapper;
import com.moodle.project.repository.UsuariosRepository;
import com.moodle.project.service.UsuarioService;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = {UsuarioController.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioControllerTest {
    @InjectMocks
    private UsuarioController controller;
    @MockBean
    private MockMvc mvc;
    @MockBean
    private UsuariosRepository repository;
    @MockBean
    private UsuarioService service;

    @MockBean
    private UsuarioMapper mapper;

    Usuario usuario;

    Tarea tarea;
    CreateUsuarioDTO dto;


    @BeforeAll
    void init() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setFullName("Test");
        usuario.setPassword("123");
        usuario.setDni("768976789D");
        usuario.setEmail("test@gmail.com");
        usuario.setUsername("testDesc");

        dto = new CreateUsuarioDTO();
        dto.setFullname("Test");
        dto.setPassword("123");
        dto.setDni("768976789D");
        dto.setEmail("test@gmail.com");
        dto.setUsername("testDesc");

        tarea = new Tarea();
        tarea.setUsuario(usuario);
        tarea.setTitulo("test");
        tarea.setDescripcion("test");


        mapper = new UsuarioMapper();

        controller = new UsuarioController();
    }

    @Test
    void createUser() {
        when(repository.save(any())).thenReturn(usuario);

        when(repository.existsById(any())).thenReturn(true);

        GetUsuarioDTO response = controller.nuevoUsuario(dto);
        var r = response;

        assertAll(
                () -> assertNotNull(r),
                () -> assertEquals(200, 200)
        );
    }


    @Test
    @Order(1)
    void getAlTestMock() {
        var dto = CreateUsuarioDTO.builder()
                .fullname(usuario.getFullName())
                .dni(usuario.getDni())
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .email(usuario.getEmail())

                .build();

        Mockito.when(repository.findAll())
                .thenReturn(List.of(usuario));

        Mockito.when(mapper.toDTO(usuario)).thenReturn((GetUsuarioDTO) List.of(dto));

        var response = controller.getUsers();
        var res = response;

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), 200),
                () -> assertEquals(res.get(0).getFullName(), usuario.getFullName()),
                () -> assertEquals(res.get(0).getPassword(), usuario.getPassword()),
                () -> assertEquals(res.get(0).getEmail(), usuario.getEmail()),
                () -> assertEquals(res.get(0).getDni(), usuario.getDni()),
                () -> assertEquals(res.get(0).getUsername(), usuario.getUsername())

        );
    }

    @Test
    public void testAddEmployee() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(service.nuevoAlumno(any(CreateUsuarioDTO.class)));

        CreateUsuarioDTO employee = new CreateUsuarioDTO("Lokesh", "Gupta", "howtodoinjava@gmail.com", "g", "@hotmail.com", "34343D", "123", "123", LocalDateTime.now());
        GetUsuarioDTO responseEntity = controller.nuevoUsuario(employee);

        assertThat(responseEntity.getDni()).isEqualTo("34343D");
    }

    @Test
    void h() throws Exception {
        given((RequestSpecification) service.getUsers());
        mvc.perform(get("usuarios/getAll")).andExpect(status().isOk()).andExpect((ResultMatcher) jsonPath("$.size"));
    }

    @Test
    void testVerDetalles() throws Exception {
        when(service.findUserById(1L)).thenReturn(Optional.of(Optional.ofNullable(usuario).orElseThrow()));
        mvc.perform(get("usuarios/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    @Order(1)
    void getAllTestMock() {
        var dto = GetUsuarioDTO.builder()
                .fullName(usuario.getFullName())
                .email(usuario.getEmail())
                .entryDate(usuario.getEntryDate())
                .avatar(usuario.getAvatar())
                .username(usuario.getUsername())
                .build();


        when(repository.findAll())
                .thenReturn(List.of(usuario));


        ResponseEntity<?> response = (ResponseEntity<?>) controller.getUsers();
        GetUsuarioDTO res = (GetUsuarioDTO) response.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode().value()),
                () -> assertEquals(res.getUsername(), usuario.getUsername()),
                () -> assertEquals(res.getFullName(), usuario.getFullName()),
                () -> assertEquals(res.getEntryDate(), usuario.getEntryDate()),
                () -> assertEquals(res.getAvatar(), usuario.getAvatar()),
                () -> assertEquals(res.getEmail(), usuario.getEmail())

        );
    }

    @Test
    @Order(2)
    void getByIdTestMock() {
        var dto = GetUsuarioDTO.builder()
                .fullName(usuario.getFullName())
                .email(usuario.getEmail())
                .entryDate(usuario.getEntryDate())
                .avatar(usuario.getAvatar())
                .username(usuario.getUsername())
                .build();

        when(repository.findById(1L))
                .thenReturn(java.util.Optional.of(usuario));


        var response = service.findUserById(1L);
        var res = response;

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response),
                () -> assertEquals(res.get().getUsername(), usuario.getUsername()),
                () -> assertEquals(res.get().getPassword(), usuario.getPassword()),
                () -> assertEquals(res.get().getAuthorities(), usuario.getAuthorities())
        );
    }
}