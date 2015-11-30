package com.xuwuji.website.controller.frontend;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.xuwuji.website.model.Article;
import com.xuwuji.website.repository.ArticleRepository;
import com.xuwuji.website.service.ArticleService;

@Controller
@RequestMapping(value = "/article/front")
public class ArticleFrontController {
	private final int PageSize = 10;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private ArticleRepository articleRepository;

	// get the articles from the db,the flag need to be 1
	@RequestMapping(value = "/getArticles/{pageNum}", method = RequestMethod.GET)
	public @ResponseBody List<Article> getArticlesByPage(@PathVariable("pageNum") int pageNum) {
		// order:latest articles show first
		Order Order = new Order(Direction.DESC, "id");
		Sort sort = new Sort(Order);
		Specification<Article> criteria = new Specification<Article>() {
			/**
			 * @param *root:
			 *            代表查询的实体类.
			 * @param query:
			 *            可以从中可到 Root 对象, 即告知 JPA Criteria 查询要查询哪一个实体类. 还可以
			 *            来添加查询条件, 还可以结合 EntityManager 对象得到最终查询的 TypedQuery 对象.
			 * @param *cb:
			 *            CriteriaBuilder 对象. 用于创建 Criteria 相关对象的工厂. 当然可以从中获取到
			 *            Predicate 对象
			 * @return: *Predicate 类型, 代表一个查询条件.
			 */
			public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path path = root.get("flag");
				Predicate predict = cb.equal(path, 1);
				return predict;
			}
		};
		// in db, the page number begins from 0, so it need minus 1
		PageRequest pageable = new PageRequest(pageNum - 1, PageSize, sort);
		Page<Article> result = articleRepository.findAll(criteria, pageable);
		return result.getContent();
	}

	// get the info of the articles
	@RequestMapping(value = "/getInfo/{pageNum}", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Integer> getInfo(@PathVariable("pageNum") int pageNum) {
		// pageNum -> true page number, start from 1
		int current_page_number = articleService.getFrontCurrentPageNum(pageNum);
		int max_page = articleService.getFrontTotalPageNum();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("current_page_number", current_page_number);
		map.put("max_page", max_page);
		return map;
	}

	// get the categories of the articles
	@RequestMapping(value = "/getInfo/categories", method = RequestMethod.GET)
	public @ResponseBody ArrayList<String> getCategories() {
		ArrayList<String> list = articleService.getCategories();
		return list;
	}

	// get articles of a given category
	@RequestMapping(value = "/getArticles/category/{category}", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Article> getArticlesByCategories(@PathVariable("category") final String category) {
		ArrayList<Article> list = articleRepository.getArticleByCategory(category);
		// order:latest articles show first
		Order Order = new Order(Direction.DESC, "id");
		Sort sort = new Sort(Order);
		Specification<Article> criteria1 = new Specification<Article>() {
			/**
			 * @param *root:
			 *            代表查询的实体类.
			 * @param query:
			 *            可以从中可到 Root 对象, 即告知 JPA Criteria 查询要查询哪一个实体类. 还可以
			 *            来添加查询条件, 还可以结合 EntityManager 对象得到最终查询的 TypedQuery 对象.
			 * @param *cb:
			 *            CriteriaBuilder 对象. 用于创建 Criteria 相关对象的工厂. 当然可以从中获取到
			 *            Predicate 对象
			 * @return: *Predicate 类型, 代表一个查询条件.
			 */
			public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path path1 = root.get("flag");
				Predicate predict1 = cb.equal(path1, 1);
				return predict1;
			}
		};
		Specification<Article> criteria2 = new Specification<Article>() {
			/**
			 * @param *root:
			 *            代表查询的实体类.
			 * @param query:
			 *            可以从中可到 Root 对象, 即告知 JPA Criteria 查询要查询哪一个实体类. 还可以
			 *            来添加查询条件, 还可以结合 EntityManager 对象得到最终查询的 TypedQuery 对象.
			 * @param *cb:
			 *            CriteriaBuilder 对象. 用于创建 Criteria 相关对象的工厂. 当然可以从中获取到
			 *            Predicate 对象
			 * @return: *Predicate 类型, 代表一个查询条件.
			 */
			public List<Predicate> toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				final List<Predicate> predicates = new ArrayList<Predicate>();
				Path path1 = root.get("flag");
				Path path2 = root.get("category");
				Predicate predict1 = cb.equal(path1, 1);
				Predicate predict2 = cb.equal(path2, category);
				predicates.add(predict1);
				predicates.add(predict2);
				return predicates;
			}
		};
		// in db, the page number begins from 0, so it need minus 1
		PageRequest pageable = new PageRequest(pageNum - 1, PageSize, sort);
		Page<Article> result = articleRepository.findAll(criteria, pageable);
		return result.getContent();
		return list;
	}

	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView getIndexPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/article/index");
		return model;
	}

}
