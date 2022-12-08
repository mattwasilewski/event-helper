package com.codecool.CodeCoolProjectGrande.user;

public enum UserType {
    USER("user"),
    MODERATOR("moderator"),
    ADMIN("admin");

    private final String role;

    UserType(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
