package com.springexample.springsecuritydemo.model.enam;

public enum Permission {
    DEVELOPERS_READ("developers:read"),
    DEVELOPERS_WRITE("developers:write"),
    PROJECTS_READ("projects:read"),
    PROJECT_WRITE("projects:write"),
    DEPARTMENT_READ("department:read"),
    DEPARTMENT_WRITE("department:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
