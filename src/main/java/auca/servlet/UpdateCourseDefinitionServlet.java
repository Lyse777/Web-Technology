package auca.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

import auca.dao.CourseDAO;
import auca.dao.CourseDefinitionDAO;
import auca.model.Course;
import auca.model.CourseDefinition;

@WebServlet(name = "UpdateCourseDefinitionServlet", urlPatterns = {"/UpdateCourseDefinitionServlet"})
public class UpdateCourseDefinitionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Integer courseDefinitionId = Integer.parseInt(request.getParameter("courseDefinitionId"));

        CourseDefinitionDAO courseDefinitionDAO = new CourseDefinitionDAO();
        CourseDefinition courseDefinition = courseDefinitionDAO.getCourseDefinitionById(courseDefinitionId);

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Update Course Definition</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; }");
        out.println("form { margin: 20px; max-width: 400px; border: 1px solid #ccc; padding: 15px; }");
        out.println("label { display: block; margin-bottom: 5px; }");
        out.println("input[type=text] { width: calc(100% - 22px); padding: 5px; margin-bottom: 10px; }");
        out.println("input[type=submit] { background-color: #4CAF50; color: white; padding: 10px; border: none; cursor: pointer; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Update Course Definition</h2>");
        out.println("<form action=\"UpdateCourseDefinitionServlet\" method=\"post\">");
        out.println("<input type=\"hidden\" name=\"courseDefinitionId\" value=\"" + courseDefinition.getCourseDefinitionId() + "\">");
        out.println("<label for=\"courseDefinitionCode\">Definition Code:</label>");
        out.println("<input type=\"text\" id=\"courseDefinitionCode\" name=\"courseDefinitionCode\" value=\"" + courseDefinition.getCourseDefinitionCode() + "\" required><br>");
        out.println("<label for=\"courseDefinitionDescription\">Description:</label>");
        out.println("<input type=\"text\" id=\"courseDefinitionDescription\" name=\"courseDefinitionDescription\" value=\"" + courseDefinition.getCourseDefinitionDescription() + "\" required><br>");
        out.println("<label for=\"courseId\">Course:</label>");
        out.println("<input type=\"text\" id=\"courseId\" name=\"courseId\" value=\"" + courseDefinition.getCourse().getCourseId() + "\" required><br>");
        out.println("<input type=\"submit\" value=\"Update\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer courseDefinitionId = Integer.parseInt(request.getParameter("courseDefinitionId"));
        String courseDefinitionCode = request.getParameter("courseDefinitionCode");
        String courseDefinitionDescription = request.getParameter("courseDefinitionDescription");
        Integer courseId = Integer.parseInt(request.getParameter("courseId"));

        CourseDAO courseDAO = new CourseDAO();
        Course course = courseDAO.getCourseById(courseId);

        CourseDefinition courseDefinition = new CourseDefinition(courseDefinitionCode, courseDefinitionDescription, course);
        courseDefinition.setCourseDefinitionId(courseDefinitionId);

        CourseDefinitionDAO courseDefinitionDAO = new CourseDefinitionDAO();
        courseDefinitionDAO.updateCourseDefinition(courseDefinition);

        response.sendRedirect("DisplayCourseDefinitionsServlet");
    }
}
