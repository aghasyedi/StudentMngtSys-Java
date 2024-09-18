package com.studentmanagementsystem.program;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

import com.db.DatabaseConnection;

@WebServlet("/GetProgramById")
public class GetProgramById extends HttpServlet {
    private static final long serialVersionUID = 1L;
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
        String programId = request.getParameter("id");
        String selectQuery = "SELECT * FROM program WHERE programId = ?";
        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, programId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                response.getWriter().println("<script>alert('**Program Information:** \\nID: " + 
                    rs.getString("programId") + "\\n" + 
                    "Name: " + rs.getString("programName") + "\\n" + 
                    "Duration: " + rs.getString("programDuration") + "\\n" + 
                    "Type: " + rs.getString("programType") + "');" +
                    "window.location.href = '/Smss';</script>");
            } else {
                response.getWriter().println("<script>alert('No program found with ID: " + programId + "');" +
                    "window.location.href = '/Smss';</script>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("SQL error");
        }
    }
}
