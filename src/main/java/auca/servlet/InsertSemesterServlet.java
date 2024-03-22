package auca.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auca.dao.SemesterDAO;
import auca.model.Semester;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/AddSemesterServlet")
public class InsertSemesterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String semesterName = request.getParameter("semesterName");
        String startingDateStr = request.getParameter("startingDate");
        String endDateStr = request.getParameter("endDate");

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime startingDate = LocalDateTime.parse(startingDateStr, formatter);
            LocalDateTime endDate = LocalDateTime.parse(endDateStr, formatter);

            Semester semester = new Semester(semesterName, startingDate, endDate);

            SemesterDAO semesterDAO = new SemesterDAO();
            semesterDAO.InsertSemester(semester);

            response.sendRedirect("semesterform.html");
        } catch (Exception e) {
            e.printStackTrace(); 
            request.setAttribute("error", "An error occurred while adding the semester.");
            request.getRequestDispatcher("semesterform.html").forward(request, response);
        }
    }
}
