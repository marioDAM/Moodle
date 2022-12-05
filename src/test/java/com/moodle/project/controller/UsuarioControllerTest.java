package com.moodle.project.controller;

import com.moodle.project.dto.GetUsuarioDTO;
import com.moodle.project.entity.Usuario;
import com.moodle.project.mapper.UsuarioMapper;
import com.moodle.project.repository.UsuariosRepository;
import com.moodle.project.security.jwt.JwtTokenProvider;
import com.moodle.project.security.jwt.model.JwtUserResponse;
import com.moodle.project.security.jwt.model.LoginRequest;
import com.moodle.project.service.CustomUserDetailsService;
import com.moodle.project.service.UsuarioService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DataJpaTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsuarioControllerTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMVC;
    private TestEntityManager entityManager;

    UsuarioControllerTest(TestEntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Test
    void testVerDetalles() throws Exception {
        when(service.findUserById(1L)).thenReturn(Optional.of(Optional.ofNullable(usuario).orElseThrow()));
        mockMVC.perform(get("usuarios/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON));
    }

    @BeforeEach
    void setUp() {
        clearData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from Usuario");

    }

    @InjectMocks
    private static UsuarioController controller;
    @MockBean
    private static UsuariosRepository repository;
    @MockBean
    private JwtUserResponse userResponse;
    @MockBean
    private LoginRequest lr;
    @MockBean
    private static UsuarioService service;
    @Mock
    JwtTokenProvider provider;
    @Mock
    private UsuarioMapper mapper;

    private CustomUserDetailsService detailsService;


    private final Usuario usuario = Usuario.builder().fullName("Andrés Mellado")
            .id(1L)
            .email("andres@hotmail.com").dni("56765678D")
            .entryDate(LocalDateTime.MAX)
            .avatar("image")
            .username("andre")
            .password("123")
            .build();


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

        when(mapper.toDTO(usuario)).thenReturn(dto);

        ResponseEntity<?> response = (ResponseEntity<?>) controller.getAllUsers();
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

        when(mapper.toDTO(usuario)).thenReturn(dto);

        var response = detailsService.loadUserById(1L);
        var res = response;

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response),
                () -> assertEquals(res.getUsername(), usuario.getUsername()),
                () -> assertEquals(res.getPassword(), usuario.getPassword()),
                () -> assertEquals(res.getAuthorities(), usuario.getAuthorities())
        );
    }
}