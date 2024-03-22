package auca.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import auca.dao.CourseDAO;
import auca.dao.SemesterDAO;
import auca.model.Course;
import auca.model.Semester;
import java.io.IOException;

@WebServlet("/AddCourseServlet")
public class InsertCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseCode = request.getParameter("courseCode");
        String courseName = request.getParameter("courseName");
        String semesterId = request.getParameter("semesterId");

        if (courseCode == null || courseCode.trim().isEmpty() || courseName == null || courseName.trim().isEmpty() || semesterId == null || semesterId.trim().isEmpty()) {
            request.setAttribute("error", "Course code, name, and semester ID are required.");
            request.getRequestDispatcher("courseform.html").forward(request, response);
            return;
        }

        SemesterDAO semesterDAO = new SemesterDAO();
        Semester semester = null;
        try {
            semester = semesterDAO.getSemesterById(Integer.parseInt(semesterId));
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid semester ID format.");
            request.getRequestDispatcher("courseform.html").forward(request, response);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while retrieving the semester.");
            request.getRequestDispatcher("courseform.html").forward(request, response);
            return;
        }

        if (semester == null) {
            request.setAttribute("error", "Semester not found for the provided ID.");
            request.getRequestDispatcher("courseform.html").forward(request, response);
            return;
        }

        Course course = new Course(courseCode, courseName, semester);
        CourseDAO courseDAO = new CourseDAO();
        courseDAO.InsertCourse(course);

        response.sendRedirect("courseform.html");
    }
}

