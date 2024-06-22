package Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import DAO.connexionBase;
import Modele.livre_emprunte;
import Modele.users;
import Modele.livre;

public class LivreEmprunteService {
    
    // Ajouter un nouvel emprunt
    public void ajouterEmprunt(livre_emprunte emprunt) {
        connexionBase cb = new connexionBase();
        cb.conn();
        
        String req = "INSERT INTO livre_emprunte(date_emprunt, date_retour, id_users, id_livre) VALUES ('" 
                      + new java.sql.Date(emprunt.getDate_emprunt().getTime()) + "', '" 
                      + new java.sql.Date(emprunt.getDate_retour().getTime()) + "', '" 
                      + emprunt.getId_users().getId() + "', '" 
                      + emprunt.getId_livre().getId() + "')";
        
        try {
            cb.st.executeUpdate(req);
        } catch (SQLException e) {
            System.out.println("Erreur sur la requête d'insertion");
            e.printStackTrace();
        } finally {
            cb.close();
        }
    }

    // Modifier un emprunt existant
    public void modifierEmprunt(livre_emprunte emprunt) {
        connexionBase cb = new connexionBase();
        cb.conn();
        
        String req = "UPDATE livre_emprunte SET date_emprunt='" 
                      + new java.sql.Date(emprunt.getDate_emprunt().getTime()) + "', date_retour='" 
                      + new java.sql.Date(emprunt.getDate_retour().getTime()) + "', id_users='" 
                      + emprunt.getId_users().getId() + "', id_livre='" 
                      + emprunt.getId_livre().getId() + "' WHERE id='" + emprunt.getId() + "'";
        
        try {
            cb.st.executeUpdate(req);
        } catch (SQLException e) {
            System.out.println("Erreur sur la requête de mise à jour");
            e.printStackTrace();
        } finally {
            cb.close();
        }
    }

    // Supprimer un emprunt
    public void supprimerEmprunt(int id) {
        connexionBase cb = new connexionBase();
        cb.conn();
        
        String req = "DELETE FROM livre_emprunte WHERE id='" + id + "'";
        
        try {
            cb.st.executeUpdate(req);
        } catch (SQLException e) {
            System.out.println("Erreur sur la requête de suppression");
            e.printStackTrace();
        } finally {
            cb.close();
        }
    }

    // Lister tous les emprunts
    public ResultSet listerEmprunts() {
        ResultSet rs = null;
        connexionBase cb = new connexionBase();
        cb.conn();
        
        String req = "SELECT * FROM livre_emprunte ORDER BY date_emprunt";
        
        try {
            rs = cb.st.executeQuery(req);
        } catch (SQLException e) {
            System.out.println("Erreur sur la requête de sélection");
            e.printStackTrace();
        }
        
        return rs;
    }
}
