package com.dj.model.sys;

import java.sql.Clob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tbug entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TBUG", schema = "")
public class Tbug implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public Tbug(String cid, String cname, Date ccreatedatetime) {
		this.cid = cid;
		this.cname = cname;
		this.ccreatedatetime = ccreatedatetime;
	}

	// Fields

	private String cid;
	private String cname;
	private Clob cdesc;
	private Date ccreatedatetime;

	// Constructors

	/** default constructor */
	public Tbug() {
	}

	/** minimal constructor */
	public Tbug(String cid, String cname) {
		this.cid = cid;
		this.cname = cname;
	}

	/** full constructor */
	public Tbug(String cid, String cname, Clob cdesc, Date ccreatedatetime) {
		this.cid = cid;
		this.cname = cname;
		this.cdesc = cdesc;
		this.ccreatedatetime = ccreatedatetime;
	}

	// Property accessors
	@Id
	@Column(name = "CID",  length = 36)
	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	@Column(name = "CNAME", nullable = false, length = 100)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "CDESC")
	public Clob getCdesc() {
		return this.cdesc;
	}

	public void setCdesc(Clob cdesc) {
		this.cdesc = cdesc;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CCREATEDATETIME", length = 7)
	public Date getCcreatedatetime() {
		return this.ccreatedatetime;
	}

	public void setCcreatedatetime(Date ccreatedatetime) {
		this.ccreatedatetime = ccreatedatetime;
	}

}