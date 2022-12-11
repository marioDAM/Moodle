package com.moodle.project.controller;

import com.moodle.project.dto.CreateUsuarioDTO;
import com.moodle.project.dto.GetUsuarioDTO;
import com.moodle.project.entity.Usuario;
import com.moodle.project.mapper.UsuarioMapper;
import com.moodle.project.security.jwt.JwtTokenProvider;
import com.moodle.project.security.jwt.model.JwtUserResponse;
import com.moodle.project.security.jwt.model.LoginRequest;
import com.moodle.project.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.moodle.project.enums.Role;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("usuarios")
/**
 * Inyeccion de dependencias usando Lombok y private final y no @Autowired, ver otros controladores
 */
public class UsuarioController {
    //Servicio
    @Autowired
    private UsuarioService service;
    //Intenta autenticar el objeto,devolviendo un objeto de autenticación completamente rellenado (incluidas las autoridades concedidas) si tiene éxito.
    @Autowired
    private AuthenticationManager authenticationManager;

    //Mapper para transformar objetos en entidad y viceversa
    @Autowired
    private UsuarioMapper ususuarioMapper;
    //Clase encargada de generar el token
    @Autowired
    private JwtTokenProvider tokenProvider;

    @ApiOperation(value = "Devuelve un usuario  por su username")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Usuario encontrado"), @ApiResponse(code = 400, message = "Error al devolver usuario")})
    @GetMapping("/user/{username}")
    public Optional<Usuario> findByUsername(@PathVariable String username) {
        return service.findUserByUsername(username);
    }


    @ApiOperation(value = "Crea un alumno")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Alumno creado"), @ApiResponse(code = 400, message = "Error al crear alumno")})
    @PostMapping("/")
    public GetUsuarioDTO nuevoUsuario(@RequestBody CreateUsuarioDTO newUser) {

        //   System.out.println("OBJETO DTO CONTROLADOR" + newUser.getFullname()+newUser.getEmail()+newUser.getUsername()+newUser.getDni()+newUser.getPassword2()+newUser.getEntryDate());
        return ususuarioMapper.toDTO(service.nuevoUsuario(newUser));
    }

    @ApiOperation(value = "Crea un Profesor")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Profesor creado"), @ApiResponse(code = 400, message = "Error al crear profesor")})
    @PostMapping("/teach")
    public GetUsuarioDTO nuevoProfesor(@RequestBody CreateUsuarioDTO newUser) {
        return ususuarioMapper.toDTO(service.nuevoProfesor(newUser));
    }


    // Petición me de datos del usuario
    // Equivalente en ponerlo en config, solo puede entrar si estamos autenticados
    // De esta forma podemos hacer las rutas espècíficas
    @ApiOperation(value = "Devuelve los datos del usuario")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Usuario devuelto"), @ApiResponse(code = 401, message = "No autenticado"), @ApiResponse(code = 403, message = "No autorizado")})
    @GetMapping("/me")
    public GetUsuarioDTO me(@AuthenticationPrincipal Usuario user) {
        return ususuarioMapper.toDTO(user);
    }

    @ApiOperation(value = "Autentica un usuario")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Usuario autenticado y token generado"), @ApiResponse(code = 400, message = "Error al autenticar usuario"),})
    @PostMapping("/authentication")
    public JwtUserResponse login(@Valid @RequestBody LoginRequest loginRequest) throws IOException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()

        ));
        // Autenticamos al usuario, si lo es nos lo devuelve
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Devolvemos al usuario autenticado
        Usuario user = (Usuario) authentication.getPrincipal();


        // Generamos el token
        String jwtToken = tokenProvider.generateToken(authentication);

        // La respuesta que queremos
        return convertUserEntityAndTokenToJwtUserResponse(user, jwtToken);

    }


    /**
     * Método que convierte un usuario y un token a una respuesta de usuario
     *
     * @param user     Usuario
     * @param jwtToken Token
     * @return JwtUserResponse con el usuario y el token
     */
    private JwtUserResponse convertUserEntityAndTokenToJwtUserResponse(Usuario user, String jwtToken) {
        return JwtUserResponse.jwtUserResponseBuilder()
                .fullName(user.getFullName())
                .email(user.getEmail())
                .dni(user.getDni())
                .username(user.getUsername())
                .entryDate(user.getEntryDate())
                .avatar(user.getAvatar())
                .roles(user.getRoles().stream().map(Role::name).collect(Collectors.toSet()))
                .token(jwtToken).build();
    }

    /**
     * Método que devuelve todos los usuarios
     *
     * @return all users
     */
    @ApiOperation(value = "Devuelve todos los usuarios")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Usuarios mostrados"), @ApiResponse(code = 400, message = "Error al mostrar los usuarios"),})
    @GetMapping("/getAll")
    public List<Usuario> getUsers() {
        return service.getUsers();
    }

    /**
     * Método que devuelve todos los alumnos con rol de alumno
     *
     * @return Usuario.STUDY
     */
    @ApiOperation(value = "Devuelve todos los alumnos")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Alumnos mostrados"), @ApiResponse(code = 400, message = "Error al mostrar los alumnos"),})
    @GetMapping("/getAll/students")
    public List<Usuario> getStudents() {
        return service.getAlumnos();
    }

    /**
     * Método que elimina un usuario a través de su id
     *
     * @param id
     * @return Usuario eliminado
     */
    @ApiOperation(value = "Elimina un usuario  por su id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Usuario eliminado"), @ApiResponse(code = 400, message = "Error al eliminar usuario")})
    @DeleteMapping("/{id}")
    public Usuario removeUser(@PathVariable Long id) {
        return service.deleteUsuario(id);
    }

    /**
     * Método que devuelve un usuario a través de su id
     *
     * @param id
     * @return Usuario
     */

    @ApiOperation(value = "Devuelve un usuario  por su id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Usuario encontrado"), @ApiResponse(code = 400, message = "Error al devolver usuario")})
    @GetMapping("/{id}")
    public Optional<Usuario> getUsuarioById(@PathVariable Long id) {
        return service.findUserById(id);
    }
}
