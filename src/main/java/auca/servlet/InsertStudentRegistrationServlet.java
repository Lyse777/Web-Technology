package auca.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import auca.dao.AcademicUnitDAO;
import auca.dao.SemesterDAO;
import auca.dao.StudentDAO;
import auca.dao.StudentRegistrationDAO;
import auca.model.AcademicUnit;
import auca.model.Semester;
import auca.model.Student;
import auca.model.StudentRegistration;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/AddStudentRegistrationServlet")
public class InsertStudentRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String registrationCode = request.getParameter("registrationCode");
        String registrationDateString = request.getParameter("registrationDate");
        LocalDateTime registrationDate = LocalDateTime.parse(registrationDateString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        Long studentId = null;
        Integer semesterId = null;
        Long departmentId = null;

        try {
            studentId = Long.parseLong(request.getParameter("studentId"));
            semesterId = Integer.parseInt(request.getParameter("semesterId"));
            departmentId = Long.parseLong(request.getParameter("departmentId"));
        } catch (NumberFormatException e) {
            response.sendRedirect("studentregistrationform.html?error=invalidId");
            return;
        }

        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.getStudentById(studentId);
        if (student == null) {
            response.sendRedirect("studentregistrationform.html?error=studentNotFound");
            return;
        }

        SemesterDAO semesterDAO = new SemesterDAO();
        Semester semester = semesterDAO.getSemesterById(semesterId);
        if (semester == null) {
            response.sendRedirect("studentregistrationform.html?error=semesterNotFound");
            return;
        }

        AcademicUnitDAO academicUnitDAO = new AcademicUnitDAO();
        AcademicUnit department = academicUnitDAO.getAcademicUnitById(departmentId);
        if (department == null) {
            response.sendRedirect("studentregistrationform.html?error=departmentNotFound");
            return;
        }

        StudentRegistration studentRegistration = new StudentRegistration(registrationCode, registrationDate, student, semester, department);

        StudentRegistrationDAO studentRegistrationDAO = new StudentRegistrationDAO();
        studentRegistrationDAO.InsertStudentRegistration(studentRegistration);

        response.sendRedirect("studentregistrationform.html");
    }
}