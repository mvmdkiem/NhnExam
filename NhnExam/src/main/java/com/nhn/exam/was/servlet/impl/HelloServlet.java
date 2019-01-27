package com.nhn.exam.was.servlet.impl;

import java.io.IOException;
import java.io.Writer;

import com.nhn.exam.was.model.request.HttpRequest;
import com.nhn.exam.was.model.response.HttpResponse;
import com.nhn.exam.was.servlet.SimpleServlet;

/**
 * Created by Eden on 2017. 8. 26..
 */
public class HelloServlet implements SimpleServlet {
    @Override
    public void service(HttpRequest req, HttpResponse res) throws IOException {
        System.out.print(res);
        Writer writer = res.getWriter();
        writer.write("service.Hello, ");
        writer.write(req.getParameter("name"));
        writer.flush();
    }
}
