package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Service.LivreEmprunteService;
import Modele.livre_emprunte;
import Modele.users;
import Modele.livre;

/**
 * Servlet implementation class ServletLiveEmp
 */
@WebServlet("/ServletLiveEmp")
public class ServletLiveEmp extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LivreEmprunteService livreEmprunteService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLiveEmp() {
        super();
        livreEmprunteService = new LivreEmprunteService();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ResultSet rs = livreEmprunteService.listerEmprunts();
        try {
            while (rs.next()) {
                out.println("ID: " + rs.getInt("id") + ", Date Emprunt: " + rs.getDate("date_emprunt") + 
                            ", Date Retour: " + rs.getDate("date_retour") + ", ID Utilisateur: " + rs.getInt("id_users") + 
                            ", ID Livre: " + rs.getInt("id_livre") + "<br>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing");
            return;
        }

        switch (action) {
            case "ajouter":
                ajouterEmprunt(request, response);
                break;
            case "modifier":
                modifierEmprunt(request, response);
                break;
            case "supprimer":
                supprimerEmprunt(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action parameter");
        }
    }

    private void ajouterEmprunt(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Date dateEmprunt = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date_emprunt"));
            Date dateRetour = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date_retour"));
            int idUser = Integer.parseInt(request.getParameter("id_users"));
            int idLivre = Integer.parseInt(request.getParameter("id_livre"));

            users user = new users(idUser);  
            livre book = new livre(idLivre);  
            livre_emprunte emprunt = new livre_emprunte(dateEmprunt, dateRetour, user, book);

            livreEmprunteService.ajouterEmprunt(emprunt);
            response.getWriter().println("Emprunt ajouté avec succès");

        } catch (ParseException | NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input parameters");
            e.printStackTrace();
        }
    }

    private void modifierEmprunt(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Date dateEmprunt = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date_emprunt"));
            Date dateRetour = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date_retour"));
            int idUser = Integer.parseInt(request.getParameter("id_users"));
            int idLivre = Integer.parseInt(request.getParameter("id_livre"));

            users user = new users(idUser);  
            livre book = new livre(idLivre);  
            livre_emprunte emprunt = new livre_emprunte(id, dateEmprunt, dateRetour, user, book);

            livreEmprunteService.modifierEmprunt(emprunt);
            response.getWriter().println("Emprunt modifié avec succès");

        } catch (ParseException | NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input parameters");
            e.printStackTrace();
        }
    }

    private void supprimerEmprunt(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            livreEmprunteService.supprimerEmprunt(id);
            response.getWriter().println("Emprunt supprimé avec succès");

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input parameters");
            e.printStackTrace();
        }
    }
}
