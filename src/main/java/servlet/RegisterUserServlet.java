package servlet;

import dao.UserDAO;
import model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterUserServlet")
public class RegisterUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("registerUser.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Validation
        if (username == null || username.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Username is required.");
            request.getRequestDispatcher("registerUser.jsp").forward(request, response);
            return;
        }

        if (password == null || password.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Password is required.");
            request.getRequestDispatcher("registerUser.jsp").forward(request, response);
            return;
        }

        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match.");
            request.getRequestDispatcher("registerUser.jsp").forward(request, response);
            return;
        }

        if (password.length() < 4) {
            request.setAttribute("errorMessage", "Password must be at least 4 characters.");
            request.getRequestDispatcher("registerUser.jsp").forward(request, response);
            return;
        }

        UserDAO userDAO = new UserDAO();

        if (userDAO.usernameExists(username)) {
            request.setAttribute("errorMessage", "Username already exists. Please choose another.");
            request.getRequestDispatcher("registerUser.jsp").forward(request, response);
            return;
        }

        User newUser = new User();
        newUser.setUserName(username);
        newUser.setPasswordHash(password); // In production, hash this

        boolean success = userDAO.saveUser(newUser);

        if (success) {
            request.setAttribute("successMessage", "User registered successfully! You can now login with username: " + username);
            request.getRequestDispatcher("registerUser.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Failed to register user. Please try again.");
            request.getRequestDispatcher("registerUser.jsp").forward(request, response);
        }
    }
}