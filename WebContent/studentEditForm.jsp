<%@ page import="web.Model.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Student</title>
 <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="form-container">
        <form action="updateStudent" method="post" class="my-form">
            <h2>Update Student</h2>
            <input type="hidden" name="id" value="${student.id}" />
            <div class="form-control">
                <label for="firstName">First Name:</label>
                <input type="text" id="firstName" name="firstName" value="${student.firstName}" />
            </div>
            <div class="form-control">
                <label for="lastName">Last Name:</label>
                <input type="text" id="lastName" name="lastName" value="${student.lastName}" />
            </div>
            <div class="form-control">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${student.email}" />
            </div>
            <div class="form-control">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" value="${student.password}" />
            </div>
               <input type="submit" value="Update Student" class="btn" />
        </form>
        
    </div>
</body>
</html>
