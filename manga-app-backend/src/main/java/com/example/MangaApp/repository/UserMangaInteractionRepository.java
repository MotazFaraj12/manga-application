package com.example.MangaApp.repository;

import com.example.MangaApp.model.UserMangaInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMangaInteractionRepository extends JpaRepository<UserMangaInteraction, Long> {
}
