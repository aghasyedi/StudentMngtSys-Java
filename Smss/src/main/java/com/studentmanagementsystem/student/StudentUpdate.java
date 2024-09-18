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
@WebServlet("/StudentUpdate")
public class StudentUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentUpdate() {
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

         // gender management
         String changeGender = " value=\"Female\" checked";
         String oppGender = " value=\"Male\"";
         String genName = "Male";
         if (gender.equals("Male")) {
             oppGender = " value=\"Female\"";
             genName = "Female";
             changeGender = " value=\"Male\" checked";
         }

         String contact = rs.getString("S_contact");
         String email = rs.getString("S_mail");

         response.getWriter().println(
             "<form action=\"UpdateStudent\" method=\"post\" onsubmit=\"return validateUpdateForm()\">\r\n"
             + "    <div class=\"form-group\">\r\n"
             + "        <label for=\"updateStudentId\">Student ID</label>\r\n"
             + "        <input type=\"text\" class=\"rounded-input\" value=\"" + sid + "\" id=\"updateStudentId\" name=\"updateStudentId\" required readonly>\r\n"
             + "    </div>\r\n"
             + "    <div class=\"form-group\">\r\n"
             + "        <label for=\"updateName\">Name</label>\r\n"
             + "        <input type=\"text\" class=\"rounded-input\" value=\"" + name + "\" id=\"updateName\" name=\"updateName\">\r\n"
             + "    </div>\r\n"
             + "    <div class=\"form-group\">\r\n"
             + "        <label for=\"updateGrade\">Grade</label>\r\n"
             + "        <input type=\"text\" class=\"rounded-input\" value=\"" + grade + "\" id=\"updateGrade\" name=\"updateGrade\">\r\n"
             + "    </div>\r\n"
             + "    <div class=\"form-group\">\r\n"
             + "        <label for=\"gender\">Gender</label>\r\n"
             + "        <div class=\"radio-group\">\r\n"
             + "            <input type=\"radio\" id=\"" + gender + "\" name=\"updateGender\"" + changeGender + "><label for=\"" + gender + "\">" + gender + "</label>\r\n"
             + "        </div>\r\n"
             + "        <div class=\"radio-group\">\r\n"
             + "            <input type=\"radio\" id=\"" + genName + "\" name=\"updateGender\"" + oppGender + "><label for=\"" + genName + "\">" + genName + "</label>\r\n"
             + "        </div>\r\n"
             + "    </div>\r\n"
             + "    <div class=\"form-group\">\r\n"
             + "        <label for=\"updateContact\">Contact</label>\r\n"
             + "        <input type=\"text\" class=\"rounded-input\" value=\"" + contact + "\" id=\"updateContact\" name=\"updateContact\">\r\n"
             + "    </div>\r\n"
             + "    <div class=\"form-group\">\r\n"
             + "        <label for=\"updateEmail\">Email</label>\r\n"
             + "        <input type=\"email\" class=\"rounded-input\" value=\"" + email + "\" id=\"updateEmail\" name=\"updateEmail\">\r\n"
             + "    </div>\r\n"
             + "    <button type=\"submit\" class=\"btn\" name=\"action\" value=\"update\">Update Student</button>\r\n"
             + "</form>");

            
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
