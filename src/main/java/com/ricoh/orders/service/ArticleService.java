package com.ricoh.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ricoh.orders.model.Article;

@Component
@Service
public class ArticleService {
	
	@Autowired
	private ArticleRepository articleRepository;

	
	public Article read(Long id) {
		return articleRepository.findOne(id);
	}

}
