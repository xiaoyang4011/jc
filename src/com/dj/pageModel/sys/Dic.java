package com.dj.pageModel.sys;

/**
 * 数据字典 entity
 * 
 * @author lxy
 */

public class Dic implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Fields

	private String cid; 		// 部门ID
	private String ckey; 		// key
	private String cvalue;		// value
	private String ctype; 		// 类型
	private String cstatus; 	// 状态 0启用 1停用
	private String ids;			// ids字符串
	private int page; 			// 当前页
	private int rows; 			// 每页显示记录数
	private String sort; 		// 排序字段名
	private String order; 		// 按什么排序(asc,desc)

	// Constructors

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCkey() {
		return ckey;
	}

	public void setCkey(String ckey) {
		this.ckey = ckey;
	}

	public String getCvalue() {
		return cvalue;
	}

	public void setCvalue(String cvalue) {
		this.cvalue = cvalue;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getCstatus() {
		return cstatus;
	}

	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
