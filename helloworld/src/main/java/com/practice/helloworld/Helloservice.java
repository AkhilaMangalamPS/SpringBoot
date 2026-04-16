package com.practice.helloworld;

import org.springframework.stereotype.Service;

@Service
public class Helloservice {

    public String getUserInfo(String name, int age){
        return "Name: "+name+ " Age: "+age;
    }
}
