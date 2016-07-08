package com.news.service.sys;

import java.util.List;

import com.dj.exception.ValidateFieldsException;
import com.news.model.sys.Tnews;
import com.news.pageModel.base.DataGrid;
import com.news.pageModel.sys.News;
/*
 * author lxy
 * 新闻模块接口
 * */
public interface NewsServiceI {
	//存储接口
	public void save(News news) throws ValidateFieldsException;
	//获取数据接口
	public DataGrid datagrid(News news);
	//根据ID查询内容接口
	public String getContentById(String nid);
	//查询Type
	public String getTypeById(String nid);
	public String getTitleById(String nid);
	//查询Top
	public String getTopById(String nid); 
	//更新置顶接口
	public void updateTop(String nid,String top);
	//删除接口
	public void delete(String nid);
	//更新新闻数据
	public void update(News news) throws ValidateFieldsException;
	public List<News> pnews1(News news);
	public int count (int i);
	public int count (News news);
	public List<News> cnews1(News news);
	public List<News> cnews2(News news);
	public List<News> cnews3(News news);
	public List<News> cnews4(News news);

	//前台获取内容
	public List<News> getAllById(String nid);


}
