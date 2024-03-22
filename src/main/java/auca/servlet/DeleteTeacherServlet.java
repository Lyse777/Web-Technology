package auca.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import auca.dao.TeacherDAO;

@WebServlet(name = "DeleteTeacherServlet", urlPatterns = {"/DeleteTeacherServlet"})
public class DeleteTeacherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final TeacherDAO teacherDAO;

    public DeleteTeacherServlet() {
        super();
        this.teacherDAO = new TeacherDAO();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String teacherIdStr = request.getParameter("teacherId");
            if (teacherIdStr != null && !teacherIdStr.isEmpty()) {
                // Validating if teacherIdStr is a valid long value
                try {
                    long teacherId = Long.parseLong(teacherIdStr);
                    // Calling my DAO method to delete the teacher using teacherId
                    teacherDAO.deleteTeacherById(teacherId);
                    response.sendRedirect("DisplayTeacherServlet"); 
                } catch (NumberFormatException e) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().println("Invalid teacher ID format");
                }
            } else {
                // Handling the case where the parameter is missing or empty
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Invalid teacher ID");
            }
        } catch (Exception e) {
            // Handling any other exceptions
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Failed to delete teacher: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forwarding doPost requests to doDelete method for handling deletion
        doDelete(request, response);
    }
}
