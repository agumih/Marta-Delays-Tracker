package com.gumih.marta_delays_tracker.service;

import com.gumih.marta_delays_tracker.dto.LoginRequest;
import com.gumih.marta_delays_tracker.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
