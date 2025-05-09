package org.ncu.music_festival_scheduler.security.controller;

import org.ncu.music_festival_scheduler.security.config.JwtUtil;
import org.ncu.music_festival_scheduler.security.entity.ApplicationUser;
import org.ncu.music_festival_scheduler.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authManager, UserService userService, JwtUtil jwtUtil) {
        this.authManager = authManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {
        ApplicationUser user = userService.registerUser(request.getEmail(), request.getPassword(), request.getRole());
        return "User registered with email: " + user.getEmailAddress();
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails userDetails = userService.loadUserByUsername(request.getEmail());
        return jwtUtil.generateToken(userDetails);
    }
}
