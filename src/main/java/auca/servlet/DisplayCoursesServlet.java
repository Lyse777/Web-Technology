package auca.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import auca.dao.CourseDAO;
import auca.model.Course;

@WebServlet(name = "DisplayCoursesServlet", urlPatterns = {"/DisplayCoursesServlet"})
public class DisplayCoursesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Display Courses</title>");
        out.println("<style>");
        out.println("table { width: 50%; border-collapse: collapse; margin-bottom: 20px; }");
        out.println("th, td { padding: 8px; text-align: left; border-bottom: 1px solid #ddd; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println("tr:hover { background-color: #f5f5f5; }");
        out.println(".action-btns { display: flex; }");
        out.println(".action-btns button.delete-btn { margin-right: 5px; padding: 6px 10px; background-color: #ff3333; color: white; border: none; cursor: pointer; }");
        out.println(".action-btns button.update-btn { padding: 6px 10px; background-color: #3377ff; color: white; border: none; cursor: pointer; }");
        out.println(".action-btns button:hover { opacity: 0.8; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Display Courses</h2>");

        CourseDAO courseDAO = new CourseDAO();
        List<Course> courses = courseDAO.getAllCourses();

        if (courses.isEmpty()) {
            out.println("<p>No courses found.</p>");
        } else {
            out.println("<table>");
            out.println("<tr><th>Course Code</th><th>Course Name</th><th>Semester ID</th><th>Actions</th></tr>");
            for (Course course : courses) {
                out.println("<tr>");
                out.println("<td>" + course.getCourseCode() + "</td>");
                out.println("<td>" + course.getCourseName() + "</td>");
                out.println("<td>" + course.getSemester().getSemesterId() + "</td>");
                out.println("<td class=\"action-btns\">");
                out.println("<form action=\"DeleteCourseServlet\" method=\"post\">");
                out.println("<input type=\"hidden\" name=\"courseId\" value=\"" + course.getCourseId() + "\">");
                out.println("<button class=\"delete-btn\" type=\"submit\">Delete</button>");
                out.println("</form>");
                out.println("<form action=\"UpdateCourseServlet\" method=\"get\">");
                out.println("<input type=\"hidden\" name=\"courseId\" value=\"" + course.getCourseId() + "\">");
                out.println("<button class=\"update-btn\" type=\"submit\">Update</button>");
                out.println("</form>");
                out.println("</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}
