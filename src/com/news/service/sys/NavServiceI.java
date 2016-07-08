package com.news.service.sys;

import java.util.List;

import com.dj.pageModel.base.TreeNode;
import com.dj.pageModel.sys.Menu;
import com.news.model.sys.Tnav;
import com.news.pageModel.sys.Nav;

public interface NavServiceI {

	/**
	 * 获取导航树
	 * 
	 * @param auth
	 * @param b
	 *            是否递归子节点
	 * @return
	 */
	public List<TreeNode> tree(Nav nav, Boolean b);

	/**
	 * 获得导航treegrid
	 * 
	 * @param nav
	 * @return
	 */
	public List<Nav> treegrid(Nav nav);

	/**
	 * 删除导航
	 * 
	 * @param nav
	 */
	public void delete(Nav nav);

	/**
	 * 添加导航
	 * 
	 * @param nav
	 */
	public void add(Nav nav);

	/**
	 * 编辑导航
	 * 
	 * @param nav
	 */
	public void edit(Nav nav);
	
	/**
	 * 查询所有导航项
	 * 
	 * @param nav
	 */
	public List<Nav> findAll();
	/**
	 * 查询所有导航项
	 * 
	 * @param nav
	 */
	public int count ();
	public String getPidById(String id);
	

}
