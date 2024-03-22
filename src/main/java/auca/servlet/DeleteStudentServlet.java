package auca.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import org.hibernate.Transaction;
import org.hibernate.Session;

import javax.servlet.annotation.*;
import java.io.IOException;
import auca.dao.StudentDAO;
import auca.util.HibernateUtil;

@WebServlet(name = "DeleteStudentServlet", urlPatterns = {"/DeleteStudentServlet"})
public class DeleteStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final StudentDAO studentDAO;

    public DeleteStudentServlet() {
        super();
        this.studentDAO = new StudentDAO();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String studentIdStr = request.getParameter("studentId");
            if (studentIdStr != null && !studentIdStr.isEmpty()) {
                Long studentId = Long.parseLong(studentIdStr);
                Session session = HibernateUtil.getSessionFactory().openSession();
                Transaction transaction = session.beginTransaction();

                // Calling my DAO method to delete the student and related records
                studentDAO.deleteStudent(studentId);

                // Committing transaction and close session
                transaction.commit();
                session.close();

                response.sendRedirect("DisplayStudentsServlet"); 
            } else {
                // Handling the case where the parameter is missing or empty
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Invalid student ID");
            }
        } catch (NumberFormatException e) {
            // Handling the case where the input string is not a valid number
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Invalid student ID format");
        } catch (Exception e) {
            // Handling other exceptions
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error deleting student: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forwarding doPost requests to doDelete method for handling deletion
        doDelete(request, response);
    }
}