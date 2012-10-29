package com.mytwits.service;

import java.sql.SQLException;

public class DaoException extends Exception{

	private static final long	serialVersionUID	= 2072930695384272275L;

	public DaoException(SQLException e,String id) {
		super();
		System.out.println(e.getMessage());
		String s =  "error." + id + ".";
		int code = e.getErrorCode();
		String msg = e.getMessage();
		switch (code) {
			case 2001:
				s = s+ code;
				break;
			case 1048:
				s = s + msg.replace(' ', '_');
				break;
			case 1062:
				int i = msg.lastIndexOf("key");
				s =s + ".unique." + msg.substring(i + 4);
				break;
			case 1451:
				s = s + ".delete.depend";
				break;
			default:
				s =s + "." + code;
				break;
		}
	}
}
