package com.neuedu.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.utils.FileSystemUtils;

/**
 * Servlet implementation class DownServ
 */
@WebServlet(name="DownServ",urlPatterns = "/down.do")
public class DownServ extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String webPath = req.getParameter("downpath");
		FileSystemUtils.fileDownLoad(webPath);
	}
}
