package org.example.week1;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private EmailService emailService;
    public UserService(EmailService emailService){
        this.emailService = emailService;
        System.out.println("User Created");
    }
}
