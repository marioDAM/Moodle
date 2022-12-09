package com.moodle.project.repository;


import com.moodle.project.entity.Tarea;
import org.springframework.data.repository.CrudRepository;

public interface TareaRepository extends CrudRepository<Tarea, Long> {
}