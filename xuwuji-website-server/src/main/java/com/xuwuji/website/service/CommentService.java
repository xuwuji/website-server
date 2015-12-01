package com.xuwuji.website.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xuwuji.website.model.Comment;
import com.xuwuji.website.repository.CommentRepository;
import com.xuwuji.website.util.Util;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;

	@Transactional
	public void save(Comment comment) {
		if (comment.getId() == 0) {
			String time = Util.getDateTime(DateTime.now());
			System.out.println("time:" + time);
			comment.setTime(time);
		}
		commentRepository.saveAndFlush(comment);

	}

	@Transactional
	public void up(int id) {
		commentRepository.upComment(id);
	}
}
