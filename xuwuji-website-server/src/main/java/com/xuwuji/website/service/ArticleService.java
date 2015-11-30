package com.xuwuji.website.service;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
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
	private Specification<Article> criteria = new Specification<Article>() {
		/**
		 * @param *root:
		 *            代表查询的实体类.
		 * @param query:
		 *            可以从中可到 Root 对象, 即告知 JPA Criteria 查询要查询哪一个实体类. 还可以 来添加查询条件,
		 *            还可以结合 EntityManager 对象得到最终查询的 TypedQuery 对象.
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
		System.out.println("time" + time);
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
		// Page<Article> result = articleRepository.findAll(pageable);
		int categories_size = (int) articleRepository.getCategoryNum();
		return categories_size;
	}

	// include the deleted articles, it is for the backend
	public int getTotalPageNum() {
		Page<Article> result = articleRepository.findAll(pageable);
		total_page_number = (int) result.getTotalPages();
		return total_page_number;
	}

	// exclude the deleted artciles, it is for the frontend
	public int getFrontTotalPageNum() {
		Page<Article> result = articleRepository.findAll(criteria, pageable);
		total_page_number = (int) result.getTotalPages();
		return total_page_number;
	}

	// include the deleted articles, it is for the backend
	public int getCurrentPageNum(int pageNum) {
		// the page number begins from 0 in the database
		PageRequest pageable = new PageRequest(pageNum - 1, PageSize, sort);
		Page<Article> result = articleRepository.findAll(pageable);
		current_page_number = (int) (result.getNumber() + 1);
		return current_page_number;
	}

	// exclude the deleted artciles, it is for the frontend
	public int getFrontCurrentPageNum(int pageNum) {
		// the page number begins from 0 in the database
		PageRequest pageable = new PageRequest(pageNum - 1, PageSize, sort);

		Page<Article> result = articleRepository.findAll(criteria, pageable);
		current_page_number = (int) (result.getNumber() + 1);
		return current_page_number;
	}

	public ArrayList<String> getCategories() {
		ArrayList<String> list = articleRepository.getCategories();
		return list;
	}

}