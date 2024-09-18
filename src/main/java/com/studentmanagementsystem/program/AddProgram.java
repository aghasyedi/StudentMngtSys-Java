package com.studentmanagementsystem.program;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

import com.db.DatabaseConnection;

@WebServlet("/AddProgram")
public class AddProgram extends HttpServlet {
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
    	String programName = request.getParameter("name");
        String programId = request.getParameter("id");
        String programDuration = request.getParameter("duration");
        String programType = request.getParameter("type");
        boolean idExists = false;
        try {
            idExists = checkIfIdExists(programId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (idExists) {
            // If the ID exists, send a JavaScript alert and redirect to home page
            String alertScript = "<script>alert('Program ID already exists!'); window.location.href = '" + winloc + "';</script>";
            response.getWriter().println(alertScript);
            return;
        }

        String insertQuery = "INSERT INTO program (programName, programId, programDuration, programType) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, programName);
            preparedStatement.setString(2, programId);
            preparedStatement.setString(3, programDuration);
            preparedStatement.setString(4, programType);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.getWriter().println("<script>alert('programId added'); window.location.href = '"+winloc+"';</script>");
        
    }
    private boolean checkIfIdExists(String programId) throws SQLException {
        String query = "SELECT COUNT(*) FROM program WHERE programId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, programId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }

    @Override
    public void destroy() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
