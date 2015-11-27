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
 *         ���ܴ���ѯ�����ķ�ҳ
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

	// ��ѯ id ֵ�����Ǹ� Article latest
	// ʹ�� @Query ע������Զ��� JPQL �����ʵ�ָ����Ĳ�ѯ
	@Query("SELECT a FROM Article a WHERE a.id = (SELECT max(a2.id) FROM Article a2)")
	Article getMaxIdArticle();

	@Query("SELECT max(a.id) FROM Article a")
	int getMaxId();

	@Query("SELECT count(id) FROM Article a GROUP BY a.category")
	int getCategoryNum();

	// ����ͨ���Զ���� JPQL ��� UPDATE �� DELETE ����. ע��: JPQL ��֧��ʹ�� INSERT
	// �� @Query ע���б�д JPQL ���, ������ʹ�� @Modifying ��������. ��֪ͨ SpringData, ����һ��
	// UPDATE �� DELETE ����
	// UPDATE �� DELETE ������Ҫʹ������, ��ʱ��Ҫ���� Service ��. �� Service ��ķ���������������.
	// Ĭ�������, SpringData ��ÿ��������������, ������һ��ֻ������. ���ǲ�������޸Ĳ���!
	@Modifying
	@Query("UPDATE Article p SET p.content = :content WHERE id = :id")
	void updateArticleContent(@Param("id") Integer id, @Param("content") String content);

}
