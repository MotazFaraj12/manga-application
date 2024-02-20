package com.example.MangaApp.mapper;

import com.example.MangaApp.model.User;
import com.example.MangaApp.security.model.AuthUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthUserMapper {
    AuthUser UserToAuthUser(User user);
}
