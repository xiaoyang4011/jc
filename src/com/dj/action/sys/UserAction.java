package com.dj.action.sys;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.dj.action.base.BaseAction;
import com.dj.exception.ValidateFieldsException;
import com.dj.pageModel.base.Json;
import com.dj.pageModel.base.SessionInfo;
import com.dj.pageModel.sys.User;
import com.dj.service.sys.MenuServiceI;
import com.dj.service.sys.UserServiceI;
import com.dj.util.base.ExceptionUtil;
import com.dj.util.base.IpUtil;
import com.dj.util.base.ResourceUtil;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户管理ACTION
 * 	
 * @author lxy
 * 
 */
@Action(
		value = "user",
		results = { @Result(name = "user", location = "/general/admin/user.jsp"), 
				    @Result(name = "userAdd", location = "/general/admin/userAdd.jsp"), 
				    @Result(name = "userEdit", location = "/general/admin/userEdit.jsp"), 
				    @Result(name = "userRoleEdit", location = "/general/admin/userRoleEdit.jsp"), 
				    @Result(name = "doNotNeedAuth_userInfo", location = "/general/user/userInfo.jsp"), 
				    @Result(name = "doNotNeedAuth_index", location = "/general/index.jsp"), 
				    @Result(name = "login", location = "/general/user/login.jsp") })
public class UserAction extends BaseAction implements ModelDriven<User> {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(UserAction.class);

	private UserServiceI userService;

	private MenuServiceI menuService;

	public MenuServiceI getMenuService() {
		return menuService;
	}

	@Autowired
	public void setMenuService(MenuServiceI menuService) {
		this.menuService = menuService;
	}

	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}

	private User user = new User();

	public User getModel() {
		return user;
	}

	public String user() {
		return "user";
	}

	public String userAdd() {
		return "userAdd";
	}

	public String userEdit() {
		return "userEdit";
	}

	public String userRoleEdit() {
		return "userRoleEdit";
	}

	public String doNotNeedAuth_userInfo() {
		return "doNotNeedAuth_userInfo";
	}

	public String doNotNeedSession_index() {
		SessionInfo sessionInfo = (SessionInfo) httpSession.getAttribute("sessionInfo");
		if (sessionInfo != null) {
			return "doNotNeedAuth_index";
		} else {
			return "login";
		}
	}

	
	
	
	
	
	
	public void doNotNeedAuth_editUserInfo() {
		Json j = new Json();
		try {
			userService.editUserInfo(user);
			j.setSuccess(true);
			j.setMsg("修改成功！");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("修改失败！");
		}
		super.writeJson(j);
	}

	/**
	 * 用户登录（不需要session）
	 */
	public String doNotNeedSession_login() {
		if (user.getCname() == null || "".equals(user.getCname())) {
			request.setAttribute("msg", "提示：请输入用户名！");
			return "login";
		}
		if (user.getCpwd() == null || "".equals(user.getCpwd())) {
			request.setAttribute("msg", "提示：请输入用户密码！");
			return "login";
		}
		User u = userService.login(user);
		if (u != null) {
			SessionInfo sessionInfo = new SessionInfo();
			sessionInfo.setUserId(u.getCid());
			sessionInfo.setLoginName(user.getCname());
			sessionInfo.setLoginPassword(user.getCpwd());
			sessionInfo.setIp(IpUtil.getIpAddr(ServletActionContext.getRequest()));
			sessionInfo.setMenus(menuService.findAll());
			sessionInfo.setAuthIds(u.getAuthIds());
			sessionInfo.setAuthNames(u.getAuthNames());
			sessionInfo.setRoleIds(u.getRoleIds());
			sessionInfo.setRoleNames(u.getRoleNames());
			sessionInfo.setAuthUrls(u.getAuthUrls());
			httpSession.setAttribute(ResourceUtil.getSessionInfoName(), sessionInfo);
		} else {
			request.setAttribute("msg", "提示：用户名或密码错误!");
			return "login";
		}
		return "doNotNeedAuth_index";
	}

	/**
	 * 用户注销（不需要session）
	 */
	public void doNotNeedSession_logout() {
		ServletActionContext.getRequest().getSession().invalidate();
		Json j = new Json();
		j.setSuccess(true);
		super.writeJson(j);
	}

	/**
	 * 用户注册（不需要session）
	 */
	public void doNotNeedSession_reg() {
		Json j = new Json();
		try {
			userService.save(user);
			j.setMsg("注册成功！");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg("注册失败，用户名已存在！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(j);
	}

	/**
	 * 用户添加
	 */
	public void add() {
		Json j = new Json();
		try {
			userService.save(user);
			j.setMsg("添加成功！");
			j.setSuccess(true);
		} catch (ValidateFieldsException e) {
			j.setMsg(e.getLocalizedMessage());
		}
		super.writeJson(j);
		
	}

	/**
	 * 用户编辑
	 */
	public void edit() {
		Json j = new Json();
		try {
			userService.update(user);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (ValidateFieldsException e) {
			j.setMsg("编辑失败，用户名已存在！");
		}
		super.writeJson(j);
	}

	/**
	 * 用户角色编辑
	 */
	public void roleEdit() {
		Json j = new Json();
		try {
			userService.roleEdit(user);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("编辑失败！");
		}
		super.writeJson(j);
	}

	/**
	 * 用户删除
	 */
	public void delete() {
		Json j = new Json();
		userService.delete(user.getIds());
		j.setSuccess(true);
		j.setMsg("删除成功!");
		super.writeJson(j);
	}

	/**
	 * 获得用户datagrid
	 */
	public void datagrid() {
		super.writeJson(userService.datagrid(user));
	}
}
