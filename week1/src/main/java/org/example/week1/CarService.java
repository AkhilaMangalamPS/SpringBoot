package org.example.week1;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    private final EngineService engineService;

    public CarService(EngineService engineService){
        this.engineService = engineService;
        System.out.println("Car Constructor created");
    }

    @PostConstruct
    public void init(){
        drive();
    }

    public void drive(){
        engineService.startEngine();
        System.out.println("Car is driving");
    }
}
