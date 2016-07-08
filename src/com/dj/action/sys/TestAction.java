package com.dj.action.sys;

import org.apache.struts2.convention.annotation.Action;
import com.dj.action.base.BaseAction;


/**
 * 在线用户列表ACTION
 * 
 * @author lxy
 * 
 */
@SuppressWarnings("serial")
@Action(value = "online1")
public class TestAction extends BaseAction {

	
	
	

	
	public void dao() {
		System.out.print("ceshi");
	}
}
