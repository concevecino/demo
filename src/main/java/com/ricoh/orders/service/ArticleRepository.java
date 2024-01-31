package com.ricoh.orders.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ricoh.orders.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

}
