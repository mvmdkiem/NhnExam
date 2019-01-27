package com.nhn.exam.was.servlet;

import com.nhn.exam.was.model.request.HttpRequest;
import com.nhn.exam.was.model.response.HttpResponse;

public interface SimpleServlet {
	public void service(HttpRequest req, HttpResponse res) throws Exception;
}
