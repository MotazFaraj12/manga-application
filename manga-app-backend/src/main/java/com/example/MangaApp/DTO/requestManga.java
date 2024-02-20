package com.example.MangaApp.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class requestManga {
    private String title;
    private Long author_id;
    private Long genre_id;
    private Integer publicationYear;
    private String description;
    private String coverImageUrl;
}
