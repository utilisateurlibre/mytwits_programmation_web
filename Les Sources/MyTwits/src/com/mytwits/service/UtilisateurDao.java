package com.mytwits.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UtilisateurDao extends BaseDao {

	public UtilisateurDao() {
		super("utilisateur");
		this.createSql = "insert into utilisateur (login,password,nom) values(?,?,?)";
		this.updateSql = "update utilisateur set login=?,password=?,nom=? where id=?";
		this.getByIdSql = "select a.id,a.login,a.password,a.nom from utilisateur a where a.id=?";
		this.listAllSql = "select a.id,a.login,a.password,a.nom from utilisateur a order by id desc";
	}

	protected BaseModel createInstance() {
		return new Utilisateur();
	}

	protected BaseModel createInstance(ResultSet rs) throws Exception {
		Utilisateur x = new Utilisateur();
		x.setId(rs.getString(1));
		x.setLogin(rs.getString(2));
		x.setPassword(rs.getString(3));
		x.setNom(rs.getString(4));
		return x;
	}

	protected int fill(PreparedStatement pstmt, BaseModel bm) throws Exception {
		Utilisateur x = (Utilisateur) bm;
		pstmt.setString(1, x.getLogin());
		pstmt.setString(2, x.getPassword());
		pstmt.setString(3, x.getNom());
		return 4;
	}

	public void createById(String id) throws Exception {
		String sql  = "insert into utilisateur (id) values(?)";
		executeUpdate(new String[]{id}, sql);
		
	}

	public ArrayList<Utilisateur> listAll() throws Exception {
        ArrayList<BaseModel> baseList = super.list();
        ArrayList<Utilisateur> list = new ArrayList<Utilisateur>();
        for (BaseModel baseModel : baseList) {
            list.add((Utilisateur) baseModel);
        }
        return list;
    }
}