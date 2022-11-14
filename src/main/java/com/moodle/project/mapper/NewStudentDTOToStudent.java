package com.moodle.project.mapper;

import com.moodle.project.dto.NewStudentDTO;
import com.moodle.project.entity.models.Student;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NewStudentDTOToStudent implements IMapper<NewStudentDTO, Student> {

    @Override
    public Student map(NewStudentDTO in) {
        Student student = new Student();
        student.setName(in.getName());
        student.setSurname(in.getSurname());
        student.setEmail(in.getEmail());
        student.setAvatar(in.getAvatar());
        student.setDni(in.getDni());
        student.setDischargedBy(in.getDischargedBy());
        student.setModifiedBy(in.getModifiedBy());
        student.setUsername(in.getUsername());
        student.setSurname(in.getSurname());
        student.setRoles(in.getRoles());
        student.setPassword(in.getPassword());
        student.setSubjects(in.getSubjects());
        student.setEntryDate(LocalDateTime.now());
        return student;
    }
}
