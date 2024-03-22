package auca.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.HashSet;
import java.util.Set;

import auca.dao.AcademicUnitDAO;
import auca.dao.CourseDAO;
import auca.dao.StudentDAO;
import auca.model.AcademicUnit;
import auca.model.Course;
import auca.model.Student;

@WebServlet(name = "UpdateStudentServlet", urlPatterns = {"/UpdateStudentServlet"})
public class UpdateStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Long studentId = Long.parseLong(request.getParameter("studentId"));

        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.getStudentById(studentId);

        CourseDAO courseDAO = new CourseDAO();

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Update Student</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; }");
        out.println("form { margin: 20px; max-width: 400px; border: 1px solid #ccc; padding: 15px; }");
        out.println("label { display: block; margin-bottom: 5px; }");
        out.println("input[type=text], input[type=date], select { width: calc(100% - 22px); padding: 5px; margin-bottom: 10px; }");
        out.println("input[type=submit] { background-color: #4CAF50; color: white; padding: 10px; border: none; cursor: pointer; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Update Student</h2>");
        out.println("<form action=\"UpdateStudentServlet\" method=\"post\">");
        out.println("<input type=\"hidden\" name=\"studentId\" value=\"" + student.getStudentId() + "\">");
        out.println("<label for=\"firstName\">First Name:</label>");
        out.println("<input type=\"text\" id=\"firstName\" name=\"firstName\" value=\"" + student.getFirstName() + "\" required><br>");
        out.println("<label for=\"lastName\">Last Name:</label>");
        out.println("<input type=\"text\" id=\"lastName\" name=\"lastName\" value=\"" + student.getLastName() + "\" required><br>");
        out.println("<label for=\"dateOfBirth\">Date of Birth:</label>");
        out.println("<input type=\"date\" id=\"dateOfBirth\" name=\"dateOfBirth\" value=\"" + student.getDateOfBirth() + "\" required><br>");
        out.println("<label for=\"academicUnitId\">Academic Unit ID:</label>");
        out.println("<input type=\"text\" id=\"academicUnitId\" name=\"academicUnitId\" value=\"" + student.getAcademicUnit().getDepartmentId() + "\" required><br>");
        out.println("<label for=\"courses\">Courses:</label>");
        out.println("<select id=\"courses\" name=\"courseId\" multiple required>");
        for (Course course : courseDAO.getAllCourses()) {
            boolean selected = student.getCourses().contains(course);
            out.println("<option value=\"" + course.getCourseId() + "\"" + (selected ? " selected" : "") + ">" + course.getCourseName() + "</option>");
        }
        out.println("</select><br>");
        out.println("<input type=\"submit\" value=\"Update\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long studentId = Long.parseLong(request.getParameter("studentId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dateOfBirth = request.getParameter("dateOfBirth");
        Long academicUnitId = Long.parseLong(request.getParameter("academicUnitId"));
        String[] courseIds = request.getParameterValues("courseId");

        AcademicUnitDAO academicUnitDAO = new AcademicUnitDAO();
        AcademicUnit academicUnit = academicUnitDAO.getAcademicUnitById(academicUnitId);

        CourseDAO courseDAO = new CourseDAO();
        Set<Course> courses = new HashSet<>();
        if (courseIds != null) {
            for (String courseId : courseIds) {
                Course course = courseDAO.getCourseById(Integer.parseInt(courseId));
                courses.add(course);
            }
        }

        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.getStudentById(studentId);
        if (student != null) {
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setDateOfBirth(dateOfBirth);
            student.setAcademicUnit(academicUnit);
            student.setCourses(courses);

            studentDAO.updateStudent(student);

            response.sendRedirect("DisplayStudentsServlet");
        } else {
         
            response.getWriter().println("Error: Student not found.");
        }
    }

}