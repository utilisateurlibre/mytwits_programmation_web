package com.mytwits.service;

import java.io.Serializable;

public class BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String	id;
	private String	name;

	public String getId() {
		return this.id;
	}
	public void setId(String newId) {
		this.id = newId;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String newName) {
		this.name = newName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BaseModel) {
			BaseModel bm =(BaseModel) obj;
			return this.id!=null && bm.getId()!=null && this.id.equals(bm.getId());
		}
		return false;
	}

}
