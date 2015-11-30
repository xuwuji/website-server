package com.xuwuji.website.service;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.xuwuji.website.model.Comment;
import com.xuwuji.website.repository.CommentRepository;
import com.xuwuji.website.util.Util;

@Service
public class CommentService {

	private CommentRepository commentRepository;

	public void save(Comment comment) {
		if (comment.getId() == 0) {
			String time = Util.getDateTime(DateTime.now());
			System.out.println("time" + time);
			comment.setTime(time);
		}
		commentRepository.saveAndFlush(comment);

	}
}
