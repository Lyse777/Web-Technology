package auca.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import auca.dao.CourseDAO;

@WebServlet(name = "DeleteCourseServlet", urlPatterns = {"/DeleteCourseServlet"})
public class DeleteCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CourseDAO courseDAO;

    public DeleteCourseServlet() {
        super();
        this.courseDAO = new CourseDAO();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String courseIdStr = request.getParameter("courseId");
            if (courseIdStr != null && !courseIdStr.isEmpty()) {
                int courseId = Integer.parseInt(courseIdStr);
                // Calling my DAO method to delete the course using courseId
                courseDAO.deleteCourseById(courseId);
                response.sendRedirect("DisplayCoursesServlet"); 
            } else {
                // Handling the case where the parameter is missing or empty
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Invalid course ID");
            }
        } catch (NumberFormatException e) {
            // Handling the case where the input string is not a valid number
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Invalid course ID format");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forwarding doPost requests to doDelete method for handling deletion
        doDelete(request, response);
    }
}
