package com.losung.assignment.service;

import com.losung.assignment.dto.request.AuthRequest;
import com.losung.assignment.dto.response.AuthResponse;
import com.losung.assignment.model.ContactDetails;
import com.losung.assignment.repository.ContactRepository;
import com.losung.assignment.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ContactDetails contactDetails = contactRepository.findByFirstNameContainingIgnoreCase(username).get(0);
        if (contactDetails != null) {
            return new org.springframework.security.core.userdetails.User("Vivek", "Singh123", new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User Not Found");
        }
    }

    public AuthResponse generateToken(AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));

        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        UserDetails userToken = this.loadUserByUsername(authRequest.getUserName());
        jwtUtil.generateToken(userToken);
        return AuthResponse.builder()
                .token(jwtUtil.generateToken(userToken))
                .build();

    }
}
