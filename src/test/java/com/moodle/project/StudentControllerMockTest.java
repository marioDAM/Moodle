package com.moodle.project;

import com.moodle.project.controller.StudentController;
import com.moodle.project.entity.models.Student;
import com.moodle.project.repository.StudentRepository;
import com.moodle.project.service.StudentService;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class StudentControllerMockTest {
    @MockBean
    private final StudentRepository repository;

    @MockBean
    private final StudentService storageService;
    @InjectMocks
    private StudentController controller;


    @Autowired
    public StudentControllerMockTest(StudentRepository repository, StudentService storageService) {
        this.repository = repository;
        this.storageService = storageService;
    }
}
