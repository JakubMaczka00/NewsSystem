package com.example.news_system.controller;

import com.example.news_system.dto.response.sport.SportsNewsResDTO;
import com.example.news_system.service.SportsNewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor

public class SportsNewsController {
    private final SportsNewsService sportsNewsService;




    @GetMapping("/sportsnews/db")
    public ResponseEntity<SportsNewsResDTO> getSportsNewsToDB() {
        return ResponseEntity.ok(sportsNewsService.GetSportsNewsFromApi());
    }
   @GetMapping("/sportsnews/articles")
    public ResponseEntity<?> getAllSportsArticles(){
        return ResponseEntity.ok(sportsNewsService.getAllSportsArticles());
   }
}
