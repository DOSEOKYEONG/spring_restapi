package com.ll.exam.spring_restapi.app.article.service;

import com.ll.exam.spring_restapi.app.article.entiry.Article;
import com.ll.exam.spring_restapi.app.article.repository.ArticleRepository;
import com.ll.exam.spring_restapi.app.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Article write(Member author, String subject, String content) {
         Article article = Article.builder()
                .subject(subject)
                .content(content)
                .author(author)
                .build();

        articleRepository.save(article);

        return article;
    }
}