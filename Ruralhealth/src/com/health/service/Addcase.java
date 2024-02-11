package com.health.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Addcase
 */
public class Addcase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addcase() {
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
		
		try
		{
			Connection con=ConnectDB.getConnect();
		int pid=0,age,drid=0;
		String address,pname,mobile,gender,symp1,symp2,symp3,uDisease=null;
		
		
		pname=request.getParameter("pname");
		age=Integer.parseInt(request.getParameter("age"));
		gender=request.getParameter("gen");
		address=request.getParameter("addr");
		mobile=request.getParameter("cno");
		symp1=request.getParameter("symp1");
		symp2=request.getParameter("symp2");
		symp3=request.getParameter("symp3");
		
		
			
			PreparedStatement ps3=con.prepareStatement("select * from disease_set where (sym1=? and sym2=? and sym3=?) or (sym1=? and sym2=?) or (sym2=? and sym3=?) or (sym1=? and sym3=?)");
			
			ps3.setString(1, symp1);
			ps3.setString(2, symp2);
			ps3.setString(3, symp3);
			ps3.setString(4, symp1);
			ps3.setString(5, symp2);
			ps3.setString(6, symp2);
			ps3.setString(7, symp3);
			ps3.setString(8, symp1);
			ps3.setString(9, symp3);
			
			ResultSet rs4=ps3.executeQuery();
			
			while(rs4.next())
			{
				String dis=rs4.getString(1);
				System.out.println(dis);
				uDisease = dis;
			}	 
				PreparedStatement ps4=con.prepareStatement("select * from doctor where dtreat=? order by rand() limit 1");
				ps4.setString(1, uDisease);
				
				ResultSet rs5=ps4.executeQuery();
			
				while(rs5.next())
				{
					drid=rs5.getInt(1);
					
				}	
					PreparedStatement ps5=con.prepareStatement("insert into addcase values(?,?,?,?,?,?,?,?,?,?,?,?)");
					ps5.setInt(1, pid);
					ps5.setString(2, pname);
					ps5.setInt(3, age);
					ps5.setString(4, gender);
					ps5.setString(5, address);
					ps5.setString(6, mobile);
					ps5.setString(7, symp1);
					ps5.setString(8, symp2);
					ps5.setString(9, symp3);
					int aid = Udata.getAid();
					ps5.setInt(10, aid);
					ps5.setString(11, uDisease);
					ps5.setInt(12, drid);
					
					
					int i=ps5.executeUpdate();
					if(i>0)
					{
						response.sendRedirect("Viewcase.jsp");
					}
				
			

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}

}
