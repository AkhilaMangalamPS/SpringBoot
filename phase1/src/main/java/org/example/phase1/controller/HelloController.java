package org.example.phase1.controller;

import org.example.phase1.service.GreetingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloController {
    private final GreetingService service;

    public HelloController(GreetingService service){
        this.service = service;
    }

    @GetMapping("/hello")
    public String hello(){
        return service.greet();
    }

    @PostMapping("/echo")
    public String echo(@RequestBody String message){
        return  "You said: "+message;
    }
}
