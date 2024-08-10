package com.woorifisa.backend.test;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class TestController {

    @Autowired
    TestService testService;

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> reqMap) {
        return testService.login(reqMap);
         
    }

}
