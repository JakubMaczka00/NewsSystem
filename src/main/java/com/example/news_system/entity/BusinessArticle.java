package com.example.news_system.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="businessArticles")
public class BusinessArticle {
    @Id
    @GeneratedValue
    private Integer id;
    private String author;
    @Column(length=500)
    private String title;
    @Column(length=500)
    private String description;
    @Column(length=500)
    private String url;
    @Column(length=500)
    private String urlToImage;
    @Column(length=500)
    private String publishedAt;
    @Column(length=500)
    private String content;
}