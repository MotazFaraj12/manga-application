package com.example.MangaApp.repository;

import com.example.MangaApp.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    // Add custom query methods if needed
}
