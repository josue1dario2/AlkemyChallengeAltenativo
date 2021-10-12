package com.proyect.alkemy.auth.controller;

import com.proyect.alkemy.auth.dto.AuthenticationRequest;
import com.proyect.alkemy.auth.dto.AuthenticationResponse;
import com.proyect.alkemy.auth.service.JwtUtils;
import com.proyect.alkemy.auth.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsCustomService userDetailsCustomService;

    @Autowired
    private JwtUtils jwtTokenUtil;

    @PostMapping
    public ResponseEntity<?> singIn(@RequestBody AuthenticationRequest authRequest)throws Exception{

        UserDetails userDetails;//new
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
            userDetails = (UserDetails) auth.getPrincipal();
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password",e);
        }
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
