package com.moodle.project.dto;

import com.moodle.project.enums.Role;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class NewStudentDTO {
    private String name;
    private String surname;
    private String username;
    private String dni;
    private String email;
    private String password;
    private String dischargedBy;
    private String modifiedBy;
    private String subjects;
    private String avatar;
    private Set<Role> roles;
    private Date entryDate;

}
