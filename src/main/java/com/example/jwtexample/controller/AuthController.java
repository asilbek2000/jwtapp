package com.example.jwtexample.controller;


import com.example.jwtexample.dto.LoginDTO;
import com.example.jwtexample.entity.User;
import com.example.jwtexample.security.CurrentUser;
import com.example.jwtexample.security.JwtProvider;
import com.example.jwtexample.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    AuthService authService;

    @Autowired
    JwtProvider jwtProvider;


    @PostMapping("login")
    public HttpEntity<?> login(@RequestBody LoginDTO dto) {
        String token = jwtProvider.generateToken(dto.getUserName());
        return ResponseEntity.ok().body(token);
    }

    @GetMapping("/me")
    public HttpEntity<?> getMe(@CurrentUser User user) { //Parametr
        return ResponseEntity.ok().body("Mana" + user);
    }

}
