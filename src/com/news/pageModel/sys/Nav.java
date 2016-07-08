package com.news.pageModel.sys;

import java.math.BigDecimal;
import com.news.model.sys.Tnav;

public class Nav implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String pid;
	private String pname;
	private String state = "open";// 是否展开(open,closed)
	private String iconCls;// 前面的小图标样式

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String cid;
	private Tnav tnav;
	private String ciconcls;
	private String cname;
	private BigDecimal cseq;
	private String curl;

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public Tnav getTnav() {
		return tnav;
	}

	public void setTnav(Tnav tnav) {
		this.tnav = tnav;
	}

	public String getCiconcls() {
		return ciconcls;
	}

	public void setCiconcls(String ciconcls) {
		this.ciconcls = ciconcls;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public BigDecimal getCseq() {
		return cseq;
	}

	public void setCseq(BigDecimal cseq) {
		this.cseq = cseq;
	}

	public String getCurl() {
		return curl;
	}

	public void setCurl(String curl) {
		this.curl = curl;
	}

}
