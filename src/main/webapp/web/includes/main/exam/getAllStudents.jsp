<%@ page import="java.sql.*, java.util.ArrayList, java.util.List, com.studentmanagementsystem.model.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*, java.util.*" %>

<%
    // Database connection details
    String DB_URL = "jdbc:mysql://localhost:3306/students";
    String DB_USER = "root";
    String DB_PASSWORD = "";

    // Establishing database connection
    Connection connection = null;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        throw new ServletException("DB connection error");
    }

    // Method to retrieve all students from the database
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

    // Close the database connection
    try {
        if (connection != null) {
            connection.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
%>

<html>
<head>
    <title>Get All Students</title>
</head>
<body>
    <h1>Get All Students</h1>
    <form action="GetAllStudents" method="get">

    <%
        // Start building HTML response
        if (students != null && !students.isEmpty()) {
    %>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Grade</th>
                    <th>Gender</th>
                    <th>Contact</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
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
            </tbody>
        </table>
    <% } else { %>
        <p>No students found</p>
    <% } %>
    </form>
    
</body>
</html>
