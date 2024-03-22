package auca.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import auca.dao.StudentDAO;
import auca.model.Student;
import auca.model.Course;

@WebServlet(name = "DisplayStudentsServlet", urlPatterns = {"/DisplayStudentsServlet"})
public class DisplayStudentsServlet extends HttpServlet {
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
        out.println("<title>Display Students</title>");
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
        out.println("<h2>Display Students</h2>");

        StudentDAO studentDAO = new StudentDAO();
        List<Student> students = studentDAO.getAllStudents();

        if (students.isEmpty()) {
            out.println("<p>No students found.</p>");
        } else {
            out.println("<table>");
            out.println("<tr><th>Student ID</th><th>First Name</th><th>Last Name</th><th>Date of Birth</th><th>Courses</th><th>Actions</th></tr>");
            for (Student student : students) {
                out.println("<tr>");
                out.println("<td>" + student.getStudentId() + "</td>");
                out.println("<td>" + student.getFirstName() + "</td>");
                out.println("<td>" + student.getLastName() + "</td>");
                out.println("<td>" + student.getDateOfBirth() + "</td>");
                out.println("<td>");
                for (Course course : student.getCourses()) {
                    out.println(course.getCourseId() + " - " + course.getCourseName() + "<br>");
                }
                out.println("</td>");
                out.println("<td class=\"action-btns\">");
                out.println("<form action=\"DeleteStudentServlet\" method=\"post\">");
                out.println("<input type=\"hidden\" name=\"studentId\" value=\"" + student.getStudentId() + "\">");
                out.println("<button class=\"delete-btn\" type=\"submit\">Delete</button>");
                out.println("</form>");
                out.println("<form action=\"UpdateStudentServlet\" method=\"get\">");
                out.println("<input type=\"hidden\" name=\"studentId\" value=\"" + student.getStudentId() + "\">");
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
