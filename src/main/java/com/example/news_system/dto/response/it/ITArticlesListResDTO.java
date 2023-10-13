package com.example.news_system.dto.response.it;

import com.example.news_system.dto.response.SourceResDTO;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ITArticlesListResDTO {
    private SourceResDTO source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;
}
