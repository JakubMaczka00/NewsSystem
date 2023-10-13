package com.example.news_system.service;

import com.example.news_system.api.GetITResultsFromApi;
import com.example.news_system.api.GetSportsResultFromApi;
import com.example.news_system.dto.response.it.ITArticlesListResDTO;
import com.example.news_system.dto.response.it.ITNewsResDTO;
import com.example.news_system.dto.response.sport.SportsArticlesListResDTO;
import com.example.news_system.dto.response.sport.SportsNewsResDTO;
import com.example.news_system.entity.ITArticle;
import com.example.news_system.entity.SportsArticle;
import com.example.news_system.entity.User;
import com.example.news_system.repository.ITArticlesRepository;
import com.example.news_system.repository.SportsArticlesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ITNewsService {
    private final ITArticlesRepository itArticlesRepository;
    private final AuthService authService;

    public ITNewsResDTO GetITNewsFromApi() {
        User user = authService.getAuthenticatedUser();
        authService.isAdminRole(user);
        GetITResultsFromApi getResultFromApi = new GetITResultsFromApi();
        ITNewsResDTO main = getResultFromApi.getResult();
        List<ITArticlesListResDTO> itArticles = main.getArticles().stream().collect(Collectors.toList());
        for (ITArticlesListResDTO itArticlesListResDTO : itArticles) {
            ITArticle itArticle = ITArticle.builder()
                    .author(itArticlesListResDTO.getAuthor())
                    .title(itArticlesListResDTO.getTitle())
                    .description(itArticlesListResDTO.getDescription())
                    .url(itArticlesListResDTO.getUrl())
                    .urlToImage(itArticlesListResDTO.getUrlToImage())
                    .publishedAt(itArticlesListResDTO.getPublishedAt())
                    .content(itArticlesListResDTO.getContent())
                    .build();
            itArticlesRepository.save(itArticle);
        }
        return main;

    }

    public List<ITArticlesListResDTO> getAllITArticles() {
        List<ITArticle> itArticles = itArticlesRepository.getAllITArticles().stream().collect(Collectors.toList());
        List<ITArticlesListResDTO> itArticlesListResDTOS = new ArrayList<>();
        for (ITArticle itArticle : itArticles) {
            ITArticlesListResDTO itArticlesListResDTO = ITArticlesListResDTO.builder()
                    .author(itArticle.getAuthor())
                    .title(itArticle.getTitle())
                    .description(itArticle.getDescription())
                    .url(itArticle.getUrl())
                    .urlToImage(itArticle.getUrlToImage())
                    .publishedAt(itArticle.getPublishedAt())
                    .content(itArticle.getContent())
                    .build();
            itArticlesListResDTOS.add(itArticlesListResDTO);

        }
        return itArticlesListResDTOS;

    }
}