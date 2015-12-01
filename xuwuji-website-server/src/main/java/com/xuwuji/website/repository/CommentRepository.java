package com.xuwuji.website.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xuwuji.website.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>, JpaSpecificationExecutor<Comment> {

	@Query(value = "select c from Comment c where article_id=:article_id order by id DESC")
	List<Comment> getCommentsByArticleId(@Param("article_id") int article_id);

	@Modifying
	@Query(value = "update Comment c set c.up=c.up+1 where id=:id")
	void upComment(@Param("id") int id);

}
