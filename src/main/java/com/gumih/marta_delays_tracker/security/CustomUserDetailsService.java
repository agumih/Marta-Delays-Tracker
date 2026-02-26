package com.gumih.marta_delays_tracker.security;

import com.gumih.marta_delays_tracker.entity.User;
import com.gumih.marta_delays_tracker.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // testing normalizing emails at login to streamline logins
        String normalizedEmail = email == null ? null : email.trim().toLowerCase();

        User user = userRepository.findByEmail(normalizedEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));



        //based on the user's role, this should show the appropriate dashboard (Admin or user)
        String authority = user.getRole(); // example:  "ROLE_USER"

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword()) // should get the bcrypt hash from DB
                .authorities(List.of(new SimpleGrantedAuthority(authority)))
                .build();
    }
}
