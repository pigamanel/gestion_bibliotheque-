package Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import DAO.connexionBase;
import Modele.users;

public class userService {
    
    public boolean connecterUser(String email, String password) {
        ResultSet rs = null;
        connexionBase cb = new connexionBase();
        cb.conn();

        String req = "SELECT * FROM users WHERE email='" + email + "' AND password='" + password + "'";

        try {
            rs = cb.st.executeQuery(req);
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Erreur sur la requête de connexion");
            e.printStackTrace();
        }

        return false;
    }

    public boolean estAdministrateur(String email) {
        ResultSet rs = null;
        connexionBase cb = new connexionBase();
        cb.conn();

        String req = "SELECT role FROM users WHERE email='" + email + "'";

        try {
            rs = cb.st.executeQuery(req);
            if (rs.next()) {
                return "admin".equals(rs.getString("role"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur sur la requête de vérification du rôle");
            e.printStackTrace();
        }

        return false;
    }
    
    public void ajouterUser(users user) {
        connexionBase cb = new connexionBase();
        cb.conn();
        
        String req = "INSERT INTO users(id, nom, password, email, role) VALUES ('" + user.getId() + "', '" + user.getNom() + "', '" + user.getPassword() + "', '" + user.getEmail() + "', '" + user.getRole() + "')";
        
        try {
            cb.st.executeUpdate(req);
        } catch (SQLException e) {
            System.out.println("Erreur sur la requête d'insertion");
            e.printStackTrace();
        }
    }

    public void modifierUser(users user) {
        connexionBase cb = new connexionBase();
        cb.conn();
        
        String req = "UPDATE users SET nom='" + user.getNom() + "', password='" + user.getPassword() + "', email='" + user.getEmail() + "', role='" + user.getRole() + "' WHERE id='" + user.getId() + "'";
        
        try {
            cb.st.executeUpdate(req);
        } catch (SQLException e) {
            System.out.println("Erreur sur la requête de mise à jour");
            e.printStackTrace();
        }
    }

    public void supprimerUser(int id) {
        connexionBase cb = new connexionBase();
        cb.conn();
        
        String req = "DELETE FROM users WHERE id='" + id + "'";
        
        try {
            cb.st.executeUpdate(req);
        } catch (SQLException e) {
            System.out.println("Erreur sur la requête de suppression");
            e.printStackTrace();
        }
    }

    public ResultSet listerUsers() {
        ResultSet rs = null;
        
        connexionBase cb = new connexionBase();
        cb.conn();
        
        String req = "SELECT * FROM users ORDER BY nom";
        
        try {
            rs = cb.st.executeQuery(req);
        } catch (SQLException e) {
            System.out.println("Erreur sur la requête de sélection");
            e.printStackTrace();
        }
        
        return rs;
    }
}
