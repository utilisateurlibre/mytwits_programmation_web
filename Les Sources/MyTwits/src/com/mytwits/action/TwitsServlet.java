package com.mytwits.action;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.mytwits.service.Twit;
import com.mytwits.service.TwitDao;
import com.mytwits.service.Utilisateur;

import java.util.*;

public class TwitsServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Utilisateur currentUtilisateur = (Utilisateur) req.getSession().getAttribute("utilisateur");
		if (currentUtilisateur == null) {
			req.getRequestDispatcher("/login.jsp").forward(req, res);
		}
		String text = req.getParameter("twit");
		TwitDao twitDao = new TwitDao();
		Twit twit = new Twit();
		twit.setText(text);
		twit.setDate(new Date());
		twit.setNomUser(currentUtilisateur.getNom());
		twit.setIdUser(currentUtilisateur.getId());
		try {
			twitDao.create(twit);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.getRequestDispatcher("/index.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}