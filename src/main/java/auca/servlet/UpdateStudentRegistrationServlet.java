package auca.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import auca.dao.AcademicUnitDAO;
import auca.dao.SemesterDAO;
import auca.dao.StudentDAO;
import auca.dao.StudentRegistrationDAO;
import auca.model.AcademicUnit;
import auca.model.Semester;
import auca.model.Student;
import auca.model.StudentRegistration;

@WebServlet(name = "UpdateStudentRegistrationServlet", urlPatterns = {"/UpdateStudentRegistrationServlet"})
public class UpdateStudentRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Long registrationId = Long.parseLong(request.getParameter("registrationId"));

        StudentRegistrationDAO studentRegistrationDAO = new StudentRegistrationDAO();
        StudentRegistration studentRegistration = studentRegistrationDAO.getStudentRegistrationById(registrationId);


        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Update Student Registration</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; }");
        out.println("form { margin: 20px; max-width: 400px; border: 1px solid #ccc; padding: 15px; }");
        out.println("label { display: block; margin-bottom: 5px; }");
        out.println("input[type=text], input[type=number], input[type=datetime-local] { width: calc(100% - 22px); padding: 5px; margin-bottom: 10px; }");
        out.println("input[type=submit] { background-color: #4CAF50; color: white; padding: 10px; border: none; cursor: pointer; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Update Student Registration</h2>");
        out.println("<form action=\"UpdateStudentRegistrationServlet\" method=\"post\">");
        out.println("<input type=\"hidden\" name=\"registrationId\" value=\"" + studentRegistration.getRegistrationId() + "\">");
        out.println("<label for=\"registrationCode\">Registration Code:</label>");
        out.println("<input type=\"text\" id=\"registrationCode\" name=\"registrationCode\" value=\"" + studentRegistration.getRegistrationCode() + "\" required><br>");
        out.println("<label for=\"registrationDate\">Registration Date:</label>");
        out.println("<input type=\"datetime-local\" id=\"registrationDate\" name=\"registrationDate\" value=\"" + studentRegistration.getRegistrationDate().toString().replace("T", " ") + "\" required><br>");
        out.println("<label for=\"studentId\">Student ID:</label>");
        out.println("<input type=\"number\" id=\"studentId\" name=\"studentId\" value=\"" + studentRegistration.getStudent().getStudentId() + "\" required><br>");
        out.println("<label for=\"semesterId\">Semester ID:</label>");
        out.println("<input type=\"number\" id=\"semesterId\" name=\"semesterId\" value=\"" + studentRegistration.getSemester().getSemesterId() + "\" required><br>");
        out.println("<label for=\"departmentId\">Department ID:</label>");
        out.println("<input type=\"number\" id=\"departmentId\" name=\"departmentId\" value=\"" + studentRegistration.getDepartment().getAcademicId() + "\" required><br>");
        out.println("<input type=\"submit\" value=\"Update\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long registrationId = Long.parseLong(request.getParameter("registrationId"));
        String registrationCode = request.getParameter("registrationCode");
        LocalDateTime registrationDate = LocalDateTime.parse(request.getParameter("registrationDate"));
        Long studentId = Long.parseLong(request.getParameter("studentId"));
        Integer semesterId = Integer.parseInt(request.getParameter("semesterId"));
        Long departmentId = Long.parseLong(request.getParameter("departmentId"));

        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.getStudentById(studentId);

        SemesterDAO semesterDAO = new SemesterDAO();
        Semester semester = semesterDAO.getSemesterById(semesterId);

        AcademicUnitDAO academicUnitDAO = new AcademicUnitDAO();
        AcademicUnit department = academicUnitDAO.getAcademicUnitById(departmentId);

        StudentRegistration studentRegistration = new StudentRegistration(registrationCode, registrationDate, student, semester, department);
        studentRegistration.setRegistrationId(registrationId);

        StudentRegistrationDAO studentRegistrationDAO = new StudentRegistrationDAO();
        studentRegistrationDAO.updateStudentRegistration(studentRegistration);

        response.sendRedirect("DisplayStudentRegistrationsServlet");
    }
}