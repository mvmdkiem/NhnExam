package com.nhn.exam.was.servlet.impl;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

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
public class TimeServlet implements SimpleServlet{
	private static Logger logger = LoggerFactory.getLogger(HelloServlet.class);
    @Override
    public void service(HttpRequest req, HttpResponse res) throws IOException {
        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        
        Writer writer = res.getWriter();
        writer.write(dayTime.format(new Date(time)));
    }
}
