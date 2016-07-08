package com.news.pageModel.sys;
import java.util.Date;


public class News implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String ids;
	private String author;
	private String title;
	private String content;
	private Date createdatetime;
	private String type;
	private String top;
	private String content1;
	private String qcount;
	private String ctime;
	private int pic_news;
	
	
	
	public int getPic_news() {
		return pic_news;
	}
	public void setPic_news(int picNews) {
		pic_news = picNews;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	public String getQcount() {
		return qcount;
	}
	public void setQcount(String qcount) {
		this.qcount = qcount;
	}
	public String getContent1() {
		return content1;
	}
	public void setContent1(String content1) {
		this.content1 = content1;
	}
	private int page;// 当前页
	private int rows;// 每页显示记录数
	private int pagecount;// 每页显示记录数
	
	public int getPagecount() {
		return pagecount;
	}
	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}
	private String sort;// 排序字段名
	private String order;// 按什么排序(asc,desc)
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
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatedatetime() {
		return createdatetime;
	}
	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTop() {
		return top;
	}
	public void setTop(String top) {
		this.top = top;
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
	
}
