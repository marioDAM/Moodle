package com.moodle.project.entity.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity

@Data
public class Admin extends User {

    private Date entryDate;
    private Date modificationDate;



    public Admin() {
    }


}
