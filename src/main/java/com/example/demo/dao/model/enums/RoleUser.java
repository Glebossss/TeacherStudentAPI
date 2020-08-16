package com.example.demo.dao.model.enums;

public enum RoleUser {

    STUDENT, TEACHER, ADMIN;

    @Override
    public String toString() {
        return "ROLE_" + name();
    }
}
