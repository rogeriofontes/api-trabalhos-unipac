package br.com.unipac.apitrabalhos.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CaffeineCache professorCache() {
        return new CaffeineCache("ProfessorCache", Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .maximumSize(500).build());
    }

    @Bean
    public CaffeineCache trabalhoCache() {
        return new CaffeineCache("TrabalhoCache", Caffeine.newBuilder()
                .expireAfterWrite(120, TimeUnit.MINUTES)
                .maximumSize(1000).build());
    }

}
