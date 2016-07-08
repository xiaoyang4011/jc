package com.dj.model.sys;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tuser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TUSER", schema = "")
public class Tuser implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	
	private String cid;
	private String csn;
	private Date ccreatedatetime;
	private Date cmodifydatetime;
	private String cname;
	private String cpwd;
	private String cemail;
	private String cstatus;
	private String crealname;
	private Tdept tdept;
	private Set<Tusertrole> tusertroles = new HashSet<Tusertrole>(0);

	// Constructors

	/** default constructor */
	public Tuser() {
	}

	/** minimal constructor */
	public Tuser(String cid, String cname, String cpwd) {
		this.cid = cid;
		this.cname = cname;
		this.cpwd = cpwd;
	}

	/** full constructor */
	public Tuser(String cid, Date ccreatedatetime, Date cmodifydatetime, String cname, String cpwd, Set<Tusertrole> tusertroles) {
		this.cid = cid;
		this.ccreatedatetime = ccreatedatetime;
		this.cmodifydatetime = cmodifydatetime;
		this.cname = cname;
		this.cpwd = cpwd;
		this.tusertroles = tusertroles;
	}

	// Property accessors
	@Id
	@Column(name = "CID",   length = 36)
	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CCREATEDATETIME", length = 7)
	public Date getCcreatedatetime() {
		return this.ccreatedatetime;
	}

	public void setCcreatedatetime(Date ccreatedatetime) {
		this.ccreatedatetime = ccreatedatetime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CMODIFYDATETIME", length = 7)
	public Date getCmodifydatetime() {
		return this.cmodifydatetime;
	}

	public void setCmodifydatetime(Date cmodifydatetime) {
		this.cmodifydatetime = cmodifydatetime;
	}

	@Column(name = "CNAME", unique = true, nullable = false, length = 100)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "CPWD", nullable = false, length = 100)
	public String getCpwd() {
		return this.cpwd;
	}

	public void setCpwd(String cpwd) {
		this.cpwd = cpwd;
	}

	@Column(name = "CEMAIL", unique = true, length = 50)
	public String getCemail() {
		return cemail;
	}

	public void setCemail(String cemail) {
		this.cemail = cemail;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tuser")
	public Set<Tusertrole> getTusertroles() {
		return this.tusertroles;
	}

	public void setTusertroles(Set<Tusertrole> tusertroles) {
		this.tusertroles = tusertroles;
	}

	@Column(name = "CSTATUS", length = 10)
	public String getCstatus() {
		return cstatus;
	}

	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}

	@Column(name = "CREALNAME", nullable = false, length = 200)
	public String getCrealname() {
		return crealname;
	}

	public void setCrealname(String crealname) {
		this.crealname = crealname;
	}

	@Column(name = "CSN", length = 36)
	public String getCsn() {
		return csn;
	}

	public void setCsn(String csn) {
		this.csn = csn;
	}

	@ManyToOne(fetch = FetchType.LAZY)
/*	     如果是EAGER，那么表示取出这条数据时，它关联的数据也同时取出放入内存中 
                如果是LAZY，那么取出这条数据时，它关联的数据并不取出来，在同一个session中，什么时候要用，就什么时候取(再次访问数据库)。 
	     但是，在session外，就不能再取了。用EAGER时，因为在内存里，所以在session外也可以取。 
	     一般只在一边设Eager，JPA接口默认为一对多为Lazy，多对一为Eager，但是Hibernate反向工程生成Entity时，多对一为Lazy，需要手动改为Eager。 
	     而两边都设Eager，那么代码中取一条记录时，会发2次SQL。*/
	//链接字段命名
	@JoinColumn(name = "CDEPTID")
	public Tdept getTdept() {
		return tdept;
	}

	public void setTdept(Tdept tdept) {
		this.tdept = tdept;
	}

}