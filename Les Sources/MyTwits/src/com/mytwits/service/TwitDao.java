package com.mytwits.service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TwitDao extends BaseDao {

	public TwitDao() {
		super("twit");
		this.createSql = "insert into twit (id_user,text,date,nom_user) values(?,?,?,?)";
		this.updateSql = "update twit set id_user=?,text=?,date=?,nom_user=? where id=?";
		this.getByIdSql = "select a.id,a.id_user,a.text,a.date,a.nom_user from twit a where a.id=?";
		this.listAllSql = "select a.id,a.id_user,a.text,a.date,a.nom_user from twit a order by id desc";
	}

	protected BaseModel createInstance() {
		return new Twit();
	}

	protected BaseModel createInstance(ResultSet rs) throws Exception {
		Twit x = new Twit();
		x.setId(rs.getString(1));
		x.setIdUser(rs.getString(2));
		x.setText(rs.getString(3));
		x.setDate(rs.getDate(4));
		x.setNomUser(rs.getString(5));
		return x;
	}

	protected int fill(PreparedStatement pstmt, BaseModel bm) throws Exception {
		Twit x = (Twit) bm;
		pstmt.setString(1, x.getIdUser());
		pstmt.setString(2, x.getText());
		pstmt.setDate(3, new Date(x.getDate().getTime()));
		pstmt.setString(4, x.getNomUser());
		return 5;
	}

	public void createById(String id) throws Exception {
		String sql  = "insert into twit (id) values(?)";
		executeUpdate(new String[]{id}, sql);
		
	}

	public ArrayList<Twit> listAll() throws Exception {
        ArrayList<BaseModel> baseList = super.list();
        ArrayList<Twit> list = new ArrayList<Twit>();
        for (BaseModel baseModel : baseList) {
            list.add((Twit) baseModel);
        }
        return list;
    }
}