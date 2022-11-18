package com.ll.exam.spring_restapi;


import com.ll.exam.spring_restapi.app.base.dto.Person;
import com.ll.exam.spring_restapi.app.cache.CacheService;
import com.ll.exam.spring_restapi.app.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class CacheTests {
    @Autowired
    private MemberService memberService;

    @Autowired
    private CacheService cacheService;

    @Test
    @DisplayName("캐시 사용")
    void t1() throws Exception {
        int rs = cacheService.getCachedInt();

        assertThat(rs).isGreaterThan(0);
        System.out.println(rs);

        rs = cacheService.getCachedInt();

        assertThat(rs).isGreaterThan(0);
        System.out.println(rs);
    }

    @Test
    @DisplayName("캐시 삭제")
    void t2() throws Exception {
        int rs = cacheService.getCachedInt();
        System.out.println(rs);

        rs = cacheService.getCachedInt();
        System.out.println(rs);

        cacheService.deleteCacheKey1();

        rs = cacheService.getCachedInt();
        System.out.println(rs);
    }

    @Test
    @DisplayName("캐시 수정")
    void t3() throws Exception {
        int rs = cacheService.getCachedInt();
        System.out.println(rs);

        rs = cacheService.getCachedInt();
        System.out.println(rs);

        cacheService.putCacheKey1();

        rs = cacheService.getCachedInt();
        System.out.println(rs);
    }

    @Test
    @DisplayName("더하기 캐시")
    void t4() throws Exception {
        int rs = cacheService.plus(10, 20);
        System.out.println(rs);

        rs = cacheService.plus(5, 2);
        System.out.println(rs);

        rs = cacheService.plus(10, 20);
        System.out.println(rs);
    }

    @Test
    @DisplayName("래퍼런스 매개변수")
    void t5() throws Exception {
        Person p1 = new Person(1, "홍길동1");
        Person p2 = new Person(1, "홍길동2");

        System.out.println(p1.equals(p2));

        String personName = cacheService.getName(p1, 5);
        System.out.println(personName);

        personName = cacheService.getName(p2, 10);
        System.out.println(personName);
    }
}
