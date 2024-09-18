package com.studentmanagementsystem.student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.DatabaseConnection;

/**
 * Servlet implementation class Heloo
 */
@WebServlet("/StudentGetById")
public class StudentGetById extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentGetById() {
        super();
    }
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

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 String studentId = request.getParameter("id");
	        String deleteStudentId = request.getParameter("id");
        PrintWriter out = response.getWriter();
    	 if (studentId == null || studentId.isEmpty()) {
             out.println("The given ID is NULL or Empty.");
             return;
         }

    	 int sid = Integer.parseInt(studentId);

        String updateQuery = "SELECT * FROM student WHERE S_ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, sid);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                out.println("The given ID is not in DB, please Re-try.");
            }
        String name = rs.getString("S_name");
        String grade = rs.getString("S_grade");
        String gender = rs.getString("S_gender");

         String contact = rs.getString("S_contact");
         String email = rs.getString("S_mail");

        
        response.getWriter().println("<div class=\"form-group\">");
        response.getWriter().println("<label>Student information of "+name+"."+"<br></label>");
        response.getWriter().println("<label><strong>Name</strong>: "+name+"</label>");
        response.getWriter().println("<label><strong>ID</strong>: "+deleteStudentId+"</label>");
        response.getWriter().println("<label><strong>Gender</strong>: "+gender+"</label>");
        response.getWriter().println("<label><strong>Email</strong>: "+email+"</label>");
        response.getWriter().println("<label><strong>Grade</strong>: "+grade+"</label>");
        response.getWriter().println("<label><strong>Contact</strong>: "+contact+"</label>");
        response.getWriter().println("</div>");


            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
