package com.moodle.project.enums;

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

