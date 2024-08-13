package com.woorifisa.backend.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woorifisa.backend.common.dto.ReviewDTO;
import com.woorifisa.backend.manage.service.ReviewService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "review test")
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService testService;

    @PostMapping("/insertRev")
    public String insertMem(@RequestBody ReviewDTO dto) {              
        return testService.insertRev(dto);
    }
    
}
