package com.studentmanagementsystem.student;

import com.db.DatabaseConnection;
import com.studentmanagementsystem.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
//////////////////////// NOT USED /////////////////////
@SuppressWarnings("unused")
@WebServlet("/GetStudentById")
public class GetStudentById extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String winloc = "/Smss";
    private Connection connection;

    @Override
    public void init() throws ServletException {

        try {
        	connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("DB connection error");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    	String selectQuery = "SELECT * FROM student WHERE S_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setInt(1, Integer.parseInt(request.getParameter("getStudentById")));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {response.getWriter().println("<script>alert('**Your Information:** \\nID: " + 
            	    rs.getInt("S_ID") + "\\n" + 
            	    "Name: " + rs.getString("S_name") + "\\n" + 
            	    "Gender: " + rs.getString("S_gender") + "\\n" + 
            	    "Grade: " + rs.getString("S_grade") + "\\n" + 
            	    "Email: " + rs.getString("S_mail") + "\\n" + 
            	    "Contact: " + rs.getString("S_contact") + "');" +
            	    "window.location.href = '" + winloc + "';</script>");

}
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("SQL error");
        }
    }}