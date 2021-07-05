package com.springexample.springsecuritydemo.model.enam;

public enum Permission {
    DEVELOPERS_READ("developers:read"),
    DEVELOPERS_WRITE("developers:write"),
    PROJECTS_READ("projects:read"),
    PROJECTS_WRITE("projects:write"),
    DEPARTMENTS_READ("departments:read"),
    DEPARTMENTS_WRITE("departments:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
