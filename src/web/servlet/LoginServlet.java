package web.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import web.Dao.StudentDao;
import web.Model.Student;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.authenticateStudent(email, password);
        
        if (student != null) {
        
            HttpSession session = request.getSession();
            session.setAttribute("student", student);
            response.sendRedirect("dashboard"); 
        } else {
            request.setAttribute("errorMessage", "Invalid email or password");
            request.getRequestDispatcher("login.html").forward(request, response);
        }
    }
}
