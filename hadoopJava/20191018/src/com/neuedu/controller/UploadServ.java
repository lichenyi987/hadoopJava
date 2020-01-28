package com.neuedu.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.neuedu.utils.FileSystemUtils;

/**
 * Servlet implementation class UploadServ
 */
@WebServlet(name="UploadServ",urlPatterns="/upload.do")
@MultipartConfig
public class UploadServ extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part part = request.getPart("file");
		System.out.println("fileName:"+part.getSubmittedFileName());
		
//		拿到上传文件的输入流
		InputStream inputStream = part.getInputStream();
		
//		拿到文件名
		String fileName = part.getSubmittedFileName();
		System.out.println(fileName);
		new FileSystemUtils().uploadFileByPerson(inputStream, fileName);
				
	}

}
