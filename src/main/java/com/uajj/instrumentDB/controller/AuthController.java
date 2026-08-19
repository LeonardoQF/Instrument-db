package com.uajj.instrumentDB.controller;

import com.uajj.instrumentDB.model.entities.User;
import com.uajj.instrumentDB.repository.UserRepository;
import com.uajj.instrumentDB.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@AllArgsConstructor
public class AuthController implements GenericController {

    private AuthenticationManager authenticationManager;
    private UserRepository repository; //Change to service later
    private PasswordEncoder encoder;
    private JwtUtil jwtUtil;

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody User user) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String token = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (repository.existsByUsername(user.getUsername()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with the given username already exists.");

        User newUser = new User(user.getUsername(), encoder.encode(user.getPassword()));

        repository.save(newUser);
        return ResponseEntity.ok().body("User registered successfully.");
    }
}
