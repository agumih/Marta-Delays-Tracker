package com.gumih.marta_delays_tracker.controller;

import com.gumih.marta_delays_tracker.dto.RegisterForm;
import com.gumih.marta_delays_tracker.entity.User;
import com.gumih.marta_delays_tracker.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthWebController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthWebController(UserRepository userRepository,
                             PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid @ModelAttribute("registerForm") RegisterForm form,
                             BindingResult bindingResult,
                             Model model) {

        // 1) Bean validation errors
        if (bindingResult.hasErrors()) {
            return "register";
        }

        // 2) Password confirmation
        if (!form.getPassword().equals(form.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "passwordMismatch", "Passwords do not match");
            return "register";
        }

        // 3) Normalize email
        String email = form.getEmail().trim().toLowerCase();

        // 4) Duplicate email check
        if (userRepository.existsByEmail(email)) {
            bindingResult.rejectValue("email", "emailExists", "Email is already registered");
            return "register";
        }

        // 5) Create user
        User user = new User();
        user.setFirstName(form.getFirstName().trim());
        user.setLastName(form.getLastName().trim());
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setRole("ROLE_USER");

        userRepository.save(user);

        // 6) Redirect to login page (with success message)
        return "redirect:/login?registered";
    }
}