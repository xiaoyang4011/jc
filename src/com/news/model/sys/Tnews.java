package com.news.model.sys;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "TNEWS", schema = "")
public class Tnews implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nid;
	private Date ncreatedatetime;
	private String ntype;
	private String nauthor;
	private String ncontent;

	private String ntitle;
	private String ntop;
	
	
	
	
	public Tnews() {
	}

	public Tnews(String nid,String ntitle,String ncontent,String nauthor,String ntop,String ntype) {
		this.nid=nid;
		this.ntitle=ntitle;
		this.ncontent=ncontent;
		this.nauthor=nauthor;
		this.ntop=ntop;
		this.ntype=ntype;
	}
	public Tnews(String nid,String ntitle,Date ncreatedatetime) {
		this.nid=nid;
		this.ntitle=ntitle;
		this.ncreatedatetime=ncreatedatetime;
		
	}

	@Id
	@Column(name = "NID",   length = 100)
	
	public String getNid() {
		return this.nid;
	}


	public void setNid(String nid) {
		this.nid = nid;
	}
	//时间戳
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "NCREATEDATETIME", length = 7)
	public Date getNcreatedatetime() {
		return this.ncreatedatetime;
	}
	public void setNcreatedatetime(Date ncreatedatetime) {
		this.ncreatedatetime = ncreatedatetime;
	}

	/**
	 * 标题列
	 */
	@Column(name = "NTITLE",  nullable = true, length = 500)
	public String getNtitle() {
		return this.ntitle;
	}
	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}

	/**
	 * 内容列
	 */
	@Column(name = "NCONTENT",  nullable = true, length = 5000)
	public String getNcontent() {
		return this.ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}

	/**
	 * 作者列
	 */
	@Column(name = "NAUTHOR",  nullable = true, length = 100)
	public String getNauthor() {
		return this.nauthor;
	}
	public void setNauthor(String nauthor) {
		this.nauthor = nauthor;
	};
	/**
	 * 类型列
	 */
	@Column(name = "NTYPE",  nullable = true, length = 10)
	public String getNtype() {
		return ntype;
	}
	public void setNtype(String ntype) {
		this.ntype = ntype;
	}
	/**
	 * 置顶标识
	 */
	@Column(name = "NTOP", length = 10)
	public String getNtop() {
		return ntop;
	}
	public void setNtop(String ntop) {
		this.ntop = ntop;
	}
	
}
