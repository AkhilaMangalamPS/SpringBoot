package org.example.phase1.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DevProfileService implements GreetingService{


    @Override
    public String greet() {
        return "Hello from DEV environment";
    }
}
