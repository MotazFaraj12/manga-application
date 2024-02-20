package com.example.MangaApp.security.service;

import com.example.MangaApp.mapper.AuthUserMapper;
import com.example.MangaApp.model.User;
import com.example.MangaApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;
    private final AuthUserMapper mapper;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(email);
        if (user.isPresent()) {
            return mapper.UserToAuthUser(user.get());
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }
    }



}

