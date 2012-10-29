package com.mytwits.service;

public class Utilisateur extends BaseModel {
	
	private static final long serialVersionUID = 1L;

	private String login;
	private String password;
	private String nom;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
}
