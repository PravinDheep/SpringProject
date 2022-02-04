package com.workouts;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	/*
	 * If we need to use @ related annotations, we need to get rid of old way of extending classes(AbstractController)
	 */
	@RequestMapping("/welcome")
	public ModelAndView helloWorld() {
		ModelAndView model = new ModelAndView("HelloPage");
		model.addObject("msg", "Hello World");
		return model;
	}
	
	@RequestMapping("/hi")
	public ModelAndView hiWorld() {
		ModelAndView model = new ModelAndView("HelloPage");
		model.addObject("msg", "Hi");
		return model;
	}
}
