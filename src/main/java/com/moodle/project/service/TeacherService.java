package com.moodle.project.service;

import com.moodle.project.dto.NewTeacherDTO;
import com.moodle.project.entity.models.Teacher;
import com.moodle.project.mapper.NewTeacherDTOToTeacher;
import com.moodle.project.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository repository;
    private final NewTeacherDTOToTeacher mapper;

    public TeacherService(TeacherRepository repository, NewTeacherDTOToTeacher mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Teacher saveTeacher(NewTeacherDTO dto) {
        Teacher teacher = mapper.map(dto);
        return repository.save(teacher);
    }
    public List<Teacher> getTeachers(){
        return repository.findAll();
    }

    public Teacher getTeacherById(Long id){
        return repository.getReferenceById(id);
    }
}
