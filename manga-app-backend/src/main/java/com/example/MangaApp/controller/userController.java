package com.example.MangaApp.controller;

import com.example.MangaApp.DTO.AuthenticationRequest;
import com.example.MangaApp.DTO.RegisterRequest;
import com.example.MangaApp.mapper.AuthUserMapper;
import com.example.MangaApp.mapper.UserMapper;
import com.example.MangaApp.model.User;
import com.example.MangaApp.security.model.AuthUser;
import com.example.MangaApp.service.JwtService;
import com.example.MangaApp.service.userService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/User")
@RequiredArgsConstructor
public class userController {
    private static final Logger logger = LoggerFactory.getLogger(userController.class);
    private final UserMapper userMapper;
    private final userService userService;
    private final JwtService jwtService;
    private final AuthUserMapper authUserMapper;
    private AuthUser authUser;

    @PostMapping("/auth/register")
    public User saveUser(@RequestBody RegisterRequest request , HttpServletResponse response) {
        logger.debug("Attempting to create a user with email: {}", request.getEmail());
        User user = userMapper.RequerstToUser(request);
        user = userService.signup(user);
        Cookie jwtCookie = CreateJWToken(user);
        response.addCookie(jwtCookie);
        return user;
    }

    @PostMapping("/auth/login")
    public AuthUser authenticate(@RequestBody AuthenticationRequest request , HttpServletResponse response) {
        logger.debug("Attempting login validation for email: {}", request.getEmail());
        User user = userService.authenticate(request.getEmail() , request.getPassword());
        Cookie jwtCookie = CreateJWToken(user);
        response.addCookie(jwtCookie);
        return authUser;
    }

    @GetMapping( "/auth/register/confirm")
    public ModelAndView confirm(@RequestParam("token") String token) {
        return userService.confirmToken(token);
    }

    @PutMapping("/update/role/{email}")
    public ResponseEntity<String> ChangeRole(@PathVariable String email){
        return ResponseEntity.ok(userService.changeRole(email));
    }

    @GetMapping( "/getUser/{email}")
    public ModelAndView getUser(@PathVariable String email) {
        return userService.confirmToken(email);
    }

    private Cookie CreateJWToken(User user){
        //extra claims
        Map<String, Object> map = new HashMap<>();
        map.put("id" , user.getUser_id());
        map.put("Username", user.getUsername());
        map.put("role" , user.getRole());
        map.put("isDeleted" , user.getIsDeleted());
        map.put("isVerified" , user.getIsVerified());
        //extra claims
        authUser = authUserMapper.UserToAuthUser(user);
        String jwtToken = jwtService.generateToken(map,authUser);
        Cookie jwtCookie = new Cookie("jwtToken", jwtToken);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(24*60*60);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(true);
        return jwtCookie;
    }
}
