package com.news.service.sys;

import java.util.List;

import com.dj.exception.ValidateFieldsException;
import com.news.pageModel.base.DataGrid;
import com.news.pageModel.sys.PicNews;

public interface PicNewsServiceI {
	public void save(PicNews picnews) throws ValidateFieldsException;
	public DataGrid datagrid(PicNews picnews);
	public String getPicById(int pid);
	public int counta (int type);
	public int count (int sort,int type);
	public String getNewId(int sort);
	public void update(int sort,String id) throws ValidateFieldsException ;
	public int getSort(String id);
	public List<PicNews> qPicnews(PicNews picnews);
	public List<PicNews> qPicnews2(PicNews picnews);
	public void delete(String pid);
	public String getSrcById(int pid);
	public int count1 (String news);
	public void update1(String news) throws ValidateFieldsException;
}
