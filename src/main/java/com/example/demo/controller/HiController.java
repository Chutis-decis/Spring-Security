package com.example.demo.controller;


import com.example.demo.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @Autowired
    private HiService hiService;

    @GetMapping
    public String hi() {
        return hiService.getMessage();
    }

    @GetMapping("/api/pizzas")
    public String getPizzas() {
        return "Pizzas";
    }
}
