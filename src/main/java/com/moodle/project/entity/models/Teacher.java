package com.moodle.project.entity.models;

import com.moodle.project.entity.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Teacher extends User {

    private String course;
    private String avatar;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryDate;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date leavingDate;

    private String dischargedBy;

    private String modifiedBY;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Student> students;

}
