package com.example.manga_app.model;
import com.example.manga_app.model.Genre;

import java.util.ArrayList;
import java.util.List;

public class Manga {

    private Long mangaId;

    private String title;

    private Author author;

    private Genre genre;

    private Integer publicationYear;

    private String description;

    private String coverImageUrl;

    private List<Chapter> Chapters = new ArrayList<>();

    public Manga() {
    }

    public Manga(Long mangaId, String title, Author author, Genre genre, Integer publicationYear, String description, String coverImageUrl, List<Chapter> chapters) {
        this.mangaId = mangaId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.description = description;
        this.coverImageUrl = coverImageUrl;
        Chapters = chapters;
    }

    public Long getMangaId() {
        return mangaId;
    }

    public void setMangaId(Long mangaId) {
        this.mangaId = mangaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public List<Chapter> getChapters() {
        return Chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        Chapters = chapters;
    }
}
