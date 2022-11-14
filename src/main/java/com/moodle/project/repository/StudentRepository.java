package com.moodle.project.repository;

import com.moodle.project.entity.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Al ser un repositorio indica que es un DAO y se encarga de
 * tramitar la información directamente con la base de datos.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
