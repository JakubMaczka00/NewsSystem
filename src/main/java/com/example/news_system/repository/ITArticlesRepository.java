package com.example.news_system.repository;

import com.example.news_system.entity.ITArticle;
import com.example.news_system.entity.SportsArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITArticlesRepository extends JpaRepository<ITArticle,Long> {
    @Query("SELECT i " +
            "FROM ITArticle i " +
            "ORDER BY i.publishedAt DESC ")
    List<ITArticle> getAllITArticles();
}
