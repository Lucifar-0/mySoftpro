package com.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/softpro")
public class UserData extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	 String INSERT_QUERY="INSERT INTO softpro_tb(name, email, phone, message)values(?,?,?,?)";
		// get Printwriter
		
		PrintWriter pw=resp.getWriter();
		// SET CONTENT TYPE 
		resp.setContentType("text/html");
		
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String phone=req.getParameter("phone");
		String text=req.getParameter("message");
		System.out.println(name);
		System.out.println(email);
		System.out.println(phone);
		System.out.println(text);
		
		//LOAD THE JDBC DRIVER
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//CREAT THE CONNECTION
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql:///softpro","root","admin");
			PreparedStatement ps=(PreparedStatement) con.prepareStatement(INSERT_QUERY);
			ps.setString(1,name);
			ps.setString(2, email);
			ps.setString(3, phone);
			ps.setString(4, text);
			
		// EXECUTE QUERY
			int count=ps.executeUpdate();
			if(count==0) {
				pw.println("record not stored in the Database ");
				
			}
			else {
				pw.println("Record Stored in to Database");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//CREATE THE QUARY
		
		
		
		
	}

}
