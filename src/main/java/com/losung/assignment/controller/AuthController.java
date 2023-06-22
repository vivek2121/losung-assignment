package com.losung.assignment.controller;

import com.losung.assignment.dto.request.AuthRequest;
import com.losung.assignment.dto.response.AuthResponse;
import com.losung.assignment.service.CustomUserDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Authentication API")
public class AuthController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/userLogin")
    @ApiOperation("get jwt token")
    public ResponseEntity<AuthResponse> userLogin(@RequestBody AuthRequest authRequest) throws Exception {
        return ResponseEntity.ok(customUserDetailsService.generateToken(authRequest));
    }
}
