package com.xuwuji.website.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.xuwuji.website.model.Article;

public interface PageSortRepository extends PagingAndSortingRepository<Article, Integer> {

}
