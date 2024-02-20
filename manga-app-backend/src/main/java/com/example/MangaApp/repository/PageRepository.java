package com.example.MangaApp.repository;

import com.example.MangaApp.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository  extends JpaRepository<Page, Long> {
}
