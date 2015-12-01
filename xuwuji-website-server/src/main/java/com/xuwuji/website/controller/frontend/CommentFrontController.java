package com.xuwuji.website.controller.frontend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.xuwuji.website.model.Comment;
import com.xuwuji.website.repository.CommentRepository;
import com.xuwuji.website.service.CommentService;

@Controller
@RequestMapping("/comment/front")
public class CommentFrontController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private CommentRepository commentRepository;

	// save an comment
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void save(@RequestBody Comment comment) {
		System.out.println(comment.getArticle_id());
		System.out.println(comment.getContent());
		System.out.println(comment.getUser());
		commentService.save(comment);
	}

	// get comments of an article
	@RequestMapping(value = "/get/{article_id}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody List<Comment> getComments(@PathVariable("article_id") int article_id) {
		List<Comment> comments = new ArrayList<Comment>();
		comments = commentRepository.getCommentsByArticleId(article_id);
		return comments;
	}

	// up a comment
	@RequestMapping(value = "/up/{comment_id}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody List<String> upComment(@PathVariable("comment_id") int comment_id) {
		commentService.up(comment_id);
		List<String> l=new ArrayList<String> ();
		return l;
	}
}
