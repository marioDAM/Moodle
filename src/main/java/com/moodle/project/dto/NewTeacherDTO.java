package com.moodle.project.dto;


import com.moodle.project.enums.Role;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class NewTeacherDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String course;
    private String avatar;
    private Date entryDate;
    private String username;
    private String surname;

    private Date leavingDate;

    private String dischargedBy;



    private Set<Role> roles;
}
