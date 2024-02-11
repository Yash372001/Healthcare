package com.health.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CommentAdd
 */
public class CommentAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentAdd() {
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
		
		String com=request.getParameter("cmt");
		int aid=Udata.getAid();
		int did=Udata.getDid();
		int pid=Udata.getPid();
		try
		{
			Connection con=ConnectDB.getConnect();
			PreparedStatement ps7=con.prepareStatement("insert into comment_tbl values(?,?,?,?,?)");
			ps7.setInt(1, 0);
			ps7.setInt(2, 0);
			ps7.setInt(3, pid);
			ps7.setInt(4, did);
			ps7.setString(5, com);
			
			int p=ps7.executeUpdate();
			if(p>0)
			{
				response.sendRedirect("Doctorview.jsp");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
