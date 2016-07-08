package com.dj.service.sys;

import java.util.List;

import com.dj.pageModel.base.DataGrid;
import com.dj.pageModel.sys.Dic;

/**
 * 数据字典Service
 * 
 * @author lxy
 * 
 */
public interface DicServiceI {

	public void add(Dic dic);

	public DataGrid datagrid(Dic dic);

	public void delete(String ids);

	public void update(Dic dic);

	public void change(Dic dic);

	public List<Dic> combobox(Dic dic);

}
