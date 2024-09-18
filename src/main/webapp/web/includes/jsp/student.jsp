<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import= "java.util.*"%>
<%@ page import="com.studentmanagementsystem.model.Student" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
nav {
  max-width: 90%;
  mask-image: linear-gradient(90deg, rgba(255, 255, 255, .8) 0%, #ffffff 25%, #ffffff 75%, rgba(255, 255, 255, 0.8) 100%);
  margin: 0 auto;
  padding: 20px 0;
  box-shadow: 0 0 25px rgba(0, 0, 0, 0.1), inset 0 0 1px rgba(255, 255, 255, 0.6);
}
nav ul {  background: linear-gradient(90deg, rgba(255, 255, 255, 0) 0%, rgba(255, 255, 255, 0.2) 25%, rgba(255, 255, 255, 0.2) 75%, rgba(255, 255, 255, 0) 100%);
  box-shadow: 0 0 25px rgba(0, 0, 0, 0.1), inset 0 0 1px rgba(255, 255, 255, 0.6);
    text-align: center;
  padding: 0;
  margin: 0;
  list-style: none;
}
nav ul li {display: inline-block;
}
nav ul li a {
  padding: 10px 15px;
  text-transform: uppercase;
  color: black;
  font-size: 18px;
  text-decoration: none;
  display: block;
}
nav ul li a:hover {
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1), inset 0 0 1px rgba(255, 255, 255, 0.6);
  background: rgba(255, 255, 255, 0.3);
  color: red;
}
        .container {
  min-width: 600px;
  min-height: 400px;
  margin: 0 auto;
  margin-bottom: 30px;
  border-radius: 10px; /* Set rounded corners */
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2); /* Set black soft shadow */
  padding: 30px;
   padding-left: 50px;
   background: #8cb38045;
}
.rounded-input {
    padding: 10px;
    border: 1px solid #0026ff;
    border-radius: 8px;
}
        .form-group { margin: 15px 0; }
        .form-group label { display: block; }
        .form-group input { width: 50%; padding: 8px; }
        .btn { padding: 10px 15px; background: #007BFF; color: #fff; border: none; cursor: pointer; }
        .btn:hover { background: #0056b3; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        table, th, td { border: 1px solid white; }
        th, td { padding: 10px; text-align: left; }
        /* Add styles from second form */
        .form-container { margin-top: 40px; margin-bottom: 40px; }
        #dob { color: orange; }
        label { color: green; font-size: 17px; }
        .form-control { background-color: #1c7bd0; color: green; }
        .form-control::placeholder { color: orange; }
        .form-control:focus { background-color: #1c7bd0; color: green; }
        h1 { margin-bottom: 40px; color: green; }
        button { margin-top: 30px; }
        .btn { background-color: orange; color: green; }
        table th, td { color: green; text-align: center; }
        .radio-group { display: flex; align-items: center;
	}.radio-group input[type="radio"] {
  margin-right: 5px;
  width: 5%;
}

.radio-group label {
  display: block;
  margin-bottom: 5px;
}
.main-content {
  display: flex;
  flex-grow: 1;
}
    </style>
</head>
<body>
<div class="main-content">
    <div id="left-pane">
        <ul>
		    <li><a href="#add" onclick="loadPagec('/Smss/web/includes/main/student/addStudent.html'); return false;">Add Student</a></li>
		    <li><a href="#update" onclick="loadPagec('/Smss/web/includes/main/student/updateStudent.html'); return false;">Update Student</a></li>
		    <li><a href="#delete" onclick="loadPagec('/Smss/web/includes/main/student/deleteStudent.html'); return false;">Delete Student</a></li>
		    <li><a href="#getst" onclick="loadPagec('/Smss/web/includes/main/student/getStudentById.html'); return false;">Get Student by ID</a></li>
		    <li><a href="#getall" onclick="loadPagec('/Smss/web/includes/main/student/getAllStudents.jsp'); return false;">Get All Students</a></li>
		</ul>

    </div>
    <div id='container' class='container'></div>
    
    </div>
</body>
</html>
