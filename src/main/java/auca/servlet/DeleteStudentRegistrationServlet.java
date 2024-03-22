package auca.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import auca.dao.StudentRegistrationDAO;

@WebServlet(name = "DeleteStudentRegistrationServlet", urlPatterns = {"/DeleteStudentRegistrationServlet"})
public class DeleteStudentRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final StudentRegistrationDAO studentRegistrationDAO;

    public DeleteStudentRegistrationServlet() {
        super();
        this.studentRegistrationDAO = new StudentRegistrationDAO();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String registrationIdStr = request.getParameter("registrationId");
            if (registrationIdStr != null && !registrationIdStr.isEmpty()) {
                Long registrationId = Long.parseLong(registrationIdStr);
                // Calling my DAO method to delete the student registration using registrationId
                studentRegistrationDAO.deleteStudentRegistration(registrationId);
                response.sendRedirect("DisplayStudentRegistrationsServlet"); 
            } else {
                // Handling the case where the parameter is missing or empty
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Invalid registration ID");
            }
        } catch (NumberFormatException e) {
            // Handling the case where the input string is not a valid number
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Invalid registration ID format");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forwarding doPost requests to doDelete method for handling deletion
        doDelete(request, response);
    }
}
