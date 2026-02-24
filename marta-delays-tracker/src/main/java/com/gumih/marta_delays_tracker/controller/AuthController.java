package com.gumih.marta_delays_tracker.controller;


import com.gumih.marta_delays_tracker.dto.LoginRequest;
import com.gumih.marta_delays_tracker.dto.LoginResponse;
import com.gumih.marta_delays_tracker.dto.RegisterRequest;
import com.gumih.marta_delays_tracker.entity.User;
import com.gumih.marta_delays_tracker.exception.DuplicateResourceException;
import com.gumih.marta_delays_tracker.repository.UserRepository;
import com.gumih.marta_delays_tracker.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthService authService;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          AuthService authService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already registered: " + request.getEmail());
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // will update later to use BCrypt
        user.setRole("ROLE_USER");

        userRepository.save(user);

        return "User registered successfully";
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }


//    @PostMapping
//    public User createUser(@RequestBody User theUser){
//        User dbUser = userRepository.save(theUser);
//
//        return dbUser;
//    }


}
