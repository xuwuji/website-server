import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;

import com.xuwuji.website.model.Article;
import com.xuwuji.website.repository.ArticleRepository;
import com.xuwuji.website.repository.PageSortRepository;
import com.xuwuji.website.service.ArticleService;

public class DBTest {

	// @Test
	public void testConnection() throws SQLException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}

	// @Test
	public void test() throws SQLException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ArticleRepository a = ctx.getBean(ArticleRepository.class);
		// Article aa = a.getById(1);
		Article aa = a.getByTitle("a");
		System.out.println(aa.toString());
	}

	// @Test
	public void testwhereQuery() throws SQLException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ArticleRepository a = ctx.getBean(ArticleRepository.class);
		ArrayList<Article> aa = a.getByTitleStartingWithAndIdLessThan("", 3);
		// Article aa = a.getByTitle("a");
		System.out.println(aa);
	}

	// @Test
	public void testQuery() throws SQLException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ArticleRepository a = ctx.getBean(ArticleRepository.class);
		// Article aa = a.getById(1);
		Article aa = a.getMaxIdArticle();
		System.out.println(aa.toString());
	}

	// @Test
	public void testUpdate() throws SQLException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ArticleService a = ctx.getBean(ArticleService.class);
		// Article aa = a.getById(1);
		a.updateArticleContent(1, "dsd");
		// System.out.println(aa.toString());
	}

	// @Test
	public void testPageAndSort() throws SQLException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		PageSortRepository a = ctx.getBean(PageSortRepository.class);
		// first param: index of the expected page
		// second param: how many records in one page
		Order Order1 = new Order(Direction.DESC, "id");
		Sort sort = new Sort(Order1);
		PageRequest d = new PageRequest(0, 2, sort);
		Page<Article> aa = a.findAll(d);
		System.out.println("total page:" + aa.getTotalPages());
		System.out.println("current page:" + (aa.getNumber() + 1));
		System.out.println("number of current page:" + aa.getNumberOfElements());
		System.out.println("total records:" + aa.getTotalElements());
		// get the content of this page
		System.out.println("content of current page" + aa.getContent());

		// a.updateArticleContent(1, "dsd");
		// System.out.println(aa.toString());
	}

	@Test
	public void testPageAndSortWithCriteria() throws SQLException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ArticleRepository a = ctx.getBean(ArticleRepository.class);
		// first param: index of the expected page
		// second param: how many records in one page
		Order Order1 = new Order(Direction.DESC, "id");
		Sort sort = new Sort(Order1);
		int pageNum = 0;
		int pageSize = 2;
		Specification<Article> criteria = new Specification<Article>() {
			/**
			 * @param *root:
			 *            �����ѯ��ʵ����.
			 * @param query:
			 *            ���Դ��пɵ� Root ����, ����֪ JPA Criteria ��ѯҪ��ѯ��һ��ʵ����. ������
			 *            ����Ӳ�ѯ����, �����Խ�� EntityManager ����õ����ղ�ѯ�� TypedQuery ����.
			 * @param *cb:
			 *            CriteriaBuilder ����. ���ڴ��� Criteria ��ض���Ĺ���. ��Ȼ���Դ��л�ȡ��
			 *            Predicate ����
			 * @return: *Predicate ����, ����һ����ѯ����.
			 */
			public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path path = root.get("id");
				Predicate predict = cb.gt(path, 1);
				return predict;
			}

		};
		PageRequest pageable = new PageRequest(pageNum, pageSize, sort);

		Page<Article> aa = a.findAll(criteria, pageable);
		System.out.println("total page:" + aa.getTotalPages());
		System.out.println("current page:" + (aa.getNumber() + 1));
		System.out.println("number of current page:" + aa.getNumberOfElements());
		System.out.println("total records:" + aa.getTotalElements());
		// get the content of this page
		System.out.println("content of current page" + aa.getContent());

		// a.updateArticleContent(1, "dsd");
		// System.out.println(aa.toString());
	}

}
