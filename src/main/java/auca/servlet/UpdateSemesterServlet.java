package auca.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import auca.dao.SemesterDAO;
import auca.model.Semester;

@WebServlet(name = "UpdateSemesterServlet", urlPatterns = {"/UpdateSemesterServlet"})
public class UpdateSemesterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Integer semesterId = Integer.parseInt(request.getParameter("semesterId"));

        SemesterDAO semesterDAO = new SemesterDAO();
        Semester semester = semesterDAO.getSemesterById(semesterId);

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Update Semester</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; }");
        out.println("form { margin: 20px; max-width: 400px; border: 1px solid #ccc; padding: 15px; }");
        out.println("label { display: block; margin-bottom: 5px; }");
        out.println("input[type=text], input[type=datetime-local] { width: calc(100% - 22px); padding: 5px; margin-bottom: 10px; }");
        out.println("input[type=submit] { background-color: #4CAF50; color: white; padding: 10px; border: none; cursor: pointer; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Update Semester</h2>");
        out.println("<form action=\"UpdateSemesterServlet\" method=\"post\">");
        out.println("<input type=\"hidden\" name=\"semesterId\" value=\"" + semester.getSemesterId() + "\">");
        out.println("<label for=\"semesterName\">Semester Name:</label>");
        out.println("<input type=\"text\" id=\"semesterName\" name=\"semesterName\" value=\"" + semester.getSemesterName() + "\" required><br>");
        out.println("<label for=\"startingDate\">Starting Date:</label>");
        out.println("<input type=\"datetime-local\" id=\"startingDate\" name=\"startingDate\" value=\"" + semester.getStartingDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "\" required><br>");
        out.println("<label for=\"endDate\">End Date:</label>");
        out.println("<input type=\"datetime-local\" id=\"endDate\" name=\"endDate\" value=\"" + semester.getEndDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "\" required><br>");
        out.println("<input type=\"submit\" value=\"Update\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer semesterId = Integer.parseInt(request.getParameter("semesterId"));
        String semesterName = request.getParameter("semesterName");

        LocalDateTime startingDate = null;
        LocalDateTime endDate = null;

        try {
            startingDate = LocalDateTime.parse(request.getParameter("startingDate"));
            endDate = LocalDateTime.parse(request.getParameter("endDate"));
        } catch (Exception e) {
            // This Handles the case when the startingDate or endDate parameter is missing or has an invalid format
            e.printStackTrace();
            response.sendRedirect("DisplaySemestersServlet");
            return;
        }

        Semester semester = new Semester(semesterName, startingDate, endDate);
        semester.setSemesterId(semesterId);

        SemesterDAO semesterDAO = new SemesterDAO();
        semesterDAO.updateSemester(semester);

        response.sendRedirect("DisplaySemestersServlet");
    }
}
