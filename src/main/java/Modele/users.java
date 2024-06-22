package Modele;

public class users {
    private int id;
    private String nom;
    private String password;
    private String email;
    private String role;

    // Constructeur sans paramètres
    public users() {
        // Appel au constructeur de la classe parente (Object)
        super();
    }

    // Constructeur avec tous les paramètres sauf l'id
    public users(String nom, String password, String email, String role) {
        this(); // Appel au constructeur sans paramètres
        this.nom = nom;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    // Constructeur avec tous les paramètres
    public users(int id, String nom, String password, String email, String role) {
        this(nom, password, email, role); // Appel au constructeur avec les paramètres
        this.id = id;
    }

    public users(int idUser) {
		// TODO Auto-generated constructor stub
    	this.id = id;
	}

	// Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
