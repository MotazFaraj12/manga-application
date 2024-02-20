package com.example.MangaApp.service;

import com.example.MangaApp.model.ConfirmationToken;
import com.example.MangaApp.model.User;
import com.example.MangaApp.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository repository;

    public ConfirmationToken createConfirmationToken(User user) {

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = ConfirmationToken
                .builder()
                .token(token)
                .create_date(new Date())
                .expiration_data(new Date(System.currentTimeMillis() + (3 * 60 * 1000)))
                .user(user)
                .build();

        return repository.save(confirmationToken);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return repository.findByToken(token);
    }

    @Transactional
    public void setConfirmedAt(String token) {
        repository.updateConfirmedAt(token, new Date());
    }
}
