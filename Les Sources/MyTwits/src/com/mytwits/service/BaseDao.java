package com.mytwits.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class BaseDao  {

	protected String	entityName;
	protected String	createSql;
	protected String	updateSql;
	protected String	deleteSql;
	protected String	getByIdSql;
	protected String	listAllSql;

	protected abstract BaseModel createInstance();
	
	public BaseDao(String entityName_) {
		this.entityName = entityName_;
		this.deleteSql = "delete from "+this.entityName+" where id=? ";
		this.createSql = "insert into "+this.entityName+" (name) values(?)";
		this.updateSql = "update "+this.entityName+" set name=?  where id=?";
		this.getByIdSql = "select a.id,a.name from "+this.entityName+" a where a.id=?";
		this.listAllSql = "select a.id,a.name from "+this.entityName+" a order by a.id ";
	}
	
	protected BaseModel createInstance(ResultSet rs) throws Exception {
		BaseModel x = createInstance();
		x.setId(rs.getString(1));
		x.setName(rs.getString(2));
		return x;
	}
	protected int fill(PreparedStatement pstmt, BaseModel bm) throws Exception {
		pstmt.setString(1, bm.getName());
		return 2;
	}
	
	public BaseModel create(BaseModel x) throws Exception {
		Connection con = PersistenceService.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet keys = null;
		try {
			System.out.println("SQL : " + this.createSql);
			pstmt = con.prepareStatement(this.createSql, Statement.RETURN_GENERATED_KEYS);
			fill(pstmt,x);
			pstmt.executeUpdate();
			keys = pstmt.getGeneratedKeys();
			keys.next();
			x.setId(keys.getString(1));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException(e, this.entityName);
		} finally {
			if (keys != null) {
				keys.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		updateMn(x);
		return getById(x.getId());
	}

	
	
	
	public void update(BaseModel x) throws Exception {
		Connection con = PersistenceService.getInstance().getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(this.updateSql);
			int i = fill(pstmt,x);
			pstmt.setString(i, x.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e, this.entityName);
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		updateMn(x);
	}

	public void executeUpdate(String[] values,String sql) throws Exception {
		Connection con = PersistenceService.getInstance().getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					pstmt.setString(i + 1, values[i]);
				}
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e, this.entityName);
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
	
	public void executeUpdate(String[] values,String sql, Connection con) throws Exception {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					pstmt.setString(i + 1, values[i]);
				}
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e, this.entityName);
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}
	}

	public void remove(String id) throws Exception {
		Connection con = PersistenceService.getInstance().getConnection();
		PreparedStatement pstmt = null;
		try {
			removeMn(id);
			pstmt = con.prepareStatement(this.deleteSql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e, this.entityName);
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	public void remove(String id, Connection con) throws Exception {
		PreparedStatement pstmt = null;
		try {
			removeMn(id);
			pstmt = con.prepareStatement(this.deleteSql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e, this.entityName);
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}
	}
	

	public BaseModel getById(String id) throws Exception {
		return get(new String[] { id }, this.getByIdSql);
	}
	public BaseModel get(String[] values, String sql) throws Exception {
		Connection con = PersistenceService.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BaseModel x = null;
		try {
			pstmt = con.prepareStatement(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					pstmt.setString(i + 1, values[i]);
				}
			}

			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = createInstance(rs);
			}
		} catch (SQLException e) {
			throw new DaoException(e, this.entityName);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		getMn(x);
		return x;
	}	

	
	public ArrayList<BaseModel> list() throws Exception {
		return list(null,this.listAllSql);
	}
	public ArrayList<BaseModel> list(String[] values, String sql) throws Exception {
		Connection con = PersistenceService.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BaseModel> l = new ArrayList<BaseModel>();
		try {
			System.out.println("SQL : " + sql);
			pstmt = con.prepareStatement(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					pstmt.setString(i + 1, values[i]);
				}
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				BaseModel x = createInstance(rs);
				getMn(x);
				l.add(x);
			}
		} catch (SQLException e) {
			throw new DaoException(e, this.entityName);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}

		return l;
	}

	public String getValue(String[] values, String sql) throws Exception {
		Connection con = PersistenceService.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String x = null;
		try {
			pstmt = con.prepareStatement(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					pstmt.setString(i + 1, values[i]);
				}
			}

			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = rs.getString(1);
			}
		} catch (SQLException e) {
			throw new DaoException(e, this.entityName);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return x;
	}
	public String[] getValues(String[] values, String sql) throws Exception {
		Connection con = PersistenceService.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String[] x = null;
		try {
			pstmt = con.prepareStatement(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					pstmt.setString(i + 1, values[i]);
				}
			}

			rs = pstmt.executeQuery();
			ArrayList<String> list = new ArrayList<String>();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			x = new String[list.size()];
			int i=0;
			for (String c : list) {
				x[i++]=c;
			}
		} catch (SQLException e) {
			throw new DaoException(e, this.entityName);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return x;
	}
	

public void getMn(BaseModel bm) throws Exception {
		
	}
	public void removeMn(String id) throws Exception {
		
	}
	public void updateMn(BaseModel bm) throws Exception {
		
	}
	
	public String cleanDouble(String s){
		if(s==null){
			return null;
		}
		if(s.startsWith(".")){
			s="0"+s;
		}
		return s.replace('.', ',');
	}
}
