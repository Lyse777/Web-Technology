package auca.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

import auca.dao.AcademicUnitDAO;
import auca.model.AcademicUnit;
import auca.model.EAcademicUnit;

@WebServlet(name = "UpdateAcademicUnitServlet", urlPatterns = {"/UpdateAcademicUnitServlet"})
public class UpdateAcademicUnitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Long academicId = Long.parseLong(request.getParameter("academicId"));

        AcademicUnitDAO academicUnitDAO = new AcademicUnitDAO();
        AcademicUnit academicUnit = academicUnitDAO.getAcademicUnitById(academicId);

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Update Academic Unit</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; }");
        out.println("form { margin: 20px; max-width: 400px; border: 1px solid #ccc; padding: 15px; }");
        out.println("label { display: block; margin-bottom: 5px; }");
        out.println("input[type=text], select { width: calc(100% - 22px); padding: 5px; margin-bottom: 10px; }");
        out.println("input[type=submit] { background-color: #4CAF50; color: white; padding: 10px; border: none; cursor: pointer; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Update Academic Unit</h2>");
        out.println("<form action=\"UpdateAcademicUnitServlet\" method=\"post\">");
        out.println("<input type=\"hidden\" name=\"academicId\" value=\"" + academicUnit.getAcademicId() + "\">");
        out.println("<label for=\"academicCode\">Academic Code:</label>");
        out.println("<input type=\"text\" id=\"academicCode\" name=\"academicCode\" value=\"" + academicUnit.getAcademicCode() + "\" required><br>");
        out.println("<label for=\"academicName\">Academic Name:</label>");
        out.println("<input type=\"text\" id=\"academicName\" name=\"academicName\" value=\"" + academicUnit.getAcademicName() + "\" required><br>");
        out.println("<label for=\"type\">Type:</label>");
        out.println("<select id=\"type\" name=\"type\" required>");
        out.println("<option value=\"PROGRAM\"" + (academicUnit.getType() == EAcademicUnit.PROGRAM ? " selected" : "") + ">Program</option>");
        out.println("<option value=\"FACULTY\"" + (academicUnit.getType() == EAcademicUnit.FACULTY ? " selected" : "") + ">Faculty</option>");
        out.println("<option value=\"DEPARTMENT\"" + (academicUnit.getType() == EAcademicUnit.DEPARTMENT ? " selected" : "") + ">Department</option>");
        out.println("</select><br>");
        out.println("<input type=\"submit\" value=\"Update\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long academicId = Long.parseLong(request.getParameter("academicId"));
        String academicCode = request.getParameter("academicCode");
        String academicName = request.getParameter("academicName");
        EAcademicUnit type = EAcademicUnit.valueOf(request.getParameter("type"));

        AcademicUnit academicUnit = new AcademicUnit(academicCode, academicName, type);
        academicUnit.setAcademicId(academicId);

        AcademicUnitDAO academicUnitDAO = new AcademicUnitDAO();
        academicUnitDAO.updateAcademicUnit(academicUnit);

        response.sendRedirect("DisplayAcademicUnitsServlet");
    }
}
