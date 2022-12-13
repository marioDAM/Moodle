package com.moodle.project.repository;


import com.moodle.project.entity.Tarea;
import com.moodle.project.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
}