package com.dj.service.sys;

import java.util.List;

import com.dj.pageModel.base.DataGrid;
import com.dj.pageModel.sys.Role;

public interface RoleServiceI {

	public DataGrid datagrid(Role role);

	public void add(Role role);

	public void edit(Role role);

	public void delete(String ids);

	public List<Role> combobox();

}
