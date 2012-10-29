package com.mytwits.action;

import java.util.List;

import com.mytwits.service.Utilisateur;
import com.mytwits.service.UtilisateurDao;

public class Login {
	public void login() throws Exception {
		UtilisateurDao utilisateurDao = new UtilisateurDao();
		List<Utilisateur> listUtilisateur = utilisateurDao.listAll();
		for (Utilisateur utilisateur : listUtilisateur) {
			System.out.println("nom : " + utilisateur.getNom());
		}
	}
}
