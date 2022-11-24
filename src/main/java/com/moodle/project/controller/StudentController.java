package com.moodle.project.controller;

import com.moodle.project.dto.NewStudentDTO;
import com.moodle.project.entity.models.Student;
import com.moodle.project.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase controladora en referencia al alumno donde definiremos las rutas
 * que veremos en el navegador
 *
 * @author Mario Valverde
 */
@RestController
@RequestMapping(path = "/students")
public class StudentController {
    /**
     * Campo de la clase encargado de llamar al servicio para tratar los datos con la base de datos
     */
    private StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    /**
     * Método encargado de añadir un alumno en la rua que se encuentra en @PostMapping
     *
     * @param studentDTO objeto de transferencia que mapeamos en el @service para convertirlo en un alumno
     * @return el alumno insertado en la base de datos
     */

    @PostMapping("/addStudent")
    public Student createStudent(@RequestBody NewStudentDTO studentDTO) {
        //studentDTO.setPassword(passwordEncoder().encode(studentDTO.getPassword()));
        return service.saveAlumno(studentDTO);
    }

    /**
     * Método encargado de devolver todos los alumnos añadadidos
     *
     * @return el alumno insertado en la base de datos
     */
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return service.getStudents();
    }

    /**
     * Método encargado de borrar un alumno a través de su id
     *
     * @param id identifica al alumno que va a ser borrado
     * @return el alumno que ha si sido borrado de la aplicacioón
     */

    @DeleteMapping("/student/{id}")
    public Student removeStudent(@PathVariable Long id) {
        return service.deleteStudent(id);

    }
}
