package com.moodle.project.controller;

import com.moodle.project.dto.NewStudentDTO;
import com.moodle.project.entity.models.Student;
import com.moodle.project.service.StudentService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Es el que maneja las solicitudes de la API, al servicio para
 * que posteriormente podamos verla
 */
@RestController
@RequestMapping(path = "/**")
public class StudentController {
    private StudentService service;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public StudentController(StudentService service) {
        this.service = service;

    }


    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("STUDE");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

    @PostMapping("/addStudent")
    public Student createStudent(@RequestBody NewStudentDTO studentDTO) {
        studentDTO.setPassword(passwordEncoder().encode(studentDTO.getPassword()));
        return service.saveAlumno(studentDTO);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return service.getStudents();
    }

    @GetMapping("/student/{id}")
    public Student removeStudent(@PathVariable Long id) {
        return service.deleteStudent(id);

    }
}
