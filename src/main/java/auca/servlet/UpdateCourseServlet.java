package auca.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

import auca.dao.CourseDAO;
import auca.dao.SemesterDAO;
import auca.model.Course;
import auca.model.Semester;

@WebServlet(name = "UpdateCourseServlet", urlPatterns = {"/UpdateCourseServlet"})
public class UpdateCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Integer courseId = Integer.parseInt(request.getParameter("courseId"));

        CourseDAO courseDAO = new CourseDAO();
        Course course = courseDAO.getCourseById(courseId);

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Update Course</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; }");
        out.println("form { margin: 20px; max-width: 400px; border: 1px solid #ccc; padding: 15px; }");
        out.println("label { display: block; margin-bottom: 5px; }");
        out.println("input[type=text] { width: calc(100% - 22px); padding: 5px; margin-bottom: 10px; }");
        out.println("input[type=submit] { background-color: #4CAF50; color: white; padding: 10px; border: none; cursor: pointer; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Update Course</h2>");
        out.println("<form action=\"UpdateCourseServlet\" method=\"post\">");
        out.println("<input type=\"hidden\" name=\"courseId\" value=\"" + course.getCourseId() + "\">");
        out.println("<label for=\"courseCode\">Course Code:</label>");
        out.println("<input type=\"text\" id=\"courseCode\" name=\"courseCode\" value=\"" + course.getCourseCode() + "\" required><br>");
        out.println("<label for=\"courseName\">Course Name:</label>");
        out.println("<input type=\"text\" id=\"courseName\" name=\"courseName\" value=\"" + course.getCourseName() + "\" required><br>");
        out.println("<label for=\"semesterName\">Semester Name:</label>");
        out.println("<input type=\"text\" id=\"semesterName\" name=\"semesterName\" value=\"" + course.getSemester().getSemesterName() + "\" required><br>");
        out.println("<input type=\"submit\" value=\"Update\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer courseId = Integer.parseInt(request.getParameter("courseId"));
        String courseCode = request.getParameter("courseCode");
        String courseName = request.getParameter("courseName");
        String semesterName = request.getParameter("semesterName");

        Course course = new Course(courseCode, courseName, null);
        course.setCourseId(courseId);

        if (semesterName != null && !semesterName.isEmpty()) {
            SemesterDAO semesterDAO = new SemesterDAO();
            Semester semester = semesterDAO.getSemesterByName(semesterName);

            if (semester == null) {
                // Creates a new semester if it doesn't exist
                semester = new Semester();
                semester.setSemesterName(semesterName);
                semesterDAO.InsertSemester(semester);
            }

            course.setSemester(semester);
        }

        CourseDAO courseDAO = new CourseDAO();
        courseDAO.updateCourse(course);

        response.sendRedirect("DisplayCoursesServlet");
    }
}
