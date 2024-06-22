package Modele;

import java.util.Date;

public class livre_emprunte {
	private int id;
	private Date date_emprunt, date_retour;
	private users id_users;
	private livre id_livre;
	public livre_emprunte() {
		super();
	}
	public livre_emprunte(Date date_emprunt, Date date_retour, users id_users, livre id_livre) {
		super();
		this.date_emprunt = date_emprunt;
		this.date_retour = date_retour;
		this.id_users = id_users;
		this.id_livre = id_livre;
	}
	public livre_emprunte(int id, Date date_emprunt, Date date_retour, users id_users, livre id_livre) {
		super();
		this.id = id;
		this.date_emprunt = date_emprunt;
		this.date_retour = date_retour;
		this.id_users = id_users;
		this.id_livre = id_livre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate_emprunt() {
		return date_emprunt;
	}
	public void setDate_emprunt(Date date_emprunt) {
		this.date_emprunt = date_emprunt;
	}
	public Date getDate_retour() {
		return date_retour;
	}
	public void setDate_retour(Date date_retour) {
		this.date_retour = date_retour;
	}
	public users getId_users() {
		return id_users;
	}
	public void setId_users(users id_users) {
		this.id_users = id_users;
	}
	public livre getId_livre() {
		return id_livre;
	}
	public void setId_livre(livre id_livre) {
		this.id_livre = id_livre;
	}
	
	
}
