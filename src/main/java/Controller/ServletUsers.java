package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Service.userService;
import Modele.users;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/ServletUsers")
public class ServletUsers extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private userService userService;

    public ServletUsers() {
        super();
        userService = new userService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "list":
                    listUsers(out);
                    break;
                case "delete":
                    deleteUser(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                default:
                    out.println("Invalid action");
                    break;
            }
        } finally {
            out.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "login":
                    loginUser(request, response);
                    break;
                case "insert":
                    insertUser(request, response);
                    break;
                case "update":
                    updateUser(request, response);
                    break;
                default:
                    response.getWriter().println("Invalid action");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listUsers(PrintWriter out) {
        try {
            ResultSet rs = userService.listerUsers();
            out.println("<html><body>");
            out.println("<h2>User List</h2>");
            while (rs.next()) {
                out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("nom") + ", Email: " + rs.getString("email") + ", Role: " + rs.getString("role") + "<br>");
            }
            out.println("</body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (userService.connecterUser(email, password)) {
            response.getWriter().println("User logged in successfully");
        } else {
            response.getWriter().println("Invalid email or password");
        }
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role = request.getParameter("role");

        users newUser = new users(0, name, password, email, role);
        userService.ajouterUser(newUser);
        response.getWriter().println("User inserted successfully");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role = request.getParameter("role");

        users updatedUser = new users(id, name, password, email, role);
        userService.modifierUser(updatedUser);
        response.getWriter().println("User updated successfully");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userService.supprimerUser(id);
        response.getWriter().println("User deleted successfully");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        // Retrieve user data and display the edit form
        // Implementation can be added as needed
    }
}
