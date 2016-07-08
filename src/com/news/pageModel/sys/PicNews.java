package com.news.pageModel.sys;
import java.io.File;
import java.util.Date;




public class PicNews implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private Date date;
	private String src;
	private String news;
	private int sort;
	private File picfile;
	private String picfileFileName;
	private String page;
	private String rows;
	private int type;
	private String ttype;
	
	
	
	

	public String getTtype() {
		return ttype;
	}
	public void setTtype(String ttype) {
		this.ttype = ttype;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public String getPicfileFileName() {
		return picfileFileName;
	}
	public void setPicfileFileName(String picfileFileName) {
		this.picfileFileName = picfileFileName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getNews() {
		return news;
	}
	public void setNews(String news) {
		this.news = news;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int i) {
		this.sort = i;
	}
	public File getPicfile() {
		return picfile;
	}
	public void setPicfile(File picfile) {
		this.picfile = picfile;
	}



	
	
}
