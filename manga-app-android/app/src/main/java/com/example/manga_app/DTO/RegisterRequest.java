package com.example.manga_app.DTO;
import com.example.manga_app.model.role;
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private role role;
    private byte[] userImage;

    public RegisterRequest() {
    }
    public RegisterRequest(String username, String email, String password, com.example.manga_app.model.role role, byte[] userImage) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.userImage = userImage;
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

    public byte[] getUserImage() {
        return userImage;
    }

    public void setUserImage(byte[] userImage) {
        this.userImage = userImage;
    }

    public static boolean isValidName(String name) {
        return name.length() >= 3 && name.length() <= 20;
    }
}
