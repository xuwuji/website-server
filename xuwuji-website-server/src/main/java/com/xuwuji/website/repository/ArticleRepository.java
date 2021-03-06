package com.xuwuji.website.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xuwuji.website.model.Article;

/**
 * 
 * @author xuwuji first patam: the object type second param:the pk of that type
 *         不能带查询条件的分页
 */
public interface ArticleRepository extends JpaRepository<Article, Integer>, JpaSpecificationExecutor<Article> {
	Article getById(int id);

	Article getByTitle(String title);

	// where title like ?% and id < ?
	ArrayList<Article> getByTitleStartingWithAndIdLessThan(String tile, int id);

	// WHERE title LIKE %? AND id < ?
	ArrayList<Article> getByTitleEndingWithAndIdLessThan(String lastName, int id);

	// WHERE title IN (?, ?, ?) OR birth < ?
	ArrayList<Article> getByTitleInOrIdLessThan(ArrayList<String> titles, int id);

	// 查询 id 值最大的那个 Article latest
	// 使用 @Query 注解可以自定义 JPQL 语句以实现更灵活的查询
	@Query("SELECT a FROM Article a WHERE a.id = (SELECT max(a2.id) FROM Article a2)")
	Article getMaxIdArticle();

	@Query("SELECT max(a.id) FROM Article a")
	int getMaxId();

	@Query(value = "select count(*) from (SELECT * from article group by category) temp", nativeQuery = true)
	int getCategoryNum();

	// 可以通过自定义的 JPQL 完成 UPDATE 和 DELETE 操作. 注意: JPQL 不支持使用 INSERT
	// 在 @Query 注解中编写 JPQL 语句, 但必须使用 @Modifying 进行修饰. 以通知 SpringData, 这是一个
	// UPDATE 或 DELETE 操作
	// UPDATE 或 DELETE 操作需要使用事务, 此时需要定义 Service 层. 在 Service 层的方法上添加事务操作.
	// 默认情况下, SpringData 的每个方法上有事务, 但都是一个只读事务. 他们不能完成修改操作!
	@Modifying
	@Query("UPDATE Article a SET a.flag = 0 WHERE id = :id")
	void deleteArticle(@Param("id") Integer id);

	@Query(value = "select category from article where flag = 1 group by category", nativeQuery = true)
	ArrayList<String> getCategories();

	@Query(value = "select a from Article a where category = :category and flag =1 order by id DESC")
	ArrayList<Article> getArticleByCategory(@Param("category") String category);

}
