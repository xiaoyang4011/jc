package com.dj.action.sys;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.dj.action.base.BaseAction;
import com.dj.pageModel.sys.Online;
import com.dj.service.sys.OnlineServiceI;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 在线用户列表ACTION
 * 
 * @author lxy
 * 
 */
@Action(value = "online")
public class OnlineAction extends BaseAction implements ModelDriven<Online> {

	private static final long serialVersionUID = 1L;
	
	private Online online = new Online();

	public Online getModel() {
		return online;
	}

	private OnlineServiceI onlineService;

	public OnlineServiceI getOnlineService() {
		return onlineService;
	}

	@Autowired
	public void setOnlineService(OnlineServiceI onlineService) {
		this.onlineService = onlineService;
	}

	public void doNotNeedSession_onlineDatagrid() {
		super.writeJson(onlineService.datagrid(online));
	}

}
