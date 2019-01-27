package com.nhn.exam.was.model.mapping;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class MappingInfo {
	@Getter @Setter	
	private String name;
	@Getter @Setter	
	private List<Route> route;
	
	@Override
	public String toString() {
		return "MappingInfo [name=" + name + ", route=" + route + "]";
	}
}
