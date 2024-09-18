<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.studentmanagementsystem.model.*, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Details</title>
</head>
<body>
    <h2>Student Details</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Grade</th>
                <th>Gender</th>
                <th>Contact</th>
                <th>Email</th>
                <!-- Add more columns if needed -->
            </tr>
        </thead>
        <tbody>
            <%-- Loop through the list of students and display their details --%>
            <% List<Student> students = (List<Student>) request.getAttribute("students"); %>
            <% if (students != null) { %>
                <% for (Student student : students) { %>
                    <tr>
                        <td><%= student.getStudentId() %></td>
                        <td><%= student.getName() %></td>
                        <td><%= student.getGrade() %></td>
                        <td><%= student.getGender() %></td>
                        <td><%= student.getContact() %></td>
                        <td><%= student.getEmail() %></td>
                    </tr>
                <% } %>
            <% } else { %>
                <tr>
                    <td colspan="3">No students found</td>
                </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>