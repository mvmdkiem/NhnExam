package com.nhn.exam.was.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Kim TaeHouyng
 *
 * @param <T> Object 
 * @param <V> String 
 * 
 */
public class JsonRead<T, V> {
	private static Logger logger = LoggerFactory.getLogger(JsonRead.class);
	public T jsonToObject(T t, V v) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			t = (T) mapper.readValue(ClassLoader.getSystemClassLoader().getResourceAsStream( v + ".json"), t.getClass());
		} catch (Exception e) {
			 logger.error(e.getMessage(), e);
		}
		
		return t;
	}
	
	public Map<String, Object> jsonToMap(V v) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			 map = mapper.readValue(ClassLoader.getSystemClassLoader().getResourceAsStream( v + ".json"), new TypeReference<Map<String, Object>>() {});
		} catch (Exception e) {
			 logger.error(e.getMessage(), e);
		}
		
		return map;
	}
}
