package com.moodle.project.mapper;

import com.moodle.project.dto.GetUsuarioDTO;
import com.moodle.project.entity.Usuario;
import org.springframework.stereotype.Component;
import com.moodle.project.enums.Role;

import java.util.stream.Collectors;

@Component
public class UsuarioMapper {
    public GetUsuarioDTO toDTO(Usuario user) {
        return GetUsuarioDTO.builder()
                .username(user.getUsername())
                .avatar(user.getAvatar())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .dni(user.getDni())
                .entryDate(user.getEntryDate())
                .roles(user.getRoles().stream()
                        .map(Role::name)
                        .collect(Collectors.toSet())
                ).build();
    }
}
