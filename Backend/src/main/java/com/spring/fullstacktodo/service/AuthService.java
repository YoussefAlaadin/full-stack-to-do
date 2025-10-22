package com.spring.fullstacktodo.service;

import com.spring.fullstacktodo.dto.AuthResponse;
import com.spring.fullstacktodo.dto.LoginRequest;
import com.spring.fullstacktodo.dto.RegisterRequest;
import com.spring.fullstacktodo.model.User;
import com.spring.fullstacktodo.repository.UserRepo;
import com.spring.fullstacktodo.model.CustomUserDetails;
import com.spring.fullstacktodo.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public AuthResponse register(RegisterRequest request) {
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .role(User.Role.USER)
                .build();
        User savedUser = userRepo.save(user);

        String token = jwtUtil.generateAccessToken(request.getEmail(), savedUser.getRole().name());
        return new AuthResponse(
                token

        );
    }

    public AuthResponse login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(loginRequest.getEmail());
            String token = jwtUtil.generateAccessToken(userDetails.getUsername(), userDetails.getAuthorities().toString()); // email + role
                return new AuthResponse(token);
        } catch (AuthenticationException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }
    }
}

