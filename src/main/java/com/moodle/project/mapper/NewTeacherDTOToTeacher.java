package com.moodle.project.mapper;


import com.moodle.project.dto.NewTeacherDTO;

import com.moodle.project.entity.models.Teacher;
import org.springframework.stereotype.Component;

@Component
public class NewTeacherDTOToTeacher implements IMapper<NewTeacherDTO, Teacher> {

    @Override
    public Teacher map(NewTeacherDTO in) {
        Teacher teacher = new Teacher();
        teacher.setId(in.getId());
        teacher.setName(in.getName());
        teacher.setEmail(in.getEmail());
        teacher.setCourse(in.getCourse());
        teacher.setRoles(in.getRoles());
        teacher.setPassword(in.getPassword());
        teacher.setUsername(in.getUsername());
        teacher.setSurname(in.getSurname());
        teacher.setAvatar(in.getAvatar());
        teacher.setDischargedBy(in.getDischargedBy());
        teacher.setEntryDate(in.getEntryDate());
        teacher.setLeavingDate(in.getLeavingDate());
        return teacher;
    }
}
