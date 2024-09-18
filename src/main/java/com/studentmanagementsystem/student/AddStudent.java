package com.studentmanagementsystem.student;
import com.db.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/AddStudent")
public class AddStudent extends HttpServlet {
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
    	String name = request.getParameter("S_name");
        String studentId = request.getParameter("S_ID");
        String grade = request.getParameter("S_grade");
        String gender = request.getParameter("S_gender");
        String contact = request.getParameter("S_contact");
        String email = request.getParameter("S_mail");
        boolean idExists = false;
        try {
            idExists = checkIfIdExists(studentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (idExists) {
            // If the ID exists, send a JavaScript alert and redirect to home page
            String alertScript = "<script>alert('Student ID already exists!'); window.location.href = '" + winloc + "';</script>";
            response.getWriter().println(alertScript);
            return;
        }

        String insertQuery = "INSERT INTO student (S_name, S_ID, S_grade, S_gender, S_contact, S_mail) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, studentId);
            preparedStatement.setString(3, grade);
            preparedStatement.setString(4, gender);
            preparedStatement.setString(5, contact);
            preparedStatement.setString(6, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.getWriter().println("<script>alert('StudentID added'); window.location.href = '"+winloc+"';</script>");
        
    }
    private boolean checkIfIdExists(String studentId) throws SQLException {
        String query = "SELECT COUNT(*) FROM student WHERE S_ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, studentId);
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
