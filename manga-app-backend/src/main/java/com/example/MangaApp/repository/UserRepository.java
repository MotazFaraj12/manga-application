package com.example.MangaApp.repository;

import com.example.MangaApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Modifying
    @Query(value = "update user set isVerified = true where email = ?1" , nativeQuery = true)
    void enableAppUser(String email);
}