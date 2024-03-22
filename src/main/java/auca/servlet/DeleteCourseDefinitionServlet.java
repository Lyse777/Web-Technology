package auca.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import auca.dao.CourseDefinitionDAO;

@WebServlet(name = "DeleteCourseDefinitionServlet", urlPatterns = {"/DeleteCourseDefinitionServlet"})
public class DeleteCourseDefinitionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CourseDefinitionDAO courseDefinitionDAO;

    public DeleteCourseDefinitionServlet() {
        super();
        this.courseDefinitionDAO = new CourseDefinitionDAO();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String courseDefinitionIdStr = request.getParameter("courseDefinitionId");
            if (courseDefinitionIdStr != null && !courseDefinitionIdStr.isEmpty()) {
                int courseDefinitionId = Integer.parseInt(courseDefinitionIdStr);
                // Calling my DAO method to delete the course definition using courseDefinitionId
                courseDefinitionDAO.deleteCourseDefinitionById(courseDefinitionId);
                response.sendRedirect("DisplayCourseDefinitionsServlet"); 
            } else {
                // Handling the case where the parameter is missing or empty
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Invalid course definition ID");
            }
        } catch (NumberFormatException e) {
            // Handling the case where the input string is not a valid number
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Invalid course definition ID format");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forwarding doPost requests to doDelete method for handling deletion
        doDelete(request, response);
    }
}
