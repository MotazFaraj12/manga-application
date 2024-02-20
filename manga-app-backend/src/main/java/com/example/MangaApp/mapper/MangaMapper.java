package com.example.MangaApp.mapper;

import com.example.MangaApp.DTO.requestManga;
import com.example.MangaApp.model.Author;
import com.example.MangaApp.model.Genre;
import com.example.MangaApp.model.Manga;
import com.example.MangaApp.repository.AuthorRepository;
import com.example.MangaApp.repository.GenreRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class MangaMapper {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;

    @Mapping(target = "author", expression = "java(getAuthorById(request.getAuthor_id()))")
    @Mapping(target = "genre", expression = "java(getGenreById(request.getGenre_id()))")
    public abstract Manga requestMangaToManga(requestManga request);

    protected Author getAuthorById(Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Author not found with id: " + authorId));
    }

    protected Genre getGenreById(Long genreId) {
        return genreRepository.findById(genreId)
                .orElseThrow(() -> new IllegalArgumentException("Genre not found with id: " + genreId));
    }

}
