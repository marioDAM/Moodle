package com.moodle.project.controller;

import com.moodle.project.dto.NewStudentDTO;
import com.moodle.project.entity.models.Student;
import com.moodle.project.service.StudentService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Es el que maneja las solicitudes de la API, al servicio para
 * que posteriormente podamos verla
 */
@RestController
@RequestMapping(path = "/**")
public class StudentController {
    private StudentService service;

//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping("/addStudent")
    public Student createStudent(@RequestBody NewStudentDTO studentDTO) {
        //studentDTO.setPassword(passwordEncoder().encode(studentDTO.getPassword()));
        return service.saveAlumno(studentDTO);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return service.getStudents();
    }

    @DeleteMapping("/student/{id}")
    public Student removeStudent(@PathVariable Long id) {
        return service.deleteStudent(id);

    }
}
