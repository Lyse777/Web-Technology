package auca.servlet;

import javax.servlet.*;
import javax.servlet.http.*;


import javax.servlet.annotation.*;
import java.io.IOException;
import auca.dao.UserDAO;
import auca.model.User;

@WebServlet(name = "ForgotPasswordServlet", urlPatterns = {"/forgotPassword"})
public class ForgotPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByEmail(email);

        if (user == null) {
            // Email does not exist in the database
            request.setAttribute("error", "Email not found. Please enter a registered email.");
            request.getRequestDispatcher("forgetpassword.html").forward(request, response);
        } else {
            // Email exists, storing the email in the session and redirect to the reset password page
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            response.sendRedirect("resetpassword.html");
        }
    }

}
