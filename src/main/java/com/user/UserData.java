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
public class UserData extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String INSERT_QUERY = "INSERT INTO softpro_tb(name, email, phone, message) VALUES (?, ?, ?, ?)";
        
        // Set content type
        resp.setContentType("text/html");
        
        // Retrieve form data
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String text = req.getParameter("message");
        
        // Load the JDBC driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        // Create the connection
        try (Connection con = DriverManager.getConnection("jdbc:mysql:///softpro", "root", "admin");
             PreparedStatement ps = (PreparedStatement) con.prepareStatement(INSERT_QUERY)) {
             
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, text);
            
            // Execute the query
            int count = ps.executeUpdate();
            if (count > 0) {
                // Redirect to index page if the record is successfully stored
                resp.sendRedirect("index.html");
            } else {
                try (PrintWriter pw = resp.getWriter()) {
                    pw.println("<h3>Record not stored in the database</h3>");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
