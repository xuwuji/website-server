package com.xuwuji.website.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.xuwuji.website.model.Article;
import com.xuwuji.website.repository.ArticleRepository;
import com.xuwuji.website.service.ArticleService;

@RequestMapping("/api/article")
@Controller
public class ArticleController {
	private final int PageSize = 10;
	private final Order Order = new Order(Direction.DESC, "id");
	private final Sort sort = new Sort(Order);
	private final PageRequest pageable = new PageRequest(0, PageSize, sort);

	@Autowired
	private ArticleService articleService;

	@Autowired
	private ArticleRepository articleRepository;

	// get the manage index page
	@RequestMapping(value = "/manage.html", method = RequestMethod.GET)
	public ModelAndView GetManagePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/article/manage_article");
		return model;
	}

	// get the add page
	@RequestMapping(value = "/add.html", method = RequestMethod.GET)
	public ModelAndView getAddPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/article/add_article");
		return model;
	}

	// save an article
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void saveArticle(@RequestBody Article article) {
		System.out.println(article.getTitle());
		System.out.println(article.getCategory());
		System.out.println(article.getContent());
		System.out.println(article.getTags());
		articleService.save(article);
	}

	// delete an article
	// set the flag of the article to 0
	@RequestMapping(value = "/delete/{articleId}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteArticle(@PathVariable("articleId") int articleId) {
		articleService.deleteArticle(articleId);
		System.out.println("DELETE ATICLE ID: " + articleId);
	}

	// show the detail of an article
	// set the flag of the article to 0
	@RequestMapping(value = "/detail/{articleId}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody Article showArticleDetail(@PathVariable("articleId") int id) {
		Article article = articleRepository.getById(id);
		System.out.println("DETAIL OF ATICLE ID: " + id);
		return article;
	}

	// update an article based on its id
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void update(@RequestBody Article article) {
		articleService.update(article);
	}

	// get the info of the articles
	@RequestMapping(value = "/getInfo/{pageNum}", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Integer> getInfo(@PathVariable("pageNum") int pageNum) {
		int total_articles = articleService.getTotalArticlesNum();
		int total_categories = articleService.getTotalCategoriesNum();
		int max_page = articleService.getTotalPageNum();
		// pageNum -> true page number, start from 1
		int current_page_number = articleService.getCurrentPageNum(pageNum);
		System.out.println("total number:" + total_articles);
		System.out.println("total category:" + total_categories);
		System.out.println("total page:" + max_page);
		System.out.println("current page:" + current_page_number);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("total_articles", total_articles);
		map.put("total_categories", total_categories);
		map.put("max_page", max_page);
		map.put("current_page_number", current_page_number);
		return map;
	}

	// get the articles of the given page
	@RequestMapping(value = "/getArticles/{pageNum}", method = RequestMethod.GET)
	public @ResponseBody List<Article> getArticlesByPage(@PathVariable("pageNum") int pageNum) {
		// order:latest articles show first
		Order Order = new Order(Direction.DESC, "id");
		Sort sort = new Sort(Order);
		// in db, the page number begins from 0, so it need minus 1
		PageRequest pageable = new PageRequest(pageNum - 1, PageSize, sort);
		Page<Article> result = articleRepository.findAll(pageable);
		return result.getContent();
	}

}
