package com.example.manga_app.model;

public class AuthUser{
    private Long id;
    private String username;
    private String email;
    private role role;
    private Boolean isDeleted;
    private Boolean isVerified;

    public AuthUser() {
    }

    public AuthUser(Long id, String username, String email, com.example.manga_app.model.role role, Boolean isDeleted, Boolean isVerified) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.isDeleted = isDeleted;
        this.isVerified = isVerified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public com.example.manga_app.model.role getRole() {
        return role;
    }

    public void setRole(com.example.manga_app.model.role role) {
        this.role = role;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }
}
