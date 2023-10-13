package com.example.news_system.dto.response.it;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ITNewsResDTO {
    private String status;
    private Integer totalResults;
    private List<ITArticlesListResDTO> articles;
}
