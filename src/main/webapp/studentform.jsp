<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="auca.model.Course" %>
<%@ page import="auca.dao.CourseDAO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Apply-Student</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-image: url('w.jpg'); 
            background-size: cover;
            background-position: center;
            color: #333; 
        }

        .container {
            max-width: 500px;
            margin: 50px auto;
            padding: 20px;
            background-color: rgba(255, 255, 255, 0.9); 
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 20px; 
            color: black; 
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold; 
        }

        input[type="text"],
        input[type="date"],
        select,
        button {
            width: calc(100% - 24px); 
            padding: 12px; 
            margin-bottom: 20px; 
            border-radius: 5px;
            border: 1px solid #ccc;
            box-sizing: border-box;
            transition: border-color 0.3s ease; 
        }

        input[type="text"]:focus,
        input[type="date"]:focus,
        select:focus {
            border-color: #007bff; 
        }

        button {
            width: 100%;
            background-color: #9C850F;
            color: #fff;
            font-weight:bold;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s ease; 
        }

        button:hover {
            background-color: #AE961F;
        }

        .error-message {
            color: #dc3545; 
            margin-top: 5px;
        }
    </style>
</head>
<body>
   <div class="container">
        <h2>Student Application</h2>
        <form id="studentForm" action="addStudent" method="post">
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" placeholder="Enter First Name" required>

            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" placeholder="Enter Last Name" required>

            <label for="dateOfBirth">Date of Birth:</label>
            <input type="date" id="dateOfBirth" name="dateOfBirth" required>

           <label for="academicUnitId">Academic Unit ID:</label>
<select id="academicUnitId" name="academicUnitId" required>
    <option value="disabled">Information Technology (IT)</option>
    <option value="1">Software Engineering</option>
    <option value="2">Information Management</option>
    <option value="3">Networking and Communication</option>
</select>


            <label for="courses">Courses:</label>
            <% 
                CourseDAO courseDAO = new CourseDAO();
                List<Course> courses = courseDAO.getAllCourses();
            %>
            <select id="courses" name="courseId" multiple required>
                <% for (Course course : courses) { %>
                    <option value="<%= course.getCourseId() %>"><%= course.getCourseName() %></option>
                <% } %>
            </select>

            <button type="submit">Submit</button>
        </form>
    </div>
</body>
</html>
