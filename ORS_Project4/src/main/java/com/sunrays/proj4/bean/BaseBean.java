package com.sunrays.proj4.bean;

import java.io.Serializable;

import java.sql.Timestamp;

/**
 * Parent class of all Beans in application. It contains generic attributes.
 * 
 * @author Interpreter
 * @version 1.0
 * 
 */

public abstract class BaseBean implements Serializable, DropDownListBean, Comparable<BaseBean> {
	/**
	 * server create id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Non Business primary key
	 */
	protected long id;
	/**
	 * Contains USER ID who created this database record
	 */
	protected String createdBy;
	/**
	 * Contains USER ID who modified this database record
	 */
	protected String modifiedBy;
	/**
	 * Contains Created Timestamp of database record
	 */

	protected Timestamp createdDatetime;
	/**
	 * Contains Modified Timestamp of database record
	 */

	protected Timestamp modifiedDatetime;

	

	/** 
	 * 
	 * 
	 * @return long
	 */
	public long getId() {
		return id;
	}

	/** 
	 * 
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public Timestamp getModifiedDatetime() {
		return modifiedDatetime;
	}

	public void setModifiedDatetime(Timestamp modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

	public int compareTo(BaseBean next) {
		return getValue().compareTo(next.getValue());

	}

	public String getvalue() {

		return null;

	}

	public String getkey() {

		return null;

	}

}
