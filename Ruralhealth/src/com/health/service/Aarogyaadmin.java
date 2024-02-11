package com.health.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.health.service.ConnectDB;

/**
 * Servlet implementation class Aarogyaadmin
 */
public class Aarogyaadmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Aarogyaadmin() {
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
		
		String em = request.getParameter("email");
		String pw = request.getParameter("pass");
		
		Connection con = ConnectDB.getConnect();
		try 
		{
			PreparedStatement ps7 = con.prepareStatement("select * from aarogya where email = ? and password = ?");
			ps7.setString(1, em);
			ps7.setString(2, pw);
			ResultSet rs3 = ps7.executeQuery();
			while(rs3.next())
			{
				Udata.setAid(rs3.getInt(1));
				response.sendRedirect("Addcase.jsp");
				
			}			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

}
