package com.xuwuji.website.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xuwuji.website.model.Article;
import com.xuwuji.website.repository.ArticleRepository;
import com.xuwuji.website.util.Util;

@Service
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;

	private final int PageSize = 10;
	private final Order Order = new Order(Direction.DESC, "id");
	private final Sort sort = new Sort(Order);
	private int pageNum;
	private final PageRequest pageable = new PageRequest(0, PageSize, sort);
	private int article_size;
	private int categories_size;
	private int total_page_number;
	private int current_page_number;

	@Transactional
	public void save(Article article) {
		System.out.println("id" + article.getId());
		if (article.getId() == 0) {
			String time = Util.getDateTime(DateTime.now());
			System.out.println("time" + time);
			article.setTime(time);
			article.setFlag(1);
		}
		// 这里其实是可以覆盖的，如果在数据库中已经有了这么一个article记录（以id即pk来判断），则直接在此条记录上面覆盖

		articleRepository.saveAndFlush(article);
	}
	
	@Transactional
	public void update(Article article) {
		System.out.println("UODATE ID:" + article.getId());
		String time = Util.getDateTime(DateTime.now());
		//System.out.println("time" + time);
		article.setTime(time);
		// 这里其实是可以覆盖的，如果在数据库中已经有了这么一个article记录（以id即pk来判断），则直接在此条记录上面覆盖
		articleRepository.saveAndFlush(article);
	}
	

	@Transactional
	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	public int getTotalArticlesNum() {
		Page<Article> result = articleRepository.findAll(pageable);
		article_size = (int) result.getTotalElements();
		System.out.println("total records:" + result.getTotalElements());
		return article_size;
	}

	public int getTotalCategoriesNum() {
		Page<Article> result = articleRepository.findAll(pageable);
		categories_size = (int) result.getTotalElements();
		return categories_size;
	}

	public int getTotalPageNum() {
		Page<Article> result = articleRepository.findAll(pageable);
		total_page_number = (int) result.getTotalPages();
		return total_page_number;
	}

	public int getCurrentPageNum(int pageNum) {
		// the page number begins from 0 in the database
		PageRequest pageable = new PageRequest(pageNum - 1, PageSize, sort);
		Page<Article> result = articleRepository.findAll(pageable);
		current_page_number = (int) (result.getNumber() + 1);
		return current_page_number;
	}

}
