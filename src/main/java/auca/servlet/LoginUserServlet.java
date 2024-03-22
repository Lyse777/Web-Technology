package auca.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import auca.dao.UserDAO;
import auca.model.User;

@WebServlet(name = "LoginUserServlet", urlPatterns = {"/login"})
public class LoginUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("error", "Email and password are required!");
            request.getRequestDispatcher("login.html").forward(request, response);
            return;
        }

        UserDAO userDAO = new UserDAO();
        User user = userDAO.authenticateUser(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole()); 

            // Setting session timeout to 1 minute (in seconds)
            session.setMaxInactiveInterval(60);

            // Redirecting based on user role
            if ("teacher".equals(user.getRole())) {
                response.sendRedirect("teacher/index/indexteacher.html");
            } else if ("admin".equals(user.getRole())) {
                response.sendRedirect("admin/index/indexadmin.html");
            } else if ("student".equals(user.getRole())) {
                response.sendRedirect("student/index/indexstudent.html");
            } else {
                // Redirecting to a default page if the role is unknown
                response.sendRedirect("login.html");
            }
        } else {
            request.setAttribute("error", "Invalid email or password!");
            request.getRequestDispatcher("login.html").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.html");
    }
}
