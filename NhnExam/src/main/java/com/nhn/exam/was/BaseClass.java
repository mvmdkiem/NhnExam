package com.nhn.exam.was;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BaseClass {
	protected static final Logger logger = LoggerFactory.getLogger(BaseClass.class);
	
	public static void main(String[] ar) {
		logger.info("test");
	}
}
