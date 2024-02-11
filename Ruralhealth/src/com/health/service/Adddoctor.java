package com.health.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Adddoctor
 */
public class Adddoctor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Adddoctor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String uname = request.getParameter("uname");
		String em = request.getParameter("email");
		String mob = request.getParameter("cno");
		String pw = request.getParameter("pass");
		String ct = request.getParameter("city");
		String sp = request.getParameter("spec");
		String tr = request.getParameter("tret");
		
	
		try 
		{
			Connection con = ConnectDB.getConnect();
			PreparedStatement ps1 = con.prepareStatement("insert into doctor values (?,?,?,?,?,?,?,?)");
			ps1.setInt(1, 0);
			ps1.setString(2, uname);
			ps1.setString(3, em);
			ps1.setString(4, mob);
			ps1.setString(5, pw);
			ps1.setString(6, ct);
			ps1.setString(7, sp);
			ps1.setString(8, tr);
			
			int i = ps1.executeUpdate();
			if(i>0)
			{
				response.sendRedirect("Success.html");
			}
			else
			{
				response.sendRedirect("Failed.html");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
