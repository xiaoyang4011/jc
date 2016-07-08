package com.news.action.sys;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.dj.action.base.BaseAction;
import com.dj.pageModel.base.Json;
import com.dj.pageModel.sys.Menu;
import com.dj.service.sys.MenuServiceI;
import com.dj.util.base.ExceptionUtil;
import com.news.pageModel.sys.Nav;
import com.news.service.sys.NavServiceI;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 菜单ACTION
 * 
 * @author lxy
 * 
 */
@Action(value = "nav", results = { 
		@Result(name = "nav", location = "/general/news/nav.jsp"),
		@Result(name = "navAdd", location = "/general/news/navAdd.jsp"),
		@Result(name = "navEdit", location = "/general/news/navEdit.jsp") })
public class NavAction extends BaseAction implements ModelDriven<Nav> {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(NavAction.class);

	private NavServiceI navService;

	public NavServiceI getNavService() {
		return navService;
	}

	@Autowired
	public void setNavService(NavServiceI navService) {
		this.navService = navService;
	}

	private Nav nav = new Nav();
	
	public Nav getModel() {
		return nav;
	}
	
	public String navAdd() {
		return "navAdd";
	}
	
	public String navEdit() {
		return "navEdit";
	}

	/**
	 * 动态加载菜单树（当展开下级时才会加载下级菜单）
	 */
	public void doNotNeedSession_tree() {
		super.writeJson(navService.tree(nav, false));
	}
	
	public void doNotNeedSession_tree1() {
		super.writeJson("{111}");
	}

	/**
	 * 一次性加载全部菜单树
	 */
	public void doNotNeedSession_treeRecursive() {
		super.writeJson(navService.tree(nav, true));
	}

	public String nav() {
		return "nav";
	}

	/**
	 * 获得菜单treegrid
	 */
	public void treegrid() {
		super.writeJson(navService.treegrid(nav));
	}
	public void doNotNeedSession_treegrid() {
		super.writeJson(navService.treegrid(nav));
	}

	/**
	 * 删除一个菜单
	 */
	public void delete() {
		Json j = new Json();
		try {
			navService.delete(nav);
			j.setSuccess(true);
			j.setMsg("删除成功！");
			j.setObj(nav.getCid());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("删除失败！");
		}
		super.writeJson(j);
	}

	public void add() {
		Json j = new Json();
		try {
			if(nav.getPid()==null||nav.getPid().equals("")){
				if(navService.count()<8){
					navService.add(nav);
					j.setSuccess(true);
					j.setMsg("添加成功！");
				}else{
					j.setSuccess(true);
					j.setMsg("导航设定最多为8个根节点，请重新选择！");
				}
			}else{
				//查询父亲是否为NULL
				//父节点为NULL添加
				String PID = nav.getPid();
			   
				if(navService.getPidById(nav.getPid())==null||navService.getPidById(nav.getPid()).equals("")){
					navService.add(nav);
					j.setSuccess(true);
					j.setMsg("添加成功！");
				}else{
					j.setSuccess(true);
					j.setMsg("导航最多划分为2级，请重新选择！");
				}
			}
		
			
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("添加失败！");
		}
		super.writeJson(j);
	}


	/**
	 * 编辑菜单
	 */
	public void edit() {
		Json j = new Json();
		try {
			navService.edit(nav);
			j.setSuccess(true);
			j.setMsg("编辑成功!");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("编辑失败！");
		}
		writeJson(j);
	}
public void test(){
	System.out.print(navService.getPidById("2014102116255"));
	
}

}
