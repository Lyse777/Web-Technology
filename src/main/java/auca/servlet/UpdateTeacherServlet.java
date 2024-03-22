package auca.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

import auca.dao.CourseDAO;
import auca.dao.TeacherDAO;
import auca.model.Course;
import auca.model.EQualification;
import auca.model.ETutorRole;
import auca.model.Teacher;

@WebServlet(name = "UpdateTeacherServlet", urlPatterns = {"/UpdateTeacherServlet"})
public class UpdateTeacherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Long teacherId = Long.parseLong(request.getParameter("teacherId"));

        TeacherDAO teacherDAO = new TeacherDAO();
        Teacher teacher = teacherDAO.getTeacherById(teacherId);

        CourseDAO courseDAO = new CourseDAO();
        Course course = courseDAO.getCourseById(teacher.getCourse().getCourseId());

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Update Teacher</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; }");
        out.println("form { margin: 20px; max-width: 400px; border: 1px solid #ccc; padding: 15px; }");
        out.println("label { display: block; margin-bottom: 5px; }");
        out.println("input[type=text], input[type=number], select { width: calc(100% - 22px); padding: 5px; margin-bottom: 10px; }");
        out.println("input[type=submit] { background-color: #4CAF50; color: white; padding: 10px; border: none; cursor: pointer; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Update Teacher</h2>");
        out.println("<form action=\"UpdateTeacherServlet\" method=\"post\">");
        out.println("<input type=\"hidden\" name=\"teacherId\" value=\"" + teacher.getTeacherId() + "\">");
        out.println("<label for=\"firstName\">First Name:</label>");
        out.println("<input type=\"text\" id=\"firstName\" name=\"firstName\" value=\"" + teacher.getFirstName() + "\" required><br>");
        out.println("<label for=\"lastName\">Last Name:</label>");
        out.println("<input type=\"text\" id=\"lastName\" name=\"lastName\" value=\"" + teacher.getLastName() + "\" required><br>");
        out.println("<label for=\"qualification\">Qualification:</label>");
        out.println("<select id=\"qualification\" name=\"qualification\" required>");
        out.println("<option value=\"MASTER\"" + (teacher.getQualification() == EQualification.MASTER ? " selected" : "") + ">MASTER</option>");
        out.println("<option value=\"PHD\"" + (teacher.getQualification() == EQualification.PHD ? " selected" : "") + ">PHD</option>");
        out.println("<option value=\"PROFESSOR\"" + (teacher.getQualification() == EQualification.PROFESSOR ? " selected" : "") + ">PROFESSOR</option>");
        out.println("</select><br>");
        out.println("<label for=\"courseId\">Course ID:</label>");
        out.println("<input type=\"number\" id=\"courseId\" name=\"courseId\" value=\"" + course.getCourseId() + "\" required><br>");
        out.println("<label for=\"tutorRole\">Tutor Role:</label>");
        out.println("<select id=\"tutorRole\" name=\"tutorRole\" required>");
        out.println("<option value=\"TUTOR\"" + (teacher.getTutorRole() == ETutorRole.TUTOR ? " selected" : "") + ">TUTOR</option>");
        out.println("<option value=\"ASSISTANT_TUTOR\"" + (teacher.getTutorRole() == ETutorRole.ASSISTANT_TUTOR ? " selected" : "") + ">ASSISTANT_TUTOR</option>");
        out.println("</select><br>");
        out.println("<input type=\"submit\" value=\"Update\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long teacherId = Long.parseLong(request.getParameter("teacherId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        EQualification qualification = EQualification.valueOf(request.getParameter("qualification"));
        Integer courseId = Integer.parseInt(request.getParameter("courseId"));
        ETutorRole tutorRole = ETutorRole.valueOf(request.getParameter("tutorRole"));

        CourseDAO courseDAO = new CourseDAO();
        Course course = courseDAO.getCourseById(courseId);

        Teacher teacher = new Teacher(firstName, lastName, qualification, course, tutorRole);
        teacher.setTeacherId(teacherId);

        TeacherDAO teacherDAO = new TeacherDAO();
        teacherDAO.updateTeacher(teacher);

        response.sendRedirect("DisplayTeacherServlet");
    }
}
