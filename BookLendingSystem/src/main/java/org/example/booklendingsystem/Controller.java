package org.example.booklendingsystem;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class Controller {
    private final UserService service;

    public Controller(UserService service) {
        this.service = service;
    }

    @PostMapping
    public String addUser(@RequestBody User user){
        service.addUser(user);
        return "User added successfully";
    }
}
