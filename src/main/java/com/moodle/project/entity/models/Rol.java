package com.moodle.project.entity.models;

import com.moodle.project.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    //Se indica que va a ser un Enum de tipo String
    @Enumerated(EnumType.STRING)
    private Role rolNombre;

    public Rol() {
    }

    public Rol(@NotNull Role rolNombre) {
        this.rolNombre = rolNombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(Role rolNombre) {
        this.rolNombre = rolNombre;
    }
}