package com.dj.service.sys;

import com.dj.pageModel.base.DataGrid;
import com.dj.pageModel.sys.Online;

public interface OnlineServiceI {

	public void saveOrUpdateTonlineByLoginNameAndIp(String loginName, String ip);

	public void deleteTonlineByLoginNameAndIp(String loginName, String ip);

	public DataGrid datagrid(Online online);

}
