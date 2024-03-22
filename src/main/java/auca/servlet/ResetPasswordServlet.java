package auca.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import auca.dao.UserDAO;
import auca.model.User;

@WebServlet(name = "ResetPasswordServlet", urlPatterns = {"/resetPassword"})
public class ResetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPassword = request.getParameter("new_password");
        String confirmPassword = request.getParameter("confirm_password");

        // Checking if new password and confirm password are not empty
        if (newPassword == null || newPassword.isEmpty() || confirmPassword == null || confirmPassword.isEmpty()) {
            request.setAttribute("error", "New password and confirm password are required.");
            request.getRequestDispatcher("resetpassword.html").forward(request, response);
            return;
        }

        // Checking if new password and confirm password match
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match.");
            request.getRequestDispatcher("resetpassword.html").forward(request, response);
            return;
        }

        // Getting the user's email from the session
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        if (email != null) {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserByEmail(email);

            if (user != null) {
                // Updating the user's password
                user.setPassword(newPassword);
                userDAO.updateUser(user);
                
                // Removing the email from the session after successful password reset
                session.removeAttribute("email");
                
                response.sendRedirect("login.html"); // Redirect to login page
            } else {
                // If user not found, we display an error message
                request.setAttribute("error", "User not found. Please try again.");
                request.getRequestDispatcher("resetpassword.html").forward(request, response);
            }
        } else {
            // If email not found in session, we display an error message
            request.setAttribute("error", "Email not found in session. Please try again.");
            request.getRequestDispatcher("resetpassword.html").forward(request, response);
        }
    }
}