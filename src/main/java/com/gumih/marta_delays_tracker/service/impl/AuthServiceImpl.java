package com.gumih.marta_delays_tracker.service.impl;

import com.gumih.marta_delays_tracker.dto.LoginRequest;
import com.gumih.marta_delays_tracker.dto.LoginResponse;
import com.gumih.marta_delays_tracker.entity.User;
import com.gumih.marta_delays_tracker.exception.InvalidCredentialsException;
import com.gumih.marta_delays_tracker.repository.UserRepository;
import com.gumih.marta_delays_tracker.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AuthServiceImpl implements AuthService {


    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {

        //find the user by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));

        //check Bcrypt password
        boolean ok = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if(!ok) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        return new LoginResponse(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                "Login successful"
        );
    }
}
