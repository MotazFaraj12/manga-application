package com.example.MangaApp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "pageId")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "page")
public class Page {
    @Id
    @Column(name = "page_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pageId;

    @Column(name = "page_number")
    private Integer pageNumber;

    @Lob
    @Column(name = "page")
    private byte[] page;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;
}
