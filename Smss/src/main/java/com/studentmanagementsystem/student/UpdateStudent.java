package com.studentmanagementsystem.student;

import com.db.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/UpdateStudent")
public class UpdateStudent extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Connection connection;
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
    	String name = request.getParameter("updateName");
        String studentId = request.getParameter("updateStudentId");
        String grade = request.getParameter("updateGrade");
        String gender = request.getParameter("updateGender");
        String contact = request.getParameter("updateContact");
        String email = request.getParameter("updateEmail");
        PrintWriter pw = response.getWriter();
        String updateQuery = "UPDATE student SET S_name = ?, S_grade = ?, S_gender = ?, S_contact = ?, S_mail = ? WHERE S_ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, grade);
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, contact);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, studentId);
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                response.getWriter().println("<script>alert('Student updated successfully'); window.location.href = '" + winloc + "';</script>");
            } else {
                response.getWriter().println("<script>alert('Student ID not found'); window.location.href = '" + winloc + "';</script>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }
