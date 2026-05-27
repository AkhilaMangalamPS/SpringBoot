package org.example.coursemanagementphase3.auth.controller;

import org.example.coursemanagementphase3.auth.dto.AuthRequest;
import org.example.coursemanagementphase3.security.jwt.JwtUtil;
import org.example.coursemanagementphase3.user.entity.User;
import org.example.coursemanagementphase3.user.repository.UserRepository;
import org.example.coursemanagementphase3.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        if(authentication.isAuthenticated()){
            return jwtUtil.generateToken(request.getUsername());
        }else{
            throw new RuntimeException("Invalid Credentials");
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody User user){
        //check if already username exists
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            return "Username already exists";
        }

//        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.registerNewUser(user);
        return "User Registered successfully";


    }
}
