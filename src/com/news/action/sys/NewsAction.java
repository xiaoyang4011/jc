package com.news.action.sys;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import com.dj.exception.ValidateFieldsException;
import com.google.gson.Gson;
import com.news.action.base.BaseAction;
import com.news.pageModel.base.Json;
import com.news.pageModel.sys.News;
import com.news.service.sys.NewsServiceI;
import com.news.service.sys.PicNewsServiceI;
import com.opensymphony.xwork2.ModelDriven;
@Action(value = "news",
		results = { @Result(name = "news", location = "/general/news/news.jsp"), 
	    @Result(name = "newsAdd", location = "/general/news/newsAdd.jsp"),
	    @Result(name = "content", location = "/general/news/ShowContent.jsp"),
	    @Result(name = "newsEdit", location = "/general/news/newsEdit.jsp"),
	    @Result(name = "nav", location = "/general/news/nav.jsp"),

	    @Result(name = "picnewsAdd", location = "/general/news/picnewsAdd.jsp"),
	    @Result(name = "pnews1", location = "/xy/list.jsp"),
	    @Result(name = "pnews2", location = "/xy/list.jsp"),
	    @Result(name = "pnews3", location = "/xy/list.jsp"),
	    @Result(name = "pnews4", location = "/xy/list.jsp"),
	    @Result(name = "pnews5", location = "/xy/list.jsp"),
	    @Result(name = "pnews6", location = "/xy/list.jsp"),
	    @Result(name = "pnews7", location = "/xy/list.jsp"),
	    @Result(name = "show", location = "/xy/news_show.jsp")
	    
	  })
//ModelDriven类似Formbeen
public class NewsAction extends BaseAction implements ModelDriven<News>{

	private static final long serialVersionUID = 1L;
	
	private NewsServiceI newsService;
	
	
	@Autowired
	public void setNewsService(NewsServiceI newsService) {
		this.newsService = newsService;
	}
	private PicNewsServiceI picnewsService;
	

	@Autowired
	public void setPicnewsService(PicNewsServiceI picnewsService) {
		this.picnewsService = picnewsService;
	}

	/**
	 * 
	 * 导航载入View
	 */
	public String nav() {
		return "nav";
	}

	/**
	 * 
	 * 图片新闻View
	 */
	public String picnews() {
		return "picnews";
	}
	/** 
	 * 载入模型
	 */
	Gson gson = new Gson();
	private News news = new News();
	public News getModel() {
	
		return news;
	}
	
	
	//新闻信息主页显示
	public String news() {
		return "news";
	}
	/** 
	 * 前台分页查询
	 */
	public String doNotNeedSession_pnews1() {
		news.setRows(10);
		news.setType("1");
		ServletActionContext.getRequest().setAttribute("page",news.getPage());
		ServletActionContext.getRequest().setAttribute("pagecount",newsService.count(10));
		ServletActionContext.getRequest().setAttribute("count",newsService.count(news));
		ServletActionContext.getRequest().setAttribute("list", newsService.pnews1(news));
		return "pnews1";
	}
	public String doNotNeedSession_pnews2() {
		news.setRows(10);
		news.setType("2");
		ServletActionContext.getRequest().setAttribute("page",news.getPage());
		ServletActionContext.getRequest().setAttribute("pagecount",newsService.count(10));
		ServletActionContext.getRequest().setAttribute("count",newsService.count(news));
		ServletActionContext.getRequest().setAttribute("list", newsService.pnews1(news));
		return "pnews2";
	}
	public String doNotNeedSession_pnews3() {
		news.setRows(10);
		news.setType("3");
		ServletActionContext.getRequest().setAttribute("page",news.getPage());
		ServletActionContext.getRequest().setAttribute("pagecount",newsService.count(10));
		ServletActionContext.getRequest().setAttribute("count",newsService.count(news));
		ServletActionContext.getRequest().setAttribute("list", newsService.pnews1(news));
		return "pnews3";
	}
	public String doNotNeedSession_pnews4() {
		news.setRows(10);
		news.setType("4");
		ServletActionContext.getRequest().setAttribute("page",news.getPage());
		ServletActionContext.getRequest().setAttribute("pagecount",newsService.count(10));
		ServletActionContext.getRequest().setAttribute("count",newsService.count(news));
		ServletActionContext.getRequest().setAttribute("list", newsService.pnews1(news));
		return "pnews4";
	}
	public String doNotNeedSession_pnews5() {
		news.setRows(10);
		news.setType("5");
		ServletActionContext.getRequest().setAttribute("page",news.getPage());
		ServletActionContext.getRequest().setAttribute("pagecount",newsService.count(10));
		ServletActionContext.getRequest().setAttribute("count",newsService.count(news));
		ServletActionContext.getRequest().setAttribute("list", newsService.pnews1(news));
		return "pnews5";
	}
	public String doNotNeedSession_pnews6() {
		news.setRows(10);
		news.setType("6");
		ServletActionContext.getRequest().setAttribute("page",news.getPage());
		ServletActionContext.getRequest().setAttribute("pagecount",newsService.count(10));
		ServletActionContext.getRequest().setAttribute("count",newsService.count(news));
		ServletActionContext.getRequest().setAttribute("list", newsService.pnews1(news));
		return "pnews6";
	}
	public String doNotNeedSession_pnews7() {
		news.setRows(10);
		news.setType("7");
		ServletActionContext.getRequest().setAttribute("page",news.getPage());
		ServletActionContext.getRequest().setAttribute("pagecount",newsService.count(10));
		ServletActionContext.getRequest().setAttribute("count",newsService.count(news));
		ServletActionContext.getRequest().setAttribute("list", newsService.pnews1(news));
		return "pnews7";
	}

	
	
