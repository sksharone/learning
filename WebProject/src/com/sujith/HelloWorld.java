package com.sujith;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HelloWorld {
	
	 // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/EMP";

	   //  Database credentials
	   static final String USER = "username";
	   static final String PASS = "password";
	   
	   String instance=null;
	
	   public HelloWorld(String instance){
		   this.instance=instance;
	   }
	   
	public static void main(String a[]){
		System.out.println("Hello World");
		
		HelloWorld hw = new HelloWorld("instance one");
		HelloWorld hw1 = new HelloWorld("instance two");
		
	
		hw.mymethod();
		hw.method2("sujith");
		hw.method3(01,"shrrone");
		hw.saveToDB("01","abc",1000);
	}
	/**
	 * 
	 */
	public void mymethod(){
		System.out.println("mymethod");
		
	}
	public void method2(String param){
		System.out.println("param= "+param);
	}
	public void method3(int a , String s){
		System.out.println("int="+a);
		System.out.println("str="+s);		
	}
	
	public boolean saveToDB(String id, String name, int salary){
		System.out.println("id="+id);
		System.out.println("name="+name);
		System.out.println("salary="+salary);
		// DB statement
		String sql="insert into emp values(?,?,?)"; 
		
		// 1 -- load the driver
		int rows=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		
		
		// 2 -- create the connectio
		Connection con = DriverManager.getConnection(DB_URL,"user1","pass");
		// 3 -- Prepare the statement
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, name);
		pstmt.setInt(3, salary);
		// 4 -- executeUpdate()
			rows = pstmt.executeUpdate();
		// 0 -- false
		// 1 - true
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rows==0)
			return false;
		else
			return true;
	}
	
	public int dummyExecuteQuery(){
		
		return 0;
	}
}
