package com.springexample.springsecuritydemo.model.enam;

import com.springexample.springsecuritydemo.model.enam.Permission;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.DEVELOPERS_READ,
            Permission.PROJECTS_READ,
            Permission.PROJECTS_WRITE,
            Permission.DEPARTMENTS_READ)),
    ADMIN(Set.of(Permission.DEVELOPERS_READ,
            Permission.DEVELOPERS_WRITE,
            Permission.PROJECTS_READ,
            Permission.PROJECTS_WRITE,
            Permission.DEPARTMENTS_READ,
            Permission.DEPARTMENTS_WRITE));

    private final Set<Permission> permissions;


    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
