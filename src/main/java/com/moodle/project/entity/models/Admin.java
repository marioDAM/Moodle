package com.moodle.project.entity.models;

import com.moodle.project.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity

@Data
public class Admin extends User {

    private Date entryDate;
    private Date modificationDate;



    public Admin() {
    }


}
