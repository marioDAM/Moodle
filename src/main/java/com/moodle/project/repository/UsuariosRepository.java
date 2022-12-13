package com.moodle.project.repository;

import com.moodle.project.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}
