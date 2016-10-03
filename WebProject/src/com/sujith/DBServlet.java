package com.sujith;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DBServelet
 */

@WebServlet("/DBServlet")


public class DBServlet extends HttpServlet {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String url="jdbc:mysql://localhost/mysql?useSSL=false";
	static final String user ="root";
	static final String password="root";
	
	
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DBServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		
	
		String option = request.getParameter("option");
		
		if(option!=null && option.equals("save")){
			
			saveToDB(request, response);
		}else if(option!=null && option.equals("list")){
			
			listDB(request, response);
		}else if(option!=null && option.equals("update")){
			updateDB(request, response);
		}else{
			out.println("Use corect option");
		}
		
		
	
		
	
	}
	

	private boolean saveToDB(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		boolean status=false;
		PrintWriter out = response.getWriter();
		String id= request.getParameter("id");
		String name = request.getParameter("name");
		int salary=Integer.parseInt(request.getParameter("salary"));
		String sql="insert into emp values(?,?,?)";
		
		
		int result=0;
		try {
			Class.forName(JDBC_DRIVER);
			Connection con = DriverManager.getConnection(url,user,password);
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,id);
			ps.setString(2,name);
			ps.setInt(3,salary);
			
			result=ps.executeUpdate();
			if(result==1)
			{
				status =true;
				System.out.println("row inserted");
			}
			else
			{
				status = false;
				System.out.println("not inserted");
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println("No. of rows inserted ="+result);
		return status;
			
	}
	
	public void listDB(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		PrintWriter out = response.getWriter();
		try {
			String sql="select * from emp";
			Class.forName(JDBC_DRIVER);
			Connection con = DriverManager.getConnection(url,user,password);
			Statement st= con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				String id= rs.getString(1);
				String name=rs.getString(2);
				int salary=rs.getInt(3);
				out.println("id="+id);
				out.println("name="+name);
				out.println("salary="+salary);
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
	}
	
	public boolean updateDB(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		boolean status=false;
		PrintWriter out = response.getWriter();
		String id= request.getParameter("id");
		int salary=Integer.parseInt(request.getParameter("salary"));
		String sql="update emp set salary =? where id =?";
		
		
		int result=0;
		try {
			Class.forName(JDBC_DRIVER);
			Connection con = DriverManager.getConnection(url,user,password);
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(2,id);
			ps.setInt(1,salary);
			
			result=ps.executeUpdate();
			if(result==1)
			{
				status =true;
				System.out.println("row updated");
			}
			else
			{
				status = false;
				System.out.println("not updated");
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println("No. of rows inserted ="+result);
		return status;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		doGet(request, response);
	}
	
	//private void saveToDB(String id, String name, String age, String salary, String month){
		
		
	//	String sql="insert into emp values(?,?,?,?,?)";
	//}

}
