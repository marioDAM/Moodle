package com.moodle.project.controller;


import com.moodle.project.dto.NewTeacherDTO;
import com.moodle.project.entity.models.Teacher;
import com.moodle.project.service.TeacherService;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */

@RestController
@RequestMapping(path = "/**")
public class TeacherController {
    private TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @PostMapping("/addTeacher")
    @ResponseStatus(HttpStatus.CREATED)
    public Teacher createStudent(@RequestBody NewTeacherDTO teacherDTO) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, teacherDTO.getPassword());
        teacherDTO.setPassword(hash);
        return service.saveTeacher(teacherDTO);
    }

    @GetMapping("/teachers")
    public List<Teacher> getAllStudents() {
        return service.getTeachers();
    }


}
