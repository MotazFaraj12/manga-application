package com.example.MangaApp.service;
import com.example.MangaApp.model.ConfirmationToken;
import com.example.MangaApp.model.role;
import com.example.MangaApp.model.User;
import com.example.MangaApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class userService {
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailsGenerator emailsGenerator;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    public User signup(User user) {
        String email = user.getEmail();
        Optional<User> employeeOptional = userRepository.findByEmail(email);
        if(employeeOptional.isPresent()){
            throw new IllegalStateException("email already exists");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user = userRepository.save(user);
        ConfirmationToken confirmationToken = confirmationTokenService.createConfirmationToken(user);
        emailsGenerator.sendConfirmationEmail(user, confirmationToken);
        return user;
    }

    public User authenticate(String email , String password){
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            if(encoder.matches(password , user.getPassword())){
                return user;
            }
            else {
               throw new IllegalStateException("Wrong password");
            }
        }else {
            throw new IllegalStateException("User dose not exist");
        }
    }

    public String changeRole(String email){
        Optional<User> employee = userRepository.findByEmail(email);
        if(employee.isPresent()){
            User user = employee.get();
            user.setRole(role.admin);
            userRepository.save(user);
            return "success";
        }else {
            return "User dose not exists";
        }
    }

    @Transactional
    public ModelAndView confirmToken(String token) {
        ModelAndView modelAndView = new ModelAndView();
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("Token not found"));

        if (confirmationToken.getConfirmation_date() != null) {
            throw new IllegalStateException("Email already confirmed");
        }
        Date expiredAt = confirmationToken.getExpiration_data();
        if (expiredAt.before(new Date())) {
            throw new IllegalStateException("Token expired");
        }
        confirmationTokenService.setConfirmedAt(token);
        userRepository.enableAppUser(confirmationToken.getUser().getEmail());
        modelAndView.setViewName("confirmationPage.html");
        return modelAndView;
    }
}
