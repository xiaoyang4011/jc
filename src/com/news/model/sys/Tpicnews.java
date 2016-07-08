package com.news.model.sys;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "TPICNEWS", schema = "")
public class Tpicnews implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private int pid;
	private Date pdate;
	private String ptitle;
	private String psrc;
	private int psort;
	private String pnews;
	private int ptype;


	
	
	
	


	public Tpicnews() {
	}

	public Tpicnews(int pid,String ptitle,String psrc,int psort,String pnews,int ptype) {
		this.pid=pid;
		this.ptitle=ptitle;
		this.pnews=pnews;
		this.psrc=psrc;
		this.psort=psort;
		this.ptype=ptype;
	}
	public Tpicnews(int pid,String psrc,String ptitle,String pnews) {
		this.pid=pid;
		this.psrc=psrc;
		this.ptitle=ptitle;
		this.pnews=pnews;
		
	}
	public Tpicnews(String pnews) {
		
		this.pnews=pnews;
	
	}
	public Tpicnews(int psort) {
		
		this.psort=psort;
	
	}

	@Id
	@Column(name = "PID",   length = 10)
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	//时间戳
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PDATE", length = 7)
	public Date getPdate() {
		return pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	/**
	 * 标题列
	 */
	@Column(name = "PTITLE",  nullable = true, length = 200)
	public String getPtitle() {
		return ptitle;
	}

	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}
	
	@Column(name = "PSRC", length = 100)
	public String getPsrc() {
		return psrc;
	}

	public void setPsrc(String psrc) {
		this.psrc = psrc;
	}
	@Column(name = "PSORT", length = 10)
	public int getPsort() {
		return psort;
	}

	public void setPsort(int psort) {
		this.psort = psort;
	}
	@Column(name = "Pnews", length = 100)
	public String getPnews() {
		return pnews;
	}

	public void setPnews(String pnews) {
		this.pnews = pnews;
	}
	@Column(name = "PTYPE", length = 10)
	public int getPtype() {
		return ptype;
	}

	public void setPtype(int ptype) {
		this.ptype = ptype;
	}
}