	//通知公告
	@SuppressWarnings("unchecked")
	public void doNotNeedSession_cnews1() {

		List list = newsService.cnews1(news);
		String json = gson.toJson(list); 
		ajaxResponse(response, json);
	}
	@SuppressWarnings("unchecked")
	public void doNotNeedSession_cnews2() {
		
		List list = newsService.cnews2(news);
		String json = gson.toJson(list); 
		ajaxResponse(response, json);
	}
	@SuppressWarnings("unchecked")
	public void doNotNeedSession_cnews3() {
		
		List list = newsService.cnews3(news);
		String json = gson.toJson(list); 
		ajaxResponse(response, json);
	}
	@SuppressWarnings("unchecked")
	public void doNotNeedSession_cnews4() {

		List list = newsService.cnews4(news);
		String json = gson.toJson(list); 
		ajaxResponse(response, json);
	}
	//前段AJAX写入
	public void ajaxResponse(HttpServletResponse response, String text){
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(text);
		} catch (IOException e) {
		}
	}
	//新闻添加（页面跳转）
	public String newsAdd() {
		return "newsAdd";
	}
	
	//新闻添加（页面跳转）
	public String picnewsAdd() {
		return "picnewsAdd";
	}
	
	public void add() {
	
		Json j = new Json();
		try {
		
			newsService.save(news);
			j.setMsg("添加成功！");
			j.setSuccess(true);
		} catch (ValidateFieldsException e) {
			j.setMsg(e.getLocalizedMessage());
		}
		super.writeJson(j);
	}


	/**
	 * 获得新闻datagrid
	 */
	public void datagrid() {

		super.writeJson(newsService.datagrid(news));
	}
	/**
	 * 显示内容
	 */
	public String showContent() {
		ServletActionContext.getRequest().setAttribute("content", newsService.getContentById(news.getIds()));
		return "content";
	}
	/**
	 * 显示内容(前台)
	 */
	public String doNotNeedSession_showAllContent() {
		ServletActionContext.getRequest().setAttribute("show", newsService.getAllById(news.getIds()));
		return "show";
	}

	/**
	 * 删除新闻
	 */
	public void delete() {
		Json j = new Json();
		newsService.delete(news.getIds());
		j.setSuccess(true);
		j.setMsg("删除成功!");
		super.writeJson(j);
	}
	public String newsEdit() {
		ServletActionContext.getRequest().setAttribute("content", newsService.getContentById(news.getIds()));
		ServletActionContext.getRequest().setAttribute("type", newsService.getTypeById(news.getIds()));
		ServletActionContext.getRequest().setAttribute("top", newsService.getTopById(news.getIds()));
		String a = picnewsService.getSort(news.getIds())+"";
		ServletActionContext.getRequest().setAttribute("news_link",a);
		return "newsEdit";
	}
	/**
	 * 编辑新闻
	 */
	public void edit() {
		Json j = new Json();
		try {
			if(news.getPic_news()!=0){
				//check检查该位置图片是否有新闻
				
				if(picnewsService.getNewId(news.getPic_news())== null||picnewsService.getNewId(news.getPic_news()).equals("")){
					picnewsService.update(news.getPic_news(), news.getIds());
				}else{
					j.setSuccess(false);
					j.setMsg("该图片位置以有新闻链接，请重新选择！");
					super.writeJson(j);
					return;
				}
			}else{
				if(picnewsService.count1(news.getIds())>0){
					picnewsService.update1(news.getIds());
				}
			}
			newsService.update(news);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (ValidateFieldsException e) {
			j.setMsg("编辑失败！");
		}
		super.writeJson(j);
	}
	public void test(){
		System.out.print(picnewsService.getSort("f56ffbb7-2be3-4e4b-8114-ae72831802c"));
	}
	
}


