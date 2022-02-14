package com.ikolanovic.restapi.controllers;

import com.ikolanovic.restapi.exceptions.RequestFailedException;
import com.ikolanovic.restapi.models.security.AuthenticationRequest;
import com.ikolanovic.restapi.models.security.AuthenticationResponse;
import com.ikolanovic.restapi.models.security.MyUserDetails;
import com.ikolanovic.restapi.repos.UserRepository;
import com.ikolanovic.restapi.security.isAdmin;
import com.ikolanovic.restapi.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rit-library")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @PostMapping(value = "/auth")
    public ResponseEntity<?> createAuthToken(
            @RequestBody AuthenticationRequest authenticationRequest) {

        userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow(() -> new RequestFailedException(HttpStatus.CONFLICT, "User does not exist."));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(), authenticationRequest.getPassword()));

        MyUserDetails userDetails =
                (MyUserDetails) userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(
                new AuthenticationResponse(
                        userDetails.getUser(),
                        jwt,
                        jwtUtil.extractExpiration(jwt)));
    }

}
