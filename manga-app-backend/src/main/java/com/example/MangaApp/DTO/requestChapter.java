package com.example.MangaApp.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class requestChapter {
    private String chapter_name;
    private String manga_name;
}
