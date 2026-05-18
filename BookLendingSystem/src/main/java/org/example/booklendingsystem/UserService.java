package org.example.booklendingsystem;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<User> userList = new ArrayList<>();
    public void addUser(User user){
        userList.add(user);
    }

    public User getUser(int id){
        return userList.stream().filter(u -> u.getUserId() == (id)).findFirst().orElse(null);
    }

}
