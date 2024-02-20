package com.example.MangaApp.DTO;

import com.example.MangaApp.model.role;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private role role;
    private byte[] userImage;
}
