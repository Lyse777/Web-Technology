package auca.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import auca.dao.SemesterDAO;

@WebServlet(name = "DeleteSemesterServlet", urlPatterns = {"/DeleteSemesterServlet"})
public class DeleteSemesterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final SemesterDAO semesterDAO;

    public DeleteSemesterServlet() {
        super();
        this.semesterDAO = new SemesterDAO();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String semesterIdStr = request.getParameter("semesterId");
            if (semesterIdStr != null && !semesterIdStr.isEmpty()) {
                int semesterId = Integer.parseInt(semesterIdStr);
                // Calling my DAO method to delete the semester using semesterId
                semesterDAO.deleteSemesterById(semesterId);
                response.sendRedirect("DisplaySemestersServlet");
            } else {
                // Handling the case where the parameter is missing or empty
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Invalid semester ID");
            }
        } catch (NumberFormatException e) {
            // Handling the case where the input string is not a valid number
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Invalid semester ID format");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forwarding doPost requests to doDelete method for handling deletion
        doDelete(request, response);
    }
}
