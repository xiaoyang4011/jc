package com.dj.service.sys;


import com.dj.exception.ValidateFieldsException;
import com.dj.pageModel.base.DataGrid;
import com.dj.pageModel.sys.User;

public interface UserServiceI {

	public User login(User user);

	public void save(User user) throws ValidateFieldsException;

	public DataGrid datagrid(User user);

	public void delete(String ids);

	public void update(User user) throws ValidateFieldsException;

	public void roleEdit(User user);

	public void editUserInfo(User user);


}
