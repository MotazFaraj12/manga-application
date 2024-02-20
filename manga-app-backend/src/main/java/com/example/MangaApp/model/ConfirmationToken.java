package com.example.MangaApp.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "confirmationtoken")
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long token_id;

    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    private Date create_date;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expiration_data;

    @Temporal(TemporalType.TIMESTAMP)
    private Date confirmation_date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
