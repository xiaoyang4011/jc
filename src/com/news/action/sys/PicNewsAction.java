package com.news.action.sys;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.dj.exception.ValidateFieldsException;
import com.dj.util.base.ExceptionUtil;
import com.google.gson.Gson;
import com.news.action.base.BaseAction;
import com.news.pageModel.base.Json;
import com.news.pageModel.sys.PicNews;
import com.news.service.sys.PicNewsServiceI;
import com.opensymphony.xwork2.ModelDriven;
@Action(value = "picnews",
		results = {
	    @Result(name = "picnews", location = "/general/news/picnews.jsp"),
	    @Result(name = "picnewsAdd", location = "/general/news/picnewsAdd.jsp"),
	     @Result(name = "src", location = "/general/news/ShowPic.jsp")
	    
	  })
//ModelDriven类似Formbeen
public class PicNewsAction extends BaseAction implements ModelDriven<PicNews>{

	private static final long serialVersionUID = 1L;
	
	
	//服务层接口
	private PicNewsServiceI picnewsService;
	

	@Autowired
	public void setPicnewsService(PicNewsServiceI picnewsService) {
		this.picnewsService = picnewsService;
	}


	

	/** 
	 * 载入模型
	 */
	Gson gson = new Gson();
	private PicNews picnews = new PicNews();
	public PicNews getModel() {
	
		return picnews;
	}
	
	/**
	 * 
	 * 图片新闻View
	 */
	public String picnews() {
		return "picnews";
	}
	
	
	//新闻添加（页面跳转）
	public String picnewsAdd() {
		return "picnewsAdd";
	}
	
	public void add() throws ValidateFieldsException  {
		Json j = new Json();
		Long i = picnews.getPicfile().length();
		String name = picnews.getPicfileFileName();
		File filepic = picnews.getPicfile();
		int t =picnews.getType();
		if(t==1){
			String[] str = name.split("\\.");
			String filetype = str[str.length - 1];
			if(picnewsService.count(picnews.getSort(),t)==0){
				if(picnewsService.counta(t)<5){
					if (filetype.equals("gif") || filetype.equals("jpeg")|| filetype.equals("png") || filetype.equals("jpg")) {
						if (i <= 1048576) {
							String realPath = ServletActionContext.getServletContext()
									.getRealPath("/images");// 实际路径
							 
							  
							String picname = "pic"+picnews.getSort()+"."+filetype;
							picnews.setPicfileFileName(picname);
							File saveFile = new File(new File(realPath), picname);
							if (!saveFile.getParentFile().exists()) {
								saveFile.getParentFile().mkdirs();
							}
							try {
								FileUtils.copyFile(filepic, saveFile);
								picnewsService.save(picnews);
								j.setMsg("添加成功");
								j.setSuccess(true);
							} catch (IOException e) {
								j.setMsg("添加失败");
								j.setSuccess(false);
							}
						} else {
							j.setMsg("文件过大，请压缩后上传");
							j.setSuccess(false);
						}
					} else {
						j.setMsg("文件格式不正确");
						j.setSuccess(false);
					}
				}else{
					j.setMsg("图片数量不能超过5张");
					j.setSuccess(false);
				}
			}else{
				j.setMsg("该位置已有图片，请重新选择图片位置（从左往右）");
				j.setSuccess(false);
			}
		}else{
			String[] str = name.split("\\.");
			String filetype = str[str.length - 1];
			if(picnewsService.count(picnews.getSort(),t)==0){
				if(picnewsService.counta(t)<3){
					if (filetype.equals("gif") || filetype.equals("jpeg")|| filetype.equals("png") || filetype.equals("jpg")) {
						if (i <= 1048576) {
							String realPath = ServletActionContext.getServletContext()
									.getRealPath("/images");// 实际路径
							 
							  
							String picname = "xyfcpic"+picnews.getSort()+"."+filetype;
							picnews.setPicfileFileName(picname);
							File saveFile = new File(new File(realPath), picname);
							if (!saveFile.getParentFile().exists()) {
								saveFile.getParentFile().mkdirs();
							}
							try {
								FileUtils.copyFile(filepic, saveFile);
								picnewsService.save(picnews);
								j.setMsg("添加成功");
								j.setSuccess(true);
							} catch (IOException e) {
								j.setMsg("添加失败");
								j.setSuccess(false);
							}
						} else {
							j.setMsg("文件过大，请压缩后上传");
							j.setSuccess(false);
						}
					} else {
						j.setMsg("文件格式不正确");
						j.setSuccess(false);
					}
				}else{
					j.setMsg("学院风采图片数量不能超过3张");
					j.setSuccess(false);
				}
			}else{
				j.setMsg("该位置已有图片，请重新选择图片位置（从上往下）");
				j.setSuccess(false);
			}
		}
		

		
			
		

		super.writeJson(j);

	}
	public void datagrid() {
		super.writeJson(picnewsService.datagrid(picnews));
	}
	/**
	 * 显示内容
	 */
	public String showPic() {
		String src ="/em/images/"+picnewsService.getPicById(picnews.getId());
		ServletActionContext.getRequest().setAttribute("src",src);
		return "src";
	}
	public void test(){
		System.out.print(picnewsService.getNewId(1));
	}
	
	@SuppressWarnings("unchecked")
	public void doNotNeedSession_pic() {

		List list = picnewsService.qPicnews(picnews);
		String json = gson.toJson(list); 
		ajaxResponse(response, json);
	}
	@SuppressWarnings("unchecked")
	public void doNotNeedSession_pic2() {

		List list = picnewsService.qPicnews2(picnews);
		String json = gson.toJson(list); 
		ajaxResponse(response, json);
	}
	//AJAX写入
	public void ajaxResponse(HttpServletResponse response, String text){
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(text);
		} catch (IOException e) {
		}
	}

	/**
	 * 删除一个图片
	 */
	public void delete() {
		Json j = new Json();
		try {
			String id = picnews.getId()+"";
			String src = picnewsService.getSrcById(picnews.getId());
			String realPath = ServletActionContext.getServletContext().getRealPath("/images")+"\\"+src;
			System.out.print(realPath);
			System.out.print(id);
	/*		File targetFile = new File(realPath); 
			  if (targetFile.isDirectory()) {
                  FileUtils.deleteDirectory(targetFile);
			  } else if (targetFile.isFile()) {
                  targetFile.delete();
			  } */
			
			picnewsService.delete(id);
			
			j.setSuccess(true);
			j.setMsg("删除成功！");
			
		} catch (Exception e) {
			System.out.print(123);
			j.setMsg("删除失败！");
		}
		super.writeJson(j);
	}
}


