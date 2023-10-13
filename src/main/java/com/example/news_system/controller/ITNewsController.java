package com.example.news_system.controller;

import com.example.news_system.dto.response.it.ITNewsResDTO;
import com.example.news_system.dto.response.sport.SportsNewsResDTO;
import com.example.news_system.service.ITNewsService;
import com.example.news_system.service.SportsNewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor

public class ITNewsController {
    private final ITNewsService itNewsService;




    @GetMapping("/itnews/db")
    public ResponseEntity<ITNewsResDTO> getITNewsToDB() {
        return ResponseEntity.ok(itNewsService.GetITNewsFromApi());
    }
    @GetMapping("/itnews/articles")
    public ResponseEntity<?> getAllITArticles(){
        return ResponseEntity.ok(itNewsService.getAllITArticles());
    }
}