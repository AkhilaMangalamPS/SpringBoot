package org.example.week1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EngineService {
    public EngineService(){
        System.out.println("EngineService Constructor : engine created ");
    }
    public void startEngine(){
        System.out.println("Engine Started");
    }
}
