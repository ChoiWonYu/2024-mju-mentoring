package com.mju.mentoring.global.config;

import com.mju.mentoring.board.infrastructure.BoardQueryDslRepository;
import com.mju.mentoring.mission.infrastructure.progress.ProgressQueryDslRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestQuerydslConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    public BoardQueryDslRepository boardQueryDslRepository() {
        return new BoardQueryDslRepository(jpaQueryFactory());
    }

    @Bean
    public ProgressQueryDslRepository progressQueryDslRepository() {
        return new ProgressQueryDslRepository(jpaQueryFactory());
    }
}
