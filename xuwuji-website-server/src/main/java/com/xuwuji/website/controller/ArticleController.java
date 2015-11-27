package com.xuwuji.website.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
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
	private int article_size;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private ArticleRepository articleRepository;

	@RequestMapping(value = "/getIntroduction", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Article> getIntroduction() {
		ArrayList<Article> list = new ArrayList<Article>();
		// Article a = new Article(1, "a", "a test");
		// Article b = new Article(1, "b", "b test");
		// list.add(a);
		// list.add(b);
		return list;

	}

	@RequestMapping(value = "/saveOneArticle", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView saveOneArticle(@RequestBody Article article) {
		ModelAndView model = new ModelAndView();
		model.setViewName("ok");
		System.out.println(article.getTitle());
		System.out.println(article.getCategory());
		System.out.println(article.getContent());
		return model;
	}

	@RequestMapping(value = "/manage/article", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView manageArticle() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/Article/manage_article");
		return model;
	}

	@RequestMapping(value = "/manage/article/get", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Article> manageArticle_get() {
		ArrayList<Article> list = new ArrayList<Article>();
		Article a = new Article(1, "a", "a test", "a time", "a");
		Article b = new Article(2, "b", "b test", "b time", "b");
		list.add(a);
		list.add(b);
		return list;
	}

	@RequestMapping(value = "/article/save", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void save(@RequestBody Article article) {
		// ModelAndView model = new ModelAndView();
		// model.setViewName("ok");
		System.out.println(article.getTitle());
		System.out.println(article.getCategory());
		System.out.println(article.getContent());
		articleService.add(article);
		// return model;
	}

	// get the manage index page
	@RequestMapping(value = "/manage.html", method = RequestMethod.GET)
	public ModelAndView GetManagePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/article/manage_article");
		return model;
	}

	// get the info of the articles
	@RequestMapping(value = "/getInfo/{pageNum}", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Integer> getInfo(@PathVariable("pageNum") int pageNum) {
		int total_articles = articleService.getTotalArticlesNum();
		int total_categories = articleService.getTotalCategoriesNum();
		int max_page = articleService.getTotalPageNum();
		//pageNum -> true page number, start from 1
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

	// get the articles of the first page
	@RequestMapping(value = "/getArticles", method = RequestMethod.GET)
	public @ResponseBody List<Article> getArticles() {
		Order Order = new Order(Direction.DESC, "id");
		Sort sort = new Sort(Order);
		PageRequest d = new PageRequest(0, PageSize, sort);
		Page<Article> result = articleRepository.findAll(d);
		System.out.println("total page:" + result.getTotalPages());
		System.out.println("current page:" + (result.getNumber() + 1));
		System.out.println("number of current page:" + result.getNumberOfElements());
		System.out.println("total records:" + result.getTotalElements());
		System.out.println("content of current page" + result.getContent());
		return result.getContent();
	}

	// get the articles of the given page
	@RequestMapping(value = "/getArticles/{pageNum}", method = RequestMethod.GET)
	public @ResponseBody List<Article> getArticlesByPage(@PathVariable("pageNum") int pageNum) {
		Order Order1 = new Order(Direction.DESC, "id");
		Sort sort = new Sort(Order1);
		PageRequest d = new PageRequest(pageNum - 1, PageSize, sort);
		Page<Article> result = articleRepository.findAll(d);
		//System.out.println("total page:" + result.getTotalPages());
		//System.out.println("current page:" + (result.getNumber() + 1));
		//System.out.println("number of current page:" + result.getNumberOfElements());
		//System.out.println("total records:" + result.getTotalElements());
		//System.out.println("content of current page" + result.getContent());
		return result.getContent();
	}

}
