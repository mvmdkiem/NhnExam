package com.nhn.exam.was.servlet.impl;

import java.io.IOException;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nhn.exam.was.model.request.HttpRequest;
import com.nhn.exam.was.model.response.HttpResponse;
import com.nhn.exam.was.servlet.SimpleServlet;

/**
 * 
 * @author Kim TaeHouyng
 *
 */
public class TestServlet implements SimpleServlet {
	private static Logger logger = LoggerFactory.getLogger(TestServlet.class);
	
    @Override
    public void service(HttpRequest req, HttpResponse res) throws IOException {
    	logger.info("REQUEST  : " + req.toString());
    	logger.info("RESPONSE : " + res.toString());
        Writer writer = res.getWriter();
        writer.write("Test Page");
        writer.write(req.getParameter("name"));
        writer.flush();
    }
}
