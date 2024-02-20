package com.example.MangaApp.repository;

import com.example.MangaApp.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.Optional;
@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long> {
    Optional<ConfirmationToken> findByToken(String token);
    @Modifying
    @Query(value = "update confirmationtoken set confirmation_date =?2 where token=?1\n" , nativeQuery = true)
    void updateConfirmedAt(String token, Date confirmedAt);
}
