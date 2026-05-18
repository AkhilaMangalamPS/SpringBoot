package org.example.phase1.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class ProdProfileService implements GreetingService{
    @Override
    public String greet() {
        return "Hello from PROD environment";
    }
}
