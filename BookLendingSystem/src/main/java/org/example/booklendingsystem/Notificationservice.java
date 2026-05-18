package org.example.booklendingsystem;

import org.springframework.stereotype.Service;

@Service
public class Notificationservice {
    public void notifyUser(String message){
        System.out.println("NOTIFICATION: "+ message);
    }
}
