package org.example.booklendingsystem;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LendingController {
    private final LendingService service;

    public LendingController(LendingService service) {
        this.service = service;
    }

    @PostMapping("/lend/{userID}/{BookID}")
    public String borrowBook(@PathVariable int userID,@PathVariable int BookID){
        return service.borrowBook(userID,BookID);
    }

    @PostMapping("/return/{userID}/")
    public String returnBook(@PathVariable int userID,@PathVariable int BookID){
        return service.returnBook(userID,BookID);
    }
}
