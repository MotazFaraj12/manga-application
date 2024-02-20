package com.example.MangaApp.service;

import com.example.MangaApp.model.Chapter;
import com.example.MangaApp.model.Manga;
import com.example.MangaApp.model.Page;
import com.example.MangaApp.repository.ChapterRepository;
import com.example.MangaApp.repository.MangaRepository;
import com.example.MangaApp.repository.PageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class mangaService {
    private final MangaRepository mangaRepository;
    private final ChapterRepository chapterRepository;
    private final PageRepository pageRepository;

    public Manga addManga(Manga manga){
        manga = mangaRepository.save(manga);
        return manga;
    }

    public List<Manga> getAllMangas(){
        return mangaRepository.findAll();
    }

    public Manga showManga(String title){
        return mangaRepository.findMangaByTitle(title).orElseThrow(() -> new IllegalArgumentException("Manga not found with name: " + title));
    }

    public Chapter createChapter(MultipartFile[] files, String chapter_name , String manga_name) {
        Manga manga = mangaRepository.findMangaByTitle(manga_name)
                .orElseThrow(() -> new IllegalArgumentException("Manga not found with name: " + manga_name));

        Chapter chapter = Chapter.builder()
                .chapter_name(chapter_name)
                .manga(manga)
                .build();

        chapter = chapterRepository.save(chapter);

        int i = 1;
        for (MultipartFile file : files) {
            try {
                byte[] imageData = file.getBytes();
                Page page = Page.builder()
                        .pageNumber(i)
                        .page(imageData)
                        .chapter(chapter)
                        .build();
                pageRepository.save(page);
                i++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return chapter;
    }
}
