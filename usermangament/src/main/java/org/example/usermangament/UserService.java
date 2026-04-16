package org.example.usermangament;

import org.example.usermangament.exception.DuplicateUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserService {
    private List<User> userList = new ArrayList<>();

    public User addUser(User user){
        for(User u : userList){
            if (u.getId() == user.getId()){
                throw new DuplicateUser("User with same id exists.");
            }
        }
        userList.add(user);
        return user;
    }

    public String deleteUser(int id){
        for (User u : userList){
            if (u.getId() == id){
                userList.remove(u);
                return "User deleted successfully";
            }
        }
        return "User doesnt exist with such id";
    }

    public List<User> getAllUsers(){
        return userList;
    }

    public User getUserById(int id){
        for (User u : userList){
            if(u.getId() == id){
                return u;
            }
        }
        return null;
    }



}
