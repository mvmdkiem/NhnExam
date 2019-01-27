package com.nhn.exam.was.model.mapping;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Mapping {
	@Getter @Setter	
	private List<MappingInfo> mappingInfo;
	
	@Override
	public String toString() {
		return "Mapping [mappingInfo=" + mappingInfo + "]";
	}
}