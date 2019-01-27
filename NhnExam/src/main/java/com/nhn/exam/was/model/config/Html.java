package com.nhn.exam.was.model.config;

import lombok.Getter;
import lombok.Setter;

public class Html {
	@Getter @Setter	
	private String index;
	@Getter @Setter	
	private String page403;
	@Getter @Setter	
	private String page404;
	@Getter @Setter	
	private String page500;
	
	@Override
	public String toString() {
		return "Html [index=" + index + ", page403=" + page403 + ", page404=" + page404 + ", page500=" + page500+ "]";
	}
}
