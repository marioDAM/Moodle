package com.moodle.project.entity.models;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Student extends User {
    private String dni;
    private int note;

    private Date dateCompleted;

    private boolean isTerminated;

    private LocalDateTime entryDate;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date leavingDate;
    private String dischargedBy;
    private String modifiedBy;

    private String subjects;

    private String avatar;

    public Student() {

    }

    @ManyToMany
    private List<Teacher> teachers;


}
