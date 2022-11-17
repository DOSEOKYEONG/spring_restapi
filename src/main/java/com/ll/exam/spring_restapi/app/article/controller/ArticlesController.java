package com.ll.exam.spring_restapi.app.article.controller;

import com.ll.exam.spring_restapi.app.article.entiry.Article;
import com.ll.exam.spring_restapi.app.article.service.ArticleService;
import com.ll.exam.spring_restapi.app.base.dto.RsData;
import com.ll.exam.spring_restapi.app.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticlesController {
    private final ArticleService articleService;

    @GetMapping("")
    public ResponseEntity<RsData> list() {
        List<Article> articleList = articleService.findAll();

        return Util.spring.responseEntityOf(
                RsData.successOf(
                        Util.mapOf(
                                "articles", articleList
                        )
                )
        );
    }
}
