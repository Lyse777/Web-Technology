package auca.servlet;

import auca.dao.AcademicUnitDAO;
import auca.model.AcademicUnit;
import auca.model.EAcademicUnit;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "InsertAcademicUnitServlet", urlPatterns = {"/addAcademicUnit"})
public class InsertAcademicUnitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final AcademicUnitDAO academicUnitDAO;

    public InsertAcademicUnitServlet() {
        super();
        this.academicUnitDAO = new AcademicUnitDAO();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String academicCode = request.getParameter("academicCode");
        String academicName = request.getParameter("academicName");
        EAcademicUnit type = EAcademicUnit.valueOf(request.getParameter("type"));

        AcademicUnit academicUnit = new AcademicUnit(academicCode, academicName, type);
        academicUnit.setDepartmentId(2L); // Default department ID
        academicUnit.setParentId(1L); // Default parent ID

        academicUnitDAO.insertAcademicUnit(academicUnit);

        response.sendRedirect("academicunitform.html"); 
    }
}