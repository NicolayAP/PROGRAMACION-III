package co.edu.uptc.Ejemplo1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class messageController {
    
    @GetMapping
    public String getMessage(){
        return "Hola mundo";
    }
}
