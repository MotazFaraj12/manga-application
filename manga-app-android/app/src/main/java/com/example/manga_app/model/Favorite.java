package com.example.manga_app.model;

import java.util.Date;

public class Favorite {

    private Long favoriteId;

    private User user;

    private Manga manga;

    private Date dateAdded;

    public Favorite() {
    }

    public Favorite(Long favoriteId, User user, Manga manga, Date dateAdded) {
        this.favoriteId = favoriteId;
        this.user = user;
        this.manga = manga;
        this.dateAdded = dateAdded;
    }

    public Long getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Long favoriteId) {
        this.favoriteId = favoriteId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Manga getManga() {
        return manga;
    }

    public void setManga(Manga manga) {
        this.manga = manga;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
