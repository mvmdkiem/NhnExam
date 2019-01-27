package com.nhn.exam.was.model.config;

import lombok.Getter;
import lombok.Setter;

public class Server {
	@Getter @Setter	
	private String name;
	@Getter @Setter
	private String root;
	@Getter @Setter
	private String domain;
	@Getter @Setter
	private Html html;
	
	@Override
	public String toString() {
		return "Server [name=" + name + ", root=" + root + ", domain=" + domain + ", html=" + html + "]";
	}
}
