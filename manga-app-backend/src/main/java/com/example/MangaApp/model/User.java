package com.example.MangaApp.model;
import com.example.MangaApp.model.role;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "user_id")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private com.example.MangaApp.model.role role;

    @Lob
    @Column(name = "user_image")
    private byte[] userImage;

    @Column(name = "isDeleted")
    private Boolean isDeleted;

    @Column(name = "isVerified")
    private Boolean isVerified;


}
