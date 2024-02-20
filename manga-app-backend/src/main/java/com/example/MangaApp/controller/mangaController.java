package com.example.MangaApp.controller;

import com.example.MangaApp.DTO.requestManga;
import com.example.MangaApp.mapper.MangaMapper;
import com.example.MangaApp.model.Chapter;
import com.example.MangaApp.model.Manga;
import com.example.MangaApp.service.mangaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class mangaController {
    private final mangaService mangaService;
    private final MangaMapper mangaMapper;

    @GetMapping("Manga/Home")
    public ResponseEntity<List<Manga>> getAllMangas(){
        return ResponseEntity.status(HttpStatus.OK).body(mangaService.getAllMangas());
    }

    @GetMapping("Manga/{title}")
    public ResponseEntity<Manga> showManga(@PathVariable String title){
        return ResponseEntity.status(HttpStatus.OK).body(mangaService.showManga(title));
    }

    @PostMapping("Manga/new")
    public ResponseEntity<Manga> addManga(@RequestBody requestManga request){
        Manga manga = mangaMapper.requestMangaToManga(request);
        manga = mangaService.addManga(manga);
        return ResponseEntity.status(HttpStatus.OK).body(manga);
    }

    @PostMapping("chapter/new")
    public ResponseEntity<Chapter> addChapter(@RequestParam("files") MultipartFile[] files,
                                              @RequestParam("chapter name") String chapter_name,
                                              @RequestParam("manga name") String manga_name){
        Chapter chapter = mangaService.createChapter(files, chapter_name, manga_name);
        return ResponseEntity.status(HttpStatus.OK).body(chapter);
    }

}
