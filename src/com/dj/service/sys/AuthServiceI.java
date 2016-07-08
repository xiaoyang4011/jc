package com.dj.service.sys;

import java.util.List;

import com.dj.pageModel.base.TreeNode;
import com.dj.pageModel.sys.Auth;

public interface AuthServiceI {

	public List<Auth> treegrid(Auth auth);

	public void delete(Auth auth);

	public void edit(Auth auth);

	public void add(Auth auth);

	/**
	 * 获取权限树
	 * 
	 * @param auth
	 * @param b
	 *            是否递归子节点
	 * @return
	 */
	public List<TreeNode> tree(Auth auth, boolean b);

}
