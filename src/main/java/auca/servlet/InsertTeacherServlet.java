package auca.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auca.dao.CourseDAO;
import auca.dao.TeacherDAO;
import auca.model.Course;
import auca.model.EQualification;
import auca.model.ETutorRole;
import auca.model.Teacher;

import java.io.IOException;

@WebServlet("/AddTeacherServlet")
public class InsertTeacherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String qualificationStr = request.getParameter("qualification");
        EQualification qualification = EQualification.valueOf(qualificationStr);
        String courseIdStr = request.getParameter("courseId");
        Integer courseId = null;
        try {
            courseId = Integer.parseInt(courseIdStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("teacherform.html?error=invalidCourseId");
            return;
        }
        CourseDAO courseDAO = new CourseDAO();
        Course course = courseDAO.getCourseById(courseId);
        if (course == null) {
            response.sendRedirect("teacherform.html?error=courseNotFound");
            return;
        }
        String tutorRoleStr = request.getParameter("tutorRole");
        ETutorRole tutorRole = ETutorRole.valueOf(tutorRoleStr);

        Teacher teacher = new Teacher(firstName, lastName, qualification, course, tutorRole);
        TeacherDAO teacherDAO = new TeacherDAO();
        teacherDAO.InsertTeacher(teacher);

        response.sendRedirect("teacherform.html");
    }
}
