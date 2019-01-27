package com.nhn.exam.was.model.config;

import lombok.Getter;
import lombok.Setter;

public class Html {
	@Getter 	
	private String index;
	@Getter 	
	private String page403;
	@Getter 	
	private String page404;
	@Getter 	
	private String page500;
	
	public Html() {
		this.index = "index";
		this.page403 = "403";
		this.page404 = "404";
		this.page500 = "500";
	}
	
	@Override
	public String toString() {
		return "Html [index=" + index + ", page403=" + page403 + ", page404=" + page404 + ", page500=" + page500+ "]";
	}
}
