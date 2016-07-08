package com.dj.action.sys;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.dj.action.base.BaseAction;
import com.dj.pageModel.base.Json;
import com.dj.pageModel.sys.Dic;
import com.dj.service.sys.DicServiceI;
import com.opensymphony.xwork2.ModelDriven;

@Action(value = "dic", results = { @Result(name = "dic", location = "/general/admin/dic.jsp") ,@Result(name = "dicAdd", location = "/general/admin/dicAdd.jsp"),@Result(name = "dicEdit", location = "/general/admin/dicEdit.jsp")})
public class DicAction extends BaseAction implements ModelDriven<Dic> {

	private static final long serialVersionUID = 1L;

	private DicServiceI dicService;

	private Dic dic = new Dic();

	public Dic getModel() {
		return dic;
	}

	public DicServiceI getDicService() {
		return dicService;
	}

	@Autowired
	public void setDicService(DicServiceI dicService) {
		this.dicService = dicService;
	}

	public String dic() {
		return "dic";
	}
	
	public String dicAdd() {
		return "dicAdd";
	}
	
	public String dicEdit() {
		return "dicEdit";
	}

	public void datagrid() {
		writeJson(dicService.datagrid(dic));
	}

	public void add() {
		Json j = new Json();
		try {
			dicService.add(dic);
			j.setSuccess(true);
			j.setMsg("添加成功!");
		} catch (RuntimeException e) {
			j.setMsg("添加失败!");
		}
		writeJson(j);
	}

	public void delete() {
		Json j = new Json();
		try {
			dicService.delete(dic.getIds());
			j.setSuccess(true);
			j.setMsg("添加成功!");
		} catch (Exception e) {
			j.setMsg("删除失败!");
		}
		writeJson(j);
	}

	public void edit() {
		Json j = new Json();
		try {
			dicService.update(dic);
			j.setSuccess(true);
			j.setMsg("操作成功!");
		} catch (Exception e) {
			j.setMsg("操作失败!");
		}
		writeJson(j);
	}

	public void change() {
		Json j = new Json();
		try {
			dicService.change(dic);
			j.setSuccess(true);
			j.setMsg("操作成功!");
		} catch (Exception e) {
			j.setMsg("操作失败!");
		}
		writeJson(j);
	}

	public void combox() {
		writeJson(dicService.combobox(dic));
	}
}
