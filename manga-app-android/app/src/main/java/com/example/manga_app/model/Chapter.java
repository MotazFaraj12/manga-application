package com.example.manga_app.model;
import com.example.manga_app.model.*;

import java.util.ArrayList;
import java.util.List;

public class Chapter {
    private Long chapter_id;
    private String chapter_name;
    private Manga manga;
    private List<Page> pages = new ArrayList<>();
    public Chapter() {
    }
    public Chapter(Long chapter_id, String chapter_name, Manga manga, List<Page> pages) {
        this.chapter_id = chapter_id;
        this.chapter_name = chapter_name;
        this.manga = manga;
        this.pages = pages;
    }

    public Long getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(Long chapter_id) {
        this.chapter_id = chapter_id;
    }

    public String getChapter_name() {
        return chapter_name;
    }

    public void setChapter_name(String chapter_name) {
        this.chapter_name = chapter_name;
    }

    public Manga getManga() {
        return manga;
    }

    public void setManga(Manga manga) {
        this.manga = manga;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }
}
