package com.example.MangaApp.repository;

import com.example.MangaApp.model.Author;
import com.example.MangaApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
