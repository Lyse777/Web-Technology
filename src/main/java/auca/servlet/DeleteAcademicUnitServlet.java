package auca.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import auca.dao.AcademicUnitDAO;

@WebServlet(name = "DeleteAcademicUnitServlet", urlPatterns = {"/DeleteAcademicUnitServlet"})
public class DeleteAcademicUnitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final AcademicUnitDAO academicUnitDAO;

    public DeleteAcademicUnitServlet() {
        super();
        this.academicUnitDAO = new AcademicUnitDAO();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String academicIdStr = request.getParameter("academicId");
            if (academicIdStr != null && !academicIdStr.isEmpty()) {
                long academicId = Long.parseLong(academicIdStr);
                // Calling my DAO method to delete the academic unit using academicId
                academicUnitDAO.deleteAcademicUnit(academicId);
                response.sendRedirect("DisplayAcademicUnitsServlet"); 
            } else {
                // Handling the case where the parameter is missing or empty
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Invalid academic unit ID");
            }
        } catch (NumberFormatException e) {
            // Handling the case where the input string is not a valid number
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Invalid academic unit ID format");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forwarding doPost requests to doDelete method for handling deletion
        doDelete(request, response);
    }
}
