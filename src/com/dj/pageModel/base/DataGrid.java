package com.dj.pageModel.base;

import java.util.List;

/**
 * easyui的datagrid模型
 * 
 * @author lxy
 * 
 */
@SuppressWarnings("serial")
public class DataGrid implements java.io.Serializable {

	private Long total;// 总记录数
	@SuppressWarnings("unchecked")
	private List rows;// 每行记录
	@SuppressWarnings("unchecked")
	private List footer;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	@SuppressWarnings("unchecked")
	public List getRows() {
		return rows;
	}

	@SuppressWarnings("unchecked")
	public void setRows(List rows) {
		this.rows = rows;
	}

	@SuppressWarnings("unchecked")
	public List getFooter() {
		return footer;
	}

	@SuppressWarnings("unchecked")
	public void setFooter(List footer) {
		this.footer = footer;
	}

}
