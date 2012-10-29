package com.mytwits.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mytwits.service.Utilisateur;
import com.mytwits.service.UtilisateurDao;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		UtilisateurDao utilisateurDao = new UtilisateurDao();
		Utilisateur currentUtilisateur = null;
		boolean found = false;
		try {
			List<Utilisateur> listUtilisateur = utilisateurDao.listAll();
			
			for (Utilisateur utilisateur : listUtilisateur) {
				if(utilisateur.getLogin().equals(login) && utilisateur.getPassword().equals(password)) {
					found = true;
					currentUtilisateur = utilisateur;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(found == true) {
			req.getSession().setAttribute("utilisateur", currentUtilisateur);
			req.getRequestDispatcher("/index.jsp").forward(req, res);
		} else {
			req.getRequestDispatcher("/login.jsp").forward(req, res);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}