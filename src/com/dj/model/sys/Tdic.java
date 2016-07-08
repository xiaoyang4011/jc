package com.dj.model.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 数据字典 entity
 * 
 * @author lxy
 */

@Entity
@Table(name = "TDIC")
public class Tdic implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	// Fields

	private String cid; 		// ID
	private String ckey;		// key
	private String cvalue;		// value
	private String ctype;		// 类型
	private String cstatus;		// 状态 0启用 1停用

	// Constructors

	/** default constructor */
	public Tdic() {
	}

	/** full constructor */
	public Tdic(String cid, String ckey, String cvalue, String ctype) {
		super();
		this.cid = cid;
		this.ckey = ckey;
		this.cvalue = cvalue;
		this.ctype = ctype;
	}

	@Id
	@Column(name = "CID", nullable = false, length = 36)
	public String getCid() {
		return cid;
	}	

	public void setCid(String cid) {
		this.cid = cid;
	}

	@Column(name = "CKEY", nullable = false, length = 50)
	public String getCkey() {
		return ckey;
	}

	public void setCkey(String ckey) {
		this.ckey = ckey;
	}

	@Column(name = "CVALUE" , length = 50)
	public String getCvalue() {
		return cvalue;
	}

	public void setCvalue(String cvalue) {
		this.cvalue = cvalue;
	}

	@Column(name = "CTYPE" , length = 36)
	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	@Column(name = "CSTATUS" , length = 10)
	public String getCstatus() {
		return cstatus;
	}

	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}

}
