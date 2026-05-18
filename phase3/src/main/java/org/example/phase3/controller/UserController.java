package org.example.phase3.controller;

import jakarta.validation.Valid;
import org.example.phase3.dto.UserDto;
import org.example.phase3.entity.User;
import org.example.phase3.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto createUser(@RequestBody @Valid UserDto dto){
        return userService.createUser(dto);
    }

    @GetMapping
    public Page<UserDto> getUsers(@RequestParam int page, @RequestParam int size){
        return userService.getAllUsers(page,size);
    }

}
