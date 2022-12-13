package com.moodle.project.service;

import com.moodle.project.dto.CreateUsuarioDTO;
import com.moodle.project.entity.Usuario;
import com.moodle.project.enums.Role;
import com.moodle.project.errors.usuarios.NewUserWithDifferentPasswordsException;
import com.moodle.project.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.moodle.project.enums.Role.STUDE;

@Service
public class UsuarioService {
    /**
     * Inyeccion de dependencias por me dio de @Autowired
     */
    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Nos permite buscar un usuario por su username
     *
     * @return Usuario
     */
    public Optional<Usuario> findUserByUsername(String username) {
        return usuariosRepository.findByUsername(username);
    }

    /**
     * Nos permite buscar un usario por su id.
     *
     * @param userId
     * @return Usuario
     */
    public Optional<Usuario> findUserById(Long userId) {
        return usuariosRepository.findById(userId);
    }

    /**
     * Nos permite ver todos los usuarios añadidos en la aplicación
     * independiente de su rol en esta.
     *
     * @return lista de usuarios
     */
    public List<Usuario> getUsers() {
        return usuariosRepository.findAll();
    }

    /**
     * Nos permite ver todos los alumnos de la aplicación
     *
     * @return todos los alumnos de la aplicación
     */
    public List<Usuario> getAlumnos() {
        List<Usuario> usuarios = getUsers();
        List<Usuario> alumnos = new ArrayList<>();
        Usuario usuario;
        for (int i = 0; i < usuarios.size(); i++) {
            usuario = usuarios.get(i);
            if (usuario.getRoles().contains(STUDE)) {
                alumnos.add(usuario);
            }
        }
        return alumnos;

    }

    /**
     * Nos permite crear un nuevo Usuario con rol alumno
     */
    public Usuario nuevoAlumno(CreateUsuarioDTO newStudent) {
        // System.out.println(passwordEncoder.encode(newUser.getPassword()));
        System.out.println();
        if (newStudent.getPassword().contentEquals(newStudent.getPassword2())) {
            Usuario usuario = Usuario.builder()
                    .username(newStudent.getUsername())
                    .password(passwordEncoder.encode(newStudent.getPassword()))
                    .avatar(newStudent.getAvatar())
                    .subjects(newStudent.getSubject())
                    .fullName(newStudent.getFullname()).email(newStudent.getEmail())
                    .dni(newStudent.getDni())
                    .entryDate(newStudent.getEntryDate())
                    .roles(Stream.of(STUDE).collect(Collectors.toSet()))
                    .build();
            try {
                return usuariosRepository.save(usuario);
            } catch (DataIntegrityViolationException ex) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de usuario ya existe");
            }
        } else {

            throw new NewUserWithDifferentPasswordsException();
        }
    }

    /**
     * Método que permite añadir una lista de alumnos
     *
     * @param alumnos
     */
    @Transactional(rollbackOn = Exception.class)
    public void nuevosAlumnos(List<Usuario> alumnos) {
        usuariosRepository.saveAll(alumnos);
    }

    /**
     * Método que permite añadir un usuario con rol profesor
     *
     * @param newTeach
     * @return
     */
    public Usuario nuevoProfesor(CreateUsuarioDTO newTeach) {
        if (newTeach.getPassword().contentEquals(newTeach.getPassword2())) {
            Usuario usuario = Usuario.builder()
                    .username(newTeach.getUsername())
                    .password(passwordEncoder.encode(newTeach.getPassword()))
                    .avatar(newTeach.getAvatar())
                    .fullName(newTeach.getFullname()).email(newTeach.getEmail())
                    .dni(newTeach.getDni())
                    .entryDate(newTeach.getEntryDate())
                    .roles(Stream.of(Role.TEACH).collect(Collectors.toSet()))
                    .build();
            try {
                return usuariosRepository.save(usuario);
            } catch (DataIntegrityViolationException ex) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de usuario ya existe");
            }
        } else {
            throw new NewUserWithDifferentPasswordsException();
        }

    }

    /**
     * Método que borra un usuario
     *
     * @param id
     * @return Usuario eliminado
     */
    public Usuario deleteUsuario(Long id) {
        usuariosRepository.deleteById(id);
        return null;
    }


}
