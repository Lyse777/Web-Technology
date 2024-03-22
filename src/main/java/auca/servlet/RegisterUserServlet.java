package auca.servlet;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import auca.dao.UserDAO;
import auca.model.User;

@WebServlet(name = "RegisterUserServlet", urlPatterns = {"/registerUser"})
public class RegisterUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        if (email == null || email.isEmpty() || password == null || password.isEmpty() || role == null || role.isEmpty()) {
            request.setAttribute("error", "Email, password, and role are required!");
            request.getRequestDispatcher("signup.html").forward(request, response);
            return;
        }

        UserDAO userDAO = new UserDAO();

        if (userDAO.emailExists(email)) {
            request.setAttribute("error", "Email already exists. Please use a different email.");
            request.getRequestDispatcher("signup.html").forward(request, response);
            return;
        }

        User user = new User(email, password, role);

        userDAO.saveUser(user);

        response.sendRedirect("login.html");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("signup.html");
    }
}
