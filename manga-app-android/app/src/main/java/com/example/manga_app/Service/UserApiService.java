package com.example.manga_app.Service;

import com.example.manga_app.DTO.AuthenticationRequest;
import com.example.manga_app.DTO.RegisterRequest;
import com.example.manga_app.model.AuthUser;
import com.example.manga_app.model.User;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Body;
import okhttp3.ResponseBody;

public interface UserApiService {
    @POST("/User/auth/register")
    Call<User> saveUser(@Body RegisterRequest registerRequest);

    @POST("/User/auth/login")
    Call<AuthUser> authenticate(@Body AuthenticationRequest authenticationRequest);
}
