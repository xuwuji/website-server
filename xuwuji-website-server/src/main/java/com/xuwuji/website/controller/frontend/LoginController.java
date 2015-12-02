package com.xuwuji.website.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/login")
@Controller
public class LoginController {

	@RequestMapping("/login.html")
	public ModelAndView getLoginPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/login/login");
		return model;
	}

}
