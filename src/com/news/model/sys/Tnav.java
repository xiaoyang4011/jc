package com.news.model.sys;

import java.math.BigDecimal;
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

/**
 * Tnav entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Tnav", schema = "")
public class Tnav implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	
	private String cid;
	private Tnav tnav;
	private String ciconcls;
	private String cname;
	private BigDecimal cseq;
	private String curl;
	private Set<Tnav> tnavs = new HashSet<Tnav>(0);

	// Constructors

	/** default constructor */
	public Tnav() {
	}

	/** minimal constructor */
	public Tnav(String cid, String cname) {
		this.cid = cid;
		this.cname = cname;
	}

	/** full constructor */
	public Tnav(String cid, Tnav tnav, String ciconcls, String cname, BigDecimal cseq, String curl, Set<Tnav> tnavs) {
		this.cid = cid;
		this.tnav = tnav;
		this.ciconcls = ciconcls;
		this.cname = cname;
		this.cseq = cseq;
		this.curl = curl;
		this.tnavs = tnavs;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CPID")
	public Tnav getTnav() {
		return this.tnav;
	}

	public void setTnav(Tnav tnav) {
		this.tnav = tnav;
	}

	@Column(name = "CICONCLS", length = 100)
	public String getCiconcls() {
		return this.ciconcls;
	}

	public void setCiconcls(String ciconcls) {
		this.ciconcls = ciconcls;
	}

	@Column(name = "CNAME", nullable = false, length = 100)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "CSEQ", precision = 22, scale = 0)
	public BigDecimal getCseq() {
		return this.cseq;
	}

	public void setCseq(BigDecimal cseq) {
		this.cseq = cseq;
	}

	@Column(name = "CURL", length = 200)
	public String getCurl() {
		return this.curl;
	}

	public void setCurl(String curl) {
		this.curl = curl;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tnav")
	public Set<Tnav> getTnavs() {
		return this.tnavs;
	}

	public void setTnavs(Set<Tnav> tnavs) {
		this.tnavs = tnavs;
	}

}