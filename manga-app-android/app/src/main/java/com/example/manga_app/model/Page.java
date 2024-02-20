package com.example.manga_app.model;


public class Page {
    private Long pageId;

    private Integer pageNumber;

    private byte[] page;

    private Chapter chapter;

    public Page() {
    }

    public Page(Long pageId, Integer pageNumber, byte[] page, Chapter chapter) {
        this.pageId = pageId;
        this.pageNumber = pageNumber;
        this.page = page;
        this.chapter = chapter;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public byte[] getPage() {
        return page;
    }

    public void setPage(byte[] page) {
        this.page = page;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
}
