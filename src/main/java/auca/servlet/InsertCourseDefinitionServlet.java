package auca.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import auca.dao.CourseDAO;
import auca.dao.CourseDefinitionDAO;
import auca.model.Course;
import auca.model.CourseDefinition;
import java.io.IOException;

@WebServlet("/AddCourseDefinitionServlet")
public class InsertCourseDefinitionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseDefinitionCode = request.getParameter("courseDefinitionCode");
        String courseDefinitionDescription = request.getParameter("courseDefinitionDescription");
        String courseIdParam = request.getParameter("courseId");

        Integer courseId = null;
        try {
            courseId = Integer.parseInt(courseIdParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("coursedefinitionform.html?error=invalidCourseId");
            return;
        }

        CourseDAO courseDAO = new CourseDAO();
        Course course = courseDAO.getCourseById(courseId);

        if (course == null) {
            response.sendRedirect("coursedefinitionform.html?error=courseNotFound");
            return;
        }

        CourseDefinition courseDefinition = new CourseDefinition(courseDefinitionCode, courseDefinitionDescription, course);

        CourseDefinitionDAO courseDefinitionDAO = new CourseDefinitionDAO();
        courseDefinitionDAO.InsertCourseDefinition(courseDefinition);

        response.sendRedirect("coursedefinitionform.html");
    }
}