package com.nhn.exam.was.model.mapping;

import lombok.Getter;
import lombok.Setter;
/**
 * 
 * @author Kim TaeHouyng
 *
 */
public class MapingPath {
	@Getter @Setter	
	private String path;
	@Getter @Setter	
	private String destination;
	
	@Override
	public String toString() {
		return "Route [path=" + path + ", destination=" + destination + "]";
	}
}
