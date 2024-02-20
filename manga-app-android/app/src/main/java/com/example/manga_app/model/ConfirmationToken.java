package com.example.manga_app.model;

import java.util.Date;

public class ConfirmationToken {
    private Long token_id;

    private String token;

    private Date create_date;

    private Date expiration_data;

    private Date confirmation_date;

    private User user;

    public ConfirmationToken() {
    }

    public ConfirmationToken(Long token_id, String token, Date create_date, Date expiration_data, Date confirmation_date, User user) {
        this.token_id = token_id;
        this.token = token;
        this.create_date = create_date;
        this.expiration_data = expiration_data;
        this.confirmation_date = confirmation_date;
        this.user = user;
    }

    public Long getToken_id() {
        return token_id;
    }

    public void setToken_id(Long token_id) {
        this.token_id = token_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getExpiration_data() {
        return expiration_data;
    }

    public void setExpiration_data(Date expiration_data) {
        this.expiration_data = expiration_data;
    }

    public Date getConfirmation_date() {
        return confirmation_date;
    }

    public void setConfirmation_date(Date confirmation_date) {
        this.confirmation_date = confirmation_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
