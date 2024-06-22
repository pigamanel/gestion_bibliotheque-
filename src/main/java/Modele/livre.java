package Modele;

public class livre {
    private String id;
    private String titre;
    private String auteur;
    private String categorie;
    private String description;
    private String dateP;

    // Constructeur
    public livre(String id, String titre, String auteur, String categorie, String description, String dateP) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.categorie = categorie;
        this.description = description;
        this.dateP = dateP;
    }

    // Constructeur acceptant uniquement un ID
    public livre(String id) {
        this.id = id;
    }

    public livre(int idLivre) {
		// TODO Auto-generated constructor stub
    	this.id = id;
	}

	// Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateP() {
        return dateP;
    }

    public void setDateP(String dateP) {
        this.dateP = dateP;
    }
}
