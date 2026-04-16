package com.practice.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final Helloservice service;

    public HelloController(Helloservice service){
        this.service = service;
    }
    @GetMapping("/info/{name}")
    public String getUser(@PathVariable String name, @RequestParam int age){
        return service.getUserInfo(name,age);
    }


//    @GetMapping("/hello")
//    public String sayHello(){
//        return "welcome Akhila!";
//    }
//
//    @GetMapping("/bye")
//    public String sayGoodBye(){
//        return "GoodBye Akhila!!";
//    }
//
//    @GetMapping("/name")
//    public String getName(@RequestParam String user){
//        return "My name is "+ user;
//    }
//
//    @GetMapping("/newname/{n}")
//    public String name(@PathVariable String n){
//        return "My name is "+ n;
//    }
//
//    @GetMapping("/nameage/{age}")
//    public String info(@PathVariable int age,@RequestParam String name){
//        return "Name :" + name + "Age: " + age;
//    }
}
