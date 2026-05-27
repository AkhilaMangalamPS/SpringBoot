package org.example.coursemanagementphase3.user.service;

import org.example.coursemanagementphase3.user.entity.User;
import org.example.coursemanagementphase3.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if(user.getRole() == null || user.getRole().trim().isEmpty()){
            user.setRole("ROLE_STUDENT");
        }else if(!user.getRole().startsWith("ROLE_")){
            user.setRole("ROLE_"+ user.getRole().toUpperCase());
        }
        return userRepository.save(user);
    }
}
