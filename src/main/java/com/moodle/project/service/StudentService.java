package com.moodle.project.service;

import com.moodle.project.dto.NewStudentDTO;
import com.moodle.project.entity.models.Student;
import com.moodle.project.mapper.NewStudentDTOToStudent;
import com.moodle.project.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Decimos servicio donde se encuentra la capa de negocio, y es la que
 * utiliza el repositorio para manejar la información
 */
@Service
public class StudentService {
    private final StudentRepository repository;
    private final NewStudentDTOToStudent mapper;

    public StudentService(StudentRepository repository, NewStudentDTOToStudent mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Student saveAlumno(NewStudentDTO alumno) {
        Student student = mapper.map(alumno);
        return repository.save(student);
    }

    public List<Student> getStudents() {
        return repository.findAll();
    }

    public Student updateAlumno(Student student) {
        Student alumno = repository.findById(student.getId()).orElseThrow();
        alumno.setName(student.getName());
        alumno.setSurname(student.getSurname());
        alumno.setEmail(student.getEmail());
        alumno.setTeachers(student.getTeachers());
        return repository.save(alumno);
    }

    public Student deleteStudent(Long id) {
        repository.deleteById(id);
        return null;
    }

}
