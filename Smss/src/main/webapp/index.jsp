<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Management System by 21BTCSE019)</title>
<style>
html {
  background-image: linear-gradient(to right top, yellow 0%, lime 100%);background-attachment: fixed;
    background-size: cover;
}
body {
  margin: 0;
  padding: 0;
  font-family: "Open Sans", sans-serif;
  display: flex;
  flex-direction: column;
  height: 100vh;
}
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

        .form-group { margin: 15px 0; }
        .form-group label { display: block; }
        .form-group input { width: 50%; padding: 8px; }
        .btn { padding: 10px 15px; background: #007BFF; color: #fff; border: none; cursor: pointer; }
        .btn:hover { background: #0056b3; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        table, th, td { border: 1px solid #ddd; }
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
#left-pane {  overflow-y: auto; /* Add vertical scroll if content overflows */
position: fixed;
height: 90%;
  width: 200px;
  background: #00c6644f;
  padding: 20px;
border-top-right-radius: 50px;
  
}
#left-pane ul {
  list-style: none;
  padding: 0;
}
#left-pane ul li {
  margin: 10px 0;
}
#left-pane ul li a {
  text-decoration: none;
  color: black;
  font-size: 16px;
  display: block;
  padding: 10px;
  background: #aaff90;
  border-radius: 4px;
}
#left-pane ul li a:hover {
  background: #ff22228f;
}
    </style>

</head>
<body>
<%@ include file="web/includes/jsp/navbar.jsp" %>

    <div id='containerx'>c</div>
    
    <script>
    
    function loadPage(url = '/Smss/web/includes/main/home.html') {
        var xhr = new XMLHttpRequest();
        xhr.open('GET', url, true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4 && xhr.status == 200) {
                document.getElementById('containerx').innerHTML = xhr.responseText;
            }
        };
        xhr.send();
    }

    function validateForm() {
        var name = document.getElementById('name').value;
        var studentId = document.getElementById('studentId').value;
        var grade = document.getElementById('grade').value;
        var gender = document.querySelector('input[name="gender"]:checked');
        var contact = document.getElementById('contact').value;
        var email = document.getElementById('email').value;
        var phonePattern = /^[6789]\d{9}$/;

        if (name === "" || studentId === "" || grade === "" || gender === null || contact === "" || email === "") {
            alert("All fields must be filled out");
            return false;
        }
        if (!Number.isInteger(Number(studentId)) || studentId.includes('.')) {
            alert("Student ID should be an integer.");
            return false;
        }
        if (!phonePattern.test(contact)) {
            alert("Invalid phone number. It should start with 6, 7, 8, or 9 and be 10 digits long.");
            return false;
        }

        return true;
    }

    function sendId(url) {
        var id = document.getElementById("id").value;

        var xhr = new XMLHttpRequest();
        xhr.open("GET", url + "?id=" + encodeURIComponent(id), true);

        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById("formContainer").innerHTML = xhr.responseText;
            }
        };

        xhr.send();
    }
    function loadPagec(url) {
        var xhr = new XMLHttpRequest();
        xhr.open('GET', url, true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4 && xhr.status == 200) {
                document.getElementById('container').innerHTML = xhr.responseText;
            }
        };
        xhr.send();
    }

    // Load the default page when the window loads
    window.onload = function() {
        loadPage();
    };


</script>
</body>
</html>
