package com.moodle.project.service.users;


import com.moodle.project.dto.usuarios.CreateUsuarioDTO;
import com.moodle.project.entity.Usuario;
import com.moodle.project.enums.Role;
import com.moodle.project.errors.usuarios.NewUserWithDifferentPasswordsException;
import com.moodle.project.repository.newRepository.UsuariosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
// OJO la inyeccion de dependencias es a modo de constructor al poner @RequiredArgsConstructor
public class UsuarioService {
    private final UsuariosRepository usuariosRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Nos permite buscar un usuario por su nombre de usuario
     */
    public Optional<Usuario> findUserByUsername(String username) {
        return usuariosRepository.findByUsername(username);
    }

    public Optional<Usuario> findUserById(Long userId) {
        return usuariosRepository.findById(userId);
    }

    public List<Usuario> getUsers() {
        return usuariosRepository.findAll();
    }

    /**
     * Nos permite crear un nuevo Usuario con rol USER
     */
    public Usuario nuevoUsuario(CreateUsuarioDTO newUser) {
        // System.out.println(passwordEncoder.encode(newUser.getPassword()));
        if (newUser.getPassword().contentEquals(newUser.getPassword2())) {
            Usuario usuario = Usuario.builder()
                    .username(newUser.getUsername())
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .avatar(newUser.getAvatar())
                    .fullName(newUser.getFullname()).email(newUser.getEmail())
                    .dni(newUser.getDni())
                    .entryDate(newUser.getEntryDate())
                    .roles(Stream.of(Role.STUDE).collect(Collectors.toSet()))
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



}
