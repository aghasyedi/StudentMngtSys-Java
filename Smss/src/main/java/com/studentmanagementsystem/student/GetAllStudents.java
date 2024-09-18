package com.studentmanagementsystem.student;
import com.db.DatabaseConnection;
import com.studentmanagementsystem.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/GetAllStudents")
public class GetAllStudents extends HttpServlet {
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
    	// Retrieve all student details here
        // For example, you can fetch data from a database or any other data source
        List<Student> students = getAllStudents(); // Implement this method

        // Set the list of students as an attribute in request
        request.setAttribute("students", students);
     // Get PrintWriter object for writing HTML response
        PrintWriter out = response.getWriter();

        // Start building HTML response
        out.println("<html>");
        out.println("<head><title>Student Details</title></head>");
        out.println("<body>");

        // Check if students list is not null
        if (students != null && !students.isEmpty()) {
            // Start building table
            out.println("<table border=\"1\">");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Grade</th>");
            out.println("<th>Gender</th>");
            out.println("<th>Contact</th>");
            out.println("<th>Email</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            // Loop through the list of students and display their details
            for (Student student : students) {
                out.println("<tr>");
                out.println("<td>" + student.getStudentId() + "</td>");
                out.println("<td>" + student.getName() + "</td>");
                out.println("<td>" + student.getGrade() + "</td>");
                out.println("<td>" + student.getGender() + "</td>");
                out.println("<td>" + student.getContact() + "</td>");
                out.println("<td>" + student.getEmail() + "</td>");
                out.println("</tr>");
            }

            out.println("</tbody>");
            out.println("</table>");
        } else {
            // If no students found, display a message
            out.println("<p>No students found</p>");
        }

        // End HTML response
        out.println("</body>");
        out.println("</html>");
    }
//        // Forward the request to JSP to display the student details
//        RequestDispatcher dispatcher = request.getRequestDispatcher("web/includes/main/student/studentDetails.jsp");
//        dispatcher.forward(request, response);


    private List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String selectQuery = "SELECT * FROM student";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {
            while (resultSet.next()) {
                Student student = new Student();
                student.setName(resultSet.getString("S_name"));
                student.setStudentId(resultSet.getString("S_ID"));
                student.setGrade(resultSet.getString("S_grade"));
                student.setGender(resultSet.getString("S_gender"));
                student.setContact(resultSet.getString("S_contact"));
                student.setEmail(resultSet.getString("S_mail"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

}
