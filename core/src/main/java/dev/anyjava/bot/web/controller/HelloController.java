package dev.anyjava.bot.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("hello")
    public Map<String, Object> getHello() {
        return Map.of("status", "up");
    }
}
