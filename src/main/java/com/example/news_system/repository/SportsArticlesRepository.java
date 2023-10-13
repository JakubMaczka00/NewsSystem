package com.example.news_system.repository;

import com.example.news_system.entity.SportsArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface SportsArticlesRepository extends JpaRepository<SportsArticle,Long> {
    @Query("SELECT s " +
            "FROM SportsArticle s " +
            "ORDER BY s.publishedAt DESC ")
    List<SportsArticle> getAllSportsArticles();

}
