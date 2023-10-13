package com.example.news_system.dto.response.business;

import com.example.news_system.dto.response.sport.SportsArticlesListResDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessNewsResDTO {
    private String status;
    private Integer totalResults;
    private List<BusinessArticlesListResDTO> articles;
}
