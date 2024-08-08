package com.woorifisa.backend.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/test")
public class TestController {
    
    @GetMapping("/test")
    public String test(@RequestParam String id) {
        return id;
    }    

    @GetMapping("/test2")
    public String test2() {
        return "test2";
    }
    
}
