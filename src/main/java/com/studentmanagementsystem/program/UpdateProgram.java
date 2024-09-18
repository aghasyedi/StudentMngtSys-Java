package com.studentmanagementsystem.program;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

import com.db.DatabaseConnection;

@WebServlet("/UpdateProgram")
public class UpdateProgram extends HttpServlet {
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
    	String name = request.getParameter("name");
        String programId = request.getParameter("id");
        String duration = request.getParameter("duration");
        String type = request.getParameter("type");

        String updateQuery = "UPDATE program SET programName = ?, programDuration = ?, programType = ? WHERE programId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, duration);
            preparedStatement.setString(3, type);
            preparedStatement.setString(4, programId);
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                response.getWriter().println("<script>alert('Program updated successfully'); window.location.href = '" + winloc + "';</script>");
            } else {
                response.getWriter().println("<script>alert('Program ID not found'); window.location.href = '" + winloc + "';</script>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }
