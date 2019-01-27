package com.nhn.exam.was.model.config;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nhn.exam.was.utils.JsonRead;

import lombok.Getter;
import lombok.Setter;
/**
 * 
 * @author Kim TaeHouyng
 *
 */
public class Host {
    private static Logger logger = LoggerFactory.getLogger(Host.class);

	@Getter @Setter	
	private String name;
	@Getter @Setter	
	private String port;
	@Getter @Setter	
	private List<String> filter;
	@Getter @Setter	
	private List<Server> server;
	
	@Override
	public String toString() {
		return "Host [name=" + name + ", port=" + port + ", filter=" + filter + ", server=" + server + "]";
	}
}
