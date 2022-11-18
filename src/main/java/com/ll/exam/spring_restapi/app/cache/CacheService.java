package com.ll.exam.spring_restapi.app.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
    @Cacheable("key1")
    public int getCachedInt() {
        System.out.println("getCachedInt 호출됨");
        return 5;
    }

    @CacheEvict("key1")
    public void deleteCacheKey1() {

    }

    @CachePut
    public int putCacheKey1() {
        return 10;
    }

    @Cacheable("plus")
    public int plus(int a, int b) {
        return a + b;
    }
}
