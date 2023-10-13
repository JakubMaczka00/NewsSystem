package com.example.news_system.repository;

import com.example.news_system.entity.BusinessArticle;
import com.example.news_system.entity.ITArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BusinessArticlesRepository extends JpaRepository<BusinessArticle,Long> {
    @Query("SELECT b " +
            "FROM BusinessArticle b " +
            "ORDER BY b.publishedAt DESC ")
    List<BusinessArticle> getAllBusinessArticles();
}
