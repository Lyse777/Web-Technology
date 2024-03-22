package auca.servlet;

import auca.dao.AcademicUnitDAO;
import auca.dao.CourseDAO;
import auca.dao.StudentDAO;
import auca.model.AcademicUnit;
import auca.model.Course;
import auca.model.Student;
import auca.util.HibernateUtil;

import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebServlet(name = "InsertStudentServlet", urlPatterns = {"/addStudent"})
public class InsertStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String academicUnitIdStr = request.getParameter("academicUnitId");
        String[] courseIds = request.getParameterValues("courseId");

        // Validating input
        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty() || dateOfBirth == null || dateOfBirth.isEmpty() || academicUnitIdStr == null || academicUnitIdStr.isEmpty()) {
            request.setAttribute("error", "All fields are required!");
            request.getRequestDispatcher("studentform.jsp").forward(request, response);
            return;
        }

        Long academicUnitId;
        try {
            academicUnitId = Long.parseLong(academicUnitIdStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid academic unit ID!");
            request.getRequestDispatcher("studentform.jsp").forward(request, response);
            return;
        }

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            AcademicUnitDAO academicUnitDAO = new AcademicUnitDAO();
            AcademicUnit academicUnit = academicUnitDAO.getAcademicUnitById(academicUnitId, session);

            if (academicUnit == null) {
                request.setAttribute("error", "Invalid academic unit selected!");
                request.getRequestDispatcher("studentform.jsp").forward(request, response);
                return;
            }

            Student student = new Student();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setDateOfBirth(dateOfBirth);
            student.setAcademicUnit(academicUnit);

            Set<Course> selectedCourses = new HashSet<>();
            if (courseIds != null && courseIds.length > 0) {
                CourseDAO courseDAO = new CourseDAO();

                for (String courseIdStr : courseIds) {
                    try {
                        int courseId = Integer.parseInt(courseIdStr);
                        Course course = courseDAO.getCourseById(courseId, session);
                        if (course != null) {
                            selectedCourses.add(course);
                        }
                    } catch (NumberFormatException e) {
                        // Skipping invalid course IDs
                    }
                }
            }

            student.setCourses(selectedCourses);

            StudentDAO studentDAO = new StudentDAO();
            studentDAO.insertStudent(student, session);
            request.setAttribute("successMessage", "Student added successfully!");
        } catch (Exception e) {
            request.setAttribute("error", "Failed to insert student!");
            e.printStackTrace();
        } finally {
            session.close();
        }

        request.getRequestDispatcher("login.html").forward(request, response);
    }
}
