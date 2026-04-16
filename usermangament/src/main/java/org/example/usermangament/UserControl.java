package org.example.usermangament;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserControl {

    private UserService service;
    public UserControl(UserService service){
        this.service = service;
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        return service.addUser(user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        return service.deleteUser(id);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable int id){
        return service.getUserById(id);
    }


}
