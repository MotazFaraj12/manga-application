package com.example.manga_app.model;

public class User {

    private Long user_id;

    private String username;

    private String email;

    private String password;

    private role role;

    private Boolean isDeleted;

    private Boolean isVerified;

    public User() {
    }

    public User(Long user_id, String username, String email, String password, com.example.manga_app.model.role role, Boolean isDeleted, Boolean isVerified) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isDeleted = isDeleted;
        this.isVerified = isVerified;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
