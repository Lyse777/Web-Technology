<%@ page import="java.util.List" %>
<%@ page import="web.Model.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <style>
        body, html {
            height: 100%;
            margin: 0;
            font-family: 'Arial', sans-serif;
            background-image: url('24.jpg');
            background-size: cover;
            background-position: center;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .form-container {
            background: rgba(255, 255, 255, 0.8); 
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 600px; 
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .btn1, .btn2 {
            padding: 8px 15px;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            text-decoration: none;
            display: inline-block;
        }
        .btn1 {
            background-color: #D31919;
        }
        .btn1:hover {
            background-color: #D73C3C;
        }
        .btn2 {
            background-color: #AA21C0;
        }
        .btn2:hover {
            background-color: #AC78B4;
        }
        .btn3 {
            margin-top: 20px;
            padding: 8px 15px; 
            background-color:  #A57000;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            text-decoration: none;
            display: block; 
            width: fit-content;
            margin-left: auto;
            margin-right: auto;
        }
        .btn3:hover {
            background-color: #BA8D2D;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>All Students Registered</h2>
        <a href="login.html" class="btn3">Logout</a>
        <% 
            List<Student> students = (List<Student>) request.getAttribute("students");
            if (students != null && !students.isEmpty()) {
        %>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
            <% 
                for (Student student : students) {
            %>
                <tr>
                    <td><%= student.getId() %></td>
                    <td><%= student.getFirstName() %></td>
                    <td><%= student.getLastName() %></td>
                    <td><%= student.getEmail() %></td>
                    <td>
                        <a href="editStudent?id=<%= student.getId() %>" class="btn2">Update</a> 
                        <a href="deleteStudent?id=${student.id}" class="btn1" onclick="return confirm('Are you sure you want to delete this student?');">Delete</a>
                    </td>
                </tr>
            <% 
                } 
            %>
            </tbody>
        </table>
        <% 
            } else {
        %>
            <p>No students found.</p>
        <% 
            } 
        %>
    </div>
</body>
</html>
