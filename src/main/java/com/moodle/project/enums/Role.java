package com.moodle.project.enums;

//Clase utilizada para poder diferenciar a los usuarios con su función en la aplicación
public enum Role {

    ADMIN("admin"),
    STUDE("student"),

    TEACH("teacher");


    private final String value;

    Role(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

