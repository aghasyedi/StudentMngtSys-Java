<%@ page import="java.sql.*, java.util.ArrayList, java.util.List, com.studentmanagementsystem.model.Program" %>
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

    // Method to retrieve all Programs from the database
    List<Program> programs = new ArrayList<>();
    String selectQuery = "SELECT * FROM program";
    int count=0;
    try (Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(selectQuery)) {
        while (resultSet.next()) {
        	count=count+1;
            Program program = new Program();
            program.setProgramId(resultSet.getString("programId"));
            program.setProgramName(resultSet.getString("programName"));
            program.setProgramDuration(resultSet.getString("programDuration"));
            program.setProgramType(resultSet.getString("programType"));
            programs.add(program);
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
    <title>Get All Programs</title>
</head>
<body>
    <h1>Get All Programs</h1>
    <form action="GetAllPrograms" method="get">

    <%
        // Start building HTML response
        if (programs != null && !programs.isEmpty()) {
    %>
    
    <h4>Total Number of Rows: <%=count %></h4>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Grade</th>
                    <th>Gender</th>
                </tr>
            </thead>
            <tbody>
                <% for (Program program : programs) { %>
                    <tr>
                        <td><%= program.getProgramId() %></td>
                        <td><%= program.getProgramName() %></td>
                        <td><%= program.getProgramDuration() %></td>
                        <td><%= program.getProgramType() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <p>No programs found</p>
    <% } %>
    </form>
    
</body>
</html>
