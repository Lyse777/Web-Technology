package web.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import web.Dao.StudentDao;
import web.Model.Student;

@WebServlet("/updateStudent")
public class UpdateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            Student student = new Student(firstName, lastName, email, password);
            student.setId(id); 

            StudentDao studentDao = new StudentDao();
            studentDao.updateStudent(student);

            response.sendRedirect("dashboard");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("editStudent?id=" + request.getParameter("id")); 
        }
    }
}
