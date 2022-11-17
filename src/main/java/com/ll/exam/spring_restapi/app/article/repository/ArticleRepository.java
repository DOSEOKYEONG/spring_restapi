package com.ll.exam.spring_restapi.app.article.repository;

import com.ll.exam.spring_restapi.app.article.entiry.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByIdDesc();
}
