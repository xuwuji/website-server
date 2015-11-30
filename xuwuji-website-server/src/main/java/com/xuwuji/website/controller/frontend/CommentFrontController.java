package com.xuwuji.website.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/comment")
public class CommentFrontController {

	// save an comment
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save() {

	}

}
