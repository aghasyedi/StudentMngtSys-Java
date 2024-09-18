package com.studentmanagementsystem.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

import com.db.DatabaseConnection;

@WebServlet("/DeleteStudent")
public class DeleteStudent extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Connection connection;
    private static final String winloc = "/Smss";

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String studentId = request.getParameter("deleteStudentId");
        String deleteQuery = "DELETE FROM student WHERE S_ID = ?";
        String alertScript;
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, studentId);
            preparedStatement.executeUpdate();
            alertScript = "<script>alert('Student ID  Deleted!'); window.location.href = '" + winloc + "';</script>";
            response.getWriter().println(alertScript);
        } catch (SQLException e) {
            e.printStackTrace();
            alertScript = "<script>alert('Student ID Error!'); window.location.href = '" + winloc + "';</script>";
            response.getWriter().println(alertScript);
        }
    }

}
