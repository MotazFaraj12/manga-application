package com.example.MangaApp.mapper;

import com.example.MangaApp.DTO.RegisterRequest;
import com.example.MangaApp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "isDeleted" , constant = "false")
    @Mapping(target = "isVerified", constant = "false")
    User RequerstToUser(RegisterRequest request);
}
