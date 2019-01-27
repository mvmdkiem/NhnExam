package com.nhn.exam.was.servlet;

import com.nhn.exam.was.model.request.HttpRequest;
import com.nhn.exam.was.model.response.HttpResponse;
/**
 * 
 * @author Kim TaeHouyng
 *
 * @param HttpRequest req 
 * @param HttpResponse res 
 * 
 */
public interface SimpleServlet {
	public void service(HttpRequest req, HttpResponse res) throws Exception;
}
