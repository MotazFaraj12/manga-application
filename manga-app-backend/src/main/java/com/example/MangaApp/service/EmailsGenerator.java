package com.example.MangaApp.service;

import com.example.MangaApp.model.ConfirmationToken;
import com.example.MangaApp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailsGenerator {
    private final EmailService emailService;
    public void sendConfirmationEmail(User User, ConfirmationToken confirmationToken){
        String title = "Confirm your email";
        String link = "http://localhost:8085/User/auth/register/confirm?token=" + confirmationToken.getToken();
        String massage = "Thank you for registering. Please click on the below link to activate your account:";
        String button = "Activate Now";
        String email = User.getEmail();
        emailService.send(email, title, User.getUsername(), link, massage, 3, button);
    }
}
