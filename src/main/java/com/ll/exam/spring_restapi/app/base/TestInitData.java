package com.ll.exam.spring_restapi.app.base;

import com.ll.exam.spring_restapi.app.article.service.ArticleService;
import com.ll.exam.spring_restapi.app.member.entity.Member;
import com.ll.exam.spring_restapi.app.member.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("test")
public class TestInitData {
    @Bean
    CommandLineRunner initData(MemberService memberService, ArticleService articleService, PasswordEncoder passwordEncoder) {
        String password = passwordEncoder.encode("1234");
        return args -> {
            Member member1 = memberService.join("user1", password, "user1@test.com");
            Member member2 = memberService.join("user2", password, "user2@test.com");

            articleService.write(member1, "제목 1", "내용 1");
            articleService.write(member2, "제목 2", "내용 2");
        };
    }
}