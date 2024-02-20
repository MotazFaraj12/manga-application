package com.example.MangaApp.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "interactionId")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Usermangainteraction")
public class UserMangaInteraction {
    @Id
    @Column(name = "interaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interactionId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "manga_id")
    private Manga manga;

    @Column(name = "interaction_type")
    private String interactionType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "interaction_date")
    private Date interactionDate;
}
