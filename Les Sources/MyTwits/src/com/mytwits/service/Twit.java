package com.mytwits.service;

import java.util.Date;

public class Twit extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	private String idUser;
	private String text;
	private Date date;
	private String nomUser;
	
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getNomUser() {
		return nomUser;
	}
	public void setNomUser(String nomUser) {
		this.nomUser = nomUser;
	}
	
	

}
