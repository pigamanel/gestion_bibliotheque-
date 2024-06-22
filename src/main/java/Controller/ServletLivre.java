package Controller;

import Service.LivreService;
import Modele.livre;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class ServletLivre
 */
@WebServlet("/ServletLivre")
public class ServletLivre extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LivreService livreService = new LivreService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLivre() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listerLivres(response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String email = request.getParameter("email");

        if (action != null && email != null) {
            switch (action) {
                case "ajouter":
                    ajouterLivre(request, response, email);
                    break;
                case "modifier":
                    modifierLivre(request, response, email);
                    break;
                case "supprimer":
                    supprimerLivre(request, response, email);
                    break;
                default:
                    response.getWriter().println("Action non reconnue.");
            }
        } else {
            response.getWriter().println("Action ou email non fournis.");
        }
    }

    private void ajouterLivre(HttpServletRequest request, HttpServletResponse response, String email) throws IOException {
        String id = request.getParameter("id");
        String titre = request.getParameter("titre");
        String auteur = request.getParameter("auteur");
        String categorie = request.getParameter("categorie");
        String description = request.getParameter("description");
        String dateP = request.getParameter("dateP");

        livre li = new livre(id, titre, auteur, categorie, description, dateP);
        livreService.ajouterLivre(li, email);

        response.getWriter().println("Livre ajouté avec succès.");
    }

    private void modifierLivre(HttpServletRequest request, HttpServletResponse response, String email) throws IOException {
        String id = request.getParameter("id");
        String titre = request.getParameter("titre");
        String auteur = request.getParameter("auteur");
        String categorie = request.getParameter("categorie");
        String description = request.getParameter("description");
        String dateP = request.getParameter("dateP");

        livre li = new livre(id, titre, auteur, categorie, description, dateP);
        livreService.modifierLivre(li, email);

        response.getWriter().println("Livre modifié avec succès.");
    }

    private void supprimerLivre(HttpServletRequest request, HttpServletResponse response, String email) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        livreService.supprimerLivre(id, email);

        response.getWriter().println("Livre supprimé avec succès.");
    }

    private void listerLivres(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ResultSet rs = livreService.listerLivres();

        out.println("<html><body>");
        out.println("<h1>Liste des livres</h1>");
        out.println("<table border='1'>");
        out.println("<tr><th>ID</th><th>Titre</th><th>Auteur</th><th>Catégorie</th><th>Description</th><th>Date de publication</th></tr>");

        try {
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("id") + "</td>");
                out.println("<td>" + rs.getString("titre") + "</td>");
                out.println("<td>" + rs.getString("auteur") + "</td>");
                out.println("<td>" + rs.getString("categorie") + "</td>");
                out.println("<td>" + rs.getString("description") + "</td>");
                out.println("<td>" + rs.getString("dateP") + "</td>");
                out.println("</tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        out.println("</table>");
        out.println("</body></html>");
    }
}
