package com.studentmanagementsystem.program;
import com.db.DatabaseConnection;
import com.studentmanagementsystem.model.Program;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;
@WebServlet("/GetAllPrograms")
public class GetAllPrograms extends HttpServlet {
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
        List<Program> programs = getAllPrograms(); // Implement this method

        // Set the list of students as an attribute in request
        request.setAttribute("programs", programs);
     // Get PrintWriter object for writing HTML response
        PrintWriter out = response.getWriter();

        // Start building HTML response
        out.println("<html>");
        out.println("<head><title>Program Details</title></head>");
        out.println("<body>");
//        programId | programName                                                            | programDuration | programType
        // Check if students list is not null
        if (programs != null && !programs.isEmpty()) {
            // Start building table
            out.println("<table border=\"1\">");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Program ID</th>");
            out.println("<th>Program Name</th>");
            out.println("<th>Program Duration</th>");
            out.println("<th>Program Type</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            // Loop through the list of students and display their details
            for (Program program : programs) {
                out.println("<tr>");
                out.println("<td>" + program.getProgramId() + "</td>");
                out.println("<td>" + program.getProgramName() + "</td>");
                out.println("<td>" + program.getProgramDuration() + "</td>");
                out.println("<td>" + program.getProgramType() + "</td>");
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


    private List<Program> getAllPrograms() {
        List<Program> programs = new ArrayList<>();
        String selectQuery = "SELECT * FROM program";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {
            while (resultSet.next()) {
            	Program program = new Program();
            	//from db
            	program.setProgramId(resultSet.getString("S_name"));
            	program.setProgramName(resultSet.getString("S_ID"));
            	program.setProgramDuration(resultSet.getString("S_grade"));
            	program.setProgramType(resultSet.getString("S_gender"));
                programs.add(program);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programs;
    }

}
