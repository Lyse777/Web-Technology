package web.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import web.Dao.StudentDao;
import web.Model.Student;

@WebServlet("/registerStudent")
public class RegisterStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        Student student = new Student(firstName, lastName, email, password);
        StudentDao studentDao = new StudentDao();
        studentDao.registerStudent(student);
        
        response.sendRedirect("login.html"); 
    }
}
