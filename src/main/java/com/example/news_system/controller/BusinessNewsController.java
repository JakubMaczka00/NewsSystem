package com.example.news_system.controller;

import com.example.news_system.dto.response.business.BusinessNewsResDTO;
import com.example.news_system.dto.response.sport.SportsNewsResDTO;
import com.example.news_system.service.BusinessNewsService;
import com.example.news_system.service.SportsNewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor

public class BusinessNewsController {
    private final BusinessNewsService businessNewsService;




    @GetMapping("/businessnews/db")
    public ResponseEntity<BusinessNewsResDTO> getBusinessNewsToDB() {
        return ResponseEntity.ok(businessNewsService.GetBusinessNewsFromApi());
    }
    @GetMapping("/businessnews/articles")
    public ResponseEntity<?> getAllBusinessArticles(){
        return ResponseEntity.ok(businessNewsService.getAllBusinessArticles());
    }
}