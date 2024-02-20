package com.example.manga_app.model;

public class Author {
    private Long authorId;
    private String authorName;
    private String biography;

    public Author() {
    }

    public Author(Long authorId, String authorName, String biography) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.biography = biography;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
