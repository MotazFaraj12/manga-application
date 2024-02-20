package com.example.MangaApp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "chapter_id")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chapter")
public class Chapter {
    @Id
    @Column(name = "chapter_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chapter_id;

    @Column(name = "chapter_name")
    private String chapter_name;

    @ManyToOne
    @JoinColumn(name = "manga_id")
    private Manga manga;

    @OneToMany(mappedBy = "chapter")
    private List<Page> pages = new ArrayList<>();
}
