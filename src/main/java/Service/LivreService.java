package Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import DAO.connexionBase;
import Modele.livre;

public class LivreService {
    private userService userService = new userService();

    public void ajouterLivre(livre li, String email) {
        if (userService.estAdministrateur(email)) {
            connexionBase cb = new connexionBase();
            cb.conn();

            String req = "INSERT INTO livre(id, titre, auteur, categorie, description, dateP) VALUES ('" + li.getId() + "', '" + li.getTitre() + "', '" + li.getAuteur() + "', '" + li.getCategorie() + "', '" + li.getDescription() + "', '" + li.getDateP() + "')";

            try {
                cb.st.executeUpdate(req);
            } catch (SQLException e) {
                System.out.println("Erreur sur la requête d'insertion");
                e.printStackTrace();
            }
        } else {
            System.out.println("Permission refusée : Seuls les administrateurs peuvent ajouter des livres.");
        }
    }

    public void modifierLivre(livre li, String email) {
        if (userService.estAdministrateur(email)) {
            connexionBase cb = new connexionBase();
            cb.conn();

            String req = "UPDATE livre SET titre='" + li.getTitre() + "', auteur='" + li.getAuteur() + "', categorie='" + li.getCategorie() + "', description='" + li.getDescription() + "', dateP='" + li.getDateP() + "' WHERE id='" + li.getId() + "'";

            try {
                cb.st.executeUpdate(req);
            } catch (SQLException e) {
                System.out.println("Erreur sur la requête de mise à jour");
                e.printStackTrace();
            }
        } else {
            System.out.println("Permission refusée : Seuls les administrateurs peuvent modifier des livres.");
        }
    }

    public void supprimerLivre(int id, String email) {
        if (userService.estAdministrateur(email)) {
            connexionBase cb = new connexionBase();
            cb.conn();

            String req = "DELETE FROM livre WHERE id='" + id + "'";

            try {
                cb.st.executeUpdate(req);
            } catch (SQLException e) {
                System.out.println("Erreur sur la requête de suppression");
                e.printStackTrace();
            }
        } else {
            System.out.println("Permission refusée : Seuls les administrateurs peuvent supprimer des livres.");
        }
    }

    public ResultSet listerLivres() {
        ResultSet rs = null;
        connexionBase cb = new connexionBase();
        cb.conn();

        String req = "SELECT * FROM livre ORDER BY dateP";

        try {
            rs = cb.st.executeQuery(req);
        } catch (SQLException e) {
            System.out.println("Erreur sur la requête de sélection");
            e.printStackTrace();
        }

        return rs;
    }
}
