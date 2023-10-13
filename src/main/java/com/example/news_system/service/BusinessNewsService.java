package com.example.news_system.service;

import com.example.news_system.api.GetBusinessResultsFromApi;
import com.example.news_system.api.GetITResultsFromApi;
import com.example.news_system.api.GetSportsResultFromApi;
import com.example.news_system.dto.response.business.BusinessArticlesListResDTO;
import com.example.news_system.dto.response.business.BusinessNewsResDTO;
import com.example.news_system.dto.response.it.ITArticlesListResDTO;
import com.example.news_system.dto.response.it.ITNewsResDTO;
import com.example.news_system.dto.response.sport.SportsArticlesListResDTO;
import com.example.news_system.dto.response.sport.SportsNewsResDTO;
import com.example.news_system.entity.BusinessArticle;
import com.example.news_system.entity.ITArticle;
import com.example.news_system.entity.SportsArticle;
import com.example.news_system.entity.User;
import com.example.news_system.repository.BusinessArticlesRepository;
import com.example.news_system.repository.ITArticlesRepository;
import com.example.news_system.repository.SportsArticlesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class BusinessNewsService {
    private final BusinessArticlesRepository businessArticlesRepository;
    private final AuthService authService;
    @Autowired
    public BusinessNewsService(BusinessArticlesRepository businessArticlesRepository, AuthService authService){
        this.businessArticlesRepository = businessArticlesRepository;
        this.authService = authService;
    }


    public BusinessNewsResDTO GetBusinessNewsFromApi() {
        User user = authService.getAuthenticatedUser();
        authService.isAdminRole(user);
        GetBusinessResultsFromApi getResultFromApi = new GetBusinessResultsFromApi();
        BusinessNewsResDTO main = getResultFromApi.getResult();
        List<BusinessArticlesListResDTO> businessArticles = main.getArticles().stream().collect(Collectors.toList());
        for (BusinessArticlesListResDTO businessArticlesListResDTO : businessArticles) {
            BusinessArticle itArticle = BusinessArticle.builder()
                    .author(businessArticlesListResDTO.getAuthor())
                    .title(businessArticlesListResDTO.getTitle())
                    .description(businessArticlesListResDTO.getDescription())
                    .url(businessArticlesListResDTO.getUrl())
                    .urlToImage(businessArticlesListResDTO.getUrlToImage())
                    .publishedAt(businessArticlesListResDTO.getPublishedAt())
                    .content(businessArticlesListResDTO.getContent())
                    .build();
            businessArticlesRepository.save(itArticle);
        }
        return main;

    }

    public List<BusinessArticlesListResDTO> getAllBusinessArticles() {
        List<BusinessArticle> businessArticles = businessArticlesRepository.getAllBusinessArticles().stream().collect(Collectors.toList());
        List<BusinessArticlesListResDTO> businessArticlesListResDTOS = new ArrayList<>();
        for (BusinessArticle businessArticle : businessArticles) {
            BusinessArticlesListResDTO businessArticlesListResDTO = BusinessArticlesListResDTO.builder()
                    .author(businessArticle.getAuthor())
                    .title(businessArticle.getTitle())
                    .description(businessArticle.getDescription())
                    .url(businessArticle.getUrl())
                    .urlToImage(businessArticle.getUrlToImage())
                    .publishedAt(businessArticle.getPublishedAt())
                    .content(businessArticle.getContent())
                    .build();
            businessArticlesListResDTOS.add(businessArticlesListResDTO);

        }
        return businessArticlesListResDTOS;

    }
}