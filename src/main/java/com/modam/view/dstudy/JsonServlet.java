package com.modam.view.dstudy;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

@WebServlet("/JsonServlet")
public class JsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=EUC-KR");
		/*
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD><TITLE>Hello World Servlet</TITLE></HEAD>");
		out.println("<BODY><h2>Hello World Servlet: ��ο���<H2></BODY>");
		out.println("</HTML>");
		out.close();
		*/
//		HashMap mp = new HashMap();
//		mp.put("data4", "aaaa");
//		mp.put("data5", "bbbb");
		
		PrintWriter out = response.getWriter();		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data4", "aaaa");
		jsonObject.put("data5", "bbbb");		
		
		//out.println("{\"data3\":\"aaaa\"}");
//		out.println("\"data2\""+":");
//		out.println("\"aaaa\"");
//		out.println("\"}\"");		
//		out.close();		
		out.print(jsonObject);
		out.close();
		
//		request.setAttribute("user", "Elijah");
//		RequestDispatcher rd = getServletContext().getRequestDispatcher("/ch01/HelloWorld.jsp");
//		rd.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
