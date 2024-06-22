package DAO;
import java.sql.*;
public class connexionBase {
	Connection COO=null;
	public Statement st=null;
	String user="root";
	String MDP="";
	String Url="jdbc:mysql://localhost/gestion_bibliotheque";
	String drive="com.mysql.cj.jdbc.Driver";
	public void conn() {
		try {
			Class.forName(drive);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			COO= DriverManager.getConnection(Url, user, MDP);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			st=COO.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void close() {
		// TODO Auto-generated method stub 
	     try {
	            if (st != null && !st.isClosed()) {
	                st.close();
	            }
	            if (COO != null && !COO.isClosed()) {
	                COO.close();
	            }
	        } catch (SQLException e) {
	            System.out.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
		
	
}

