package com.neuedu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.entity.HFile;
import com.neuedu.utils.FileSystemUtils;

/**
 * Servlet implementation class QueryFileServ
 * <servlet-name></servlet-name>
 * <servlet-class></servlet-class>
 * 
 * <servlet-name></servlet-name>
 * <url-pattern></servlet-class>
 */
@WebServlet(name="/QueryFileServ",urlPatterns="/query.do")
public class QueryFileServ extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<HFile> queryAllFile = FileSystemUtils.queryALLFile();
		req.setAttribute("files", queryAllFile);
		req.getRequestDispatcher("/page/QueryFile.jsp").forward(req, resp);
		
	}

}
