package com.example.news_system.service;

import com.example.news_system.api.GetSportsResultFromApi;
import com.example.news_system.dto.response.sport.SportsArticlesListResDTO;
import com.example.news_system.dto.response.sport.SportsNewsResDTO;
import com.example.news_system.entity.SportsArticle;
import com.example.news_system.entity.User;
import com.example.news_system.repository.SportsArticlesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SportsNewsService {
    private final SportsArticlesRepository sportsArticlesRepository;
    private final AuthService authService;

    public SportsNewsResDTO GetSportsNewsFromApi() {
        User user = authService.getAuthenticatedUser();
        authService.isAdminRole(user);
        GetSportsResultFromApi getResultFromApi = new GetSportsResultFromApi();
        SportsNewsResDTO main = getResultFromApi.getResult();
        List<SportsArticlesListResDTO> sportsArticles = main.getArticles().stream().collect(Collectors.toList());
        for (SportsArticlesListResDTO sportsArticlesListResDTO : sportsArticles) {
            SportsArticle sportsArticle = SportsArticle.builder()
                    .author(sportsArticlesListResDTO.getAuthor())
                    .title(sportsArticlesListResDTO.getTitle())
                    .description(sportsArticlesListResDTO.getDescription())
                    .url(sportsArticlesListResDTO.getUrl())
                    .urlToImage(sportsArticlesListResDTO.getUrlToImage())
                    .publishedAt(sportsArticlesListResDTO.getPublishedAt())
                    .content(sportsArticlesListResDTO.getContent())
                    .build();
            sportsArticlesRepository.save(sportsArticle);
        }
        return main;

    }

    public List<SportsArticlesListResDTO> getAllSportsArticles() {
        List<SportsArticle> sportArticles = sportsArticlesRepository.getAllSportsArticles().stream().collect(Collectors.toList());
        System.out.println(sportArticles);
        List<SportsArticlesListResDTO> sportsArticlesListResDTOS = new ArrayList<>();
        for (SportsArticle sportsArticle : sportArticles) {
            SportsArticlesListResDTO sportsArticlesListResDTO = SportsArticlesListResDTO.builder()
                    .author(sportsArticle.getAuthor())
                    .title(sportsArticle.getTitle())
                    .description(sportsArticle.getDescription())
                    .url(sportsArticle.getUrl())
                    .urlToImage(sportsArticle.getUrlToImage())
                    .publishedAt(sportsArticle.getPublishedAt())
                    .content(sportsArticle.getContent())
                    .build();
            sportsArticlesListResDTOS.add(sportsArticlesListResDTO);

        }
        return sportsArticlesListResDTOS;

    }
}