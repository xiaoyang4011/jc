package com.news.service.sys.impl;
import java.text.DateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.dj.dao.base.BaseDaoI;
import com.dj.exception.ValidateFieldsException;
import com.dj.service.base.impl.BaseServiceImpl;
import com.news.model.sys.Tnews;
import com.news.pageModel.base.DataGrid;
import com.news.pageModel.sys.News;
import com.news.service.sys.NewsServiceI;
/*@Service用于标注业务层组件

@Controller用于标注控制层组件（如struts中的action）

@Repository用于标注数据访问组件，即DAO组件

@Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。*/
//注解标注业务层组件，配置spring.xml字段扫描service包
@Service("newsService")
public class NewsServiceImpl extends BaseServiceImpl implements NewsServiceI{
	
	/*数据持就成注入*/
	private BaseDaoI<Tnews> newsDao;
	
	public BaseDaoI<Tnews> getNewsDao() {
		return newsDao;
	}
	//注入BaseDao层
	@Autowired
	public void setNewsDao(BaseDaoI<Tnews> newsDao) {
		this.newsDao = newsDao;
	}



	public void save(News news) throws ValidateFieldsException {
		//实例化数据模型
		Tnews t = new Tnews();
		
		t.setNauthor(news.getAuthor());
		t.setNid(UUID.randomUUID().toString());
		t.setNcreatedatetime(new Date());
		t.setNtitle(news.getTitle());
		t.setNcontent(news.getContent());
		t.setNtype(news.getType());
		t.setNtop(news.getTop());
		try {
			//存入一个对象
			newsDao.save(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(News news) {
		DataGrid j = new DataGrid();
		j.setRows(this.changeModel(this.find(news)));
		j.setTotal(this.total(news));
		return j;
	}

	/*前台分页查询*/
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<News> pnews1(News news){
		
		 List<News> news1 = new ArrayList<News>();
		 news1 =  this.qchangeModel(this.findlist(news));
		 return news1;
	
	}
	
	/**
	 * 
	 * 前台数据获取

	 */
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<News> cnews1(News news){
		 news.setType("1");
		 List<News> news1 = new ArrayList<News>();
		 news1 =  this.qchangeModel(this.find1(news,5));
		 return news1;
	}
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<News> cnews2(News news){
		 news.setType("2");
		 List<News> news1 = new ArrayList<News>();
		 news1 =  this.qchangeModel(this.find1(news,8));
		 return news1;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<News> cnews3(News news){
		 news.setType("3");
		 List<News> news1 = new ArrayList<News>();
		 news1 =  this.qchangeModel(this.find1(news,8));
		 return news1;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<News> cnews4(News news){
	   	 news.setType("4");
		 List<News> news1 = new ArrayList<News>();
		 news1 =  this.qchangeModel(this.find1(news,8));
		 return news1;
	}

	
	
	//-----------------------------------------------------------------------------
	
//获取页数
	public int count (int i){
		int a = newsDao.count("select count(*) from Tnews").intValue();
		if(a%i==0){
			return a/i;
		}else{
			return a/i+1;
		}
	}
	public int count (News news){
		int a = newsDao.count("select count(*) from Tnews where ntype="+news.getType()+"").intValue();
		return a;
	}
	
	/*数据类型转化*/
	private List<News> changeModel(List<Tnews> tnews) {
		List<News> news = new ArrayList<News>();
		if (tnews != null && tnews.size() > 0) {
			
			for (Tnews tu : tnews) {
				News n = new News();
				
				n.setIds(tu.getNid());
				n.setTitle(tu.getNtitle());
				n.setAuthor(tu.getNauthor());
				n.setContent(tu.getNcontent());
				n.setCreatedatetime(tu.getNcreatedatetime());
				int top =Integer.parseInt(tu.getNtop());
				if(top==1){
					
					n.setTop("否");
				}else{
					
					n.setTop("是");
				}
				int type =Integer.parseInt(tu.getNtype());
				switch(type) 
				{ 
				case 1: 
					n.setType("通知公告");
				break;                                                                                                                                              
				case 2: 
					n.setType("新闻中心");
				break; 
				case 3: 
					n.setType("活动中心");
				break; 
				case 4: 
					n.setType("教学科研");
				break;
				case 5: 
					n.setType("学生工作");
				break;
			
				case 6: 
					n.setType("下载专区");
				break;
				case 7: 
					n.setType("学院风采");
				break;
				case 8: 
					n.setType("其他");
				break;
				} 
				
				
				

				news.add(n);
			}
		}
		return news;
	}
	private List<News> qchangeModel(List<Tnews> tnews) {
		List<News> news = new ArrayList<News>();
		if (tnews != null && tnews.size() > 0) {
			
			for (Tnews tu : tnews) {
				News n = new News();
				n.setIds(tu.getNid());
				n.setTitle(tu.getNtitle());
				n.setCtime((DateFormat.getDateInstance().format(tu.getNcreatedatetime())));
				news.add(n);
			}
		}
		return news;
	}
	private String addWhere(News news, String hql, List<Object> values) {
		if (news.getType() != null &&!news.getType().equals("")) {
			hql += " and t.ntype = "+news.getType()+"";
			
		}
		if (news.getTitle() != null &&!news.getTitle().equals("")) {
			hql += " and t.ntitle like '%"+news.getTitle()+"%' ";
			
		}
	
		System.out.print(hql);
		return hql;
	}

	private  List<Tnews> find1(News news,int i){
		String hql = "select new Tnews(t.nid,t.ntitle,t.ncreatedatetime) from Tnews t where 1=1";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(news, hql, values);
		news.setSort("t.ncreatedatetime");
		news.setOrder("desc");
		if (news.getSort() != null && news.getOrder() != null) {
			hql += " order by ntop," + news.getSort() + " " + news.getOrder();
		}
		return newsDao.find(hql,i);
	}
	//List 列表查询
	private  List<Tnews> findlist(News news){
		String hql = "select new Tnews(t.nid,t.ntitle,t.ncreatedatetime) from Tnews t where 1=1";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(news, hql, values);
		news.setSort("t.ncreatedatetime");
		news.setOrder("desc");
		if (news.getSort() != null && news.getOrder() != null) {
			hql += " order by  ntop," + news.getSort() + " " + news.getOrder();
		}
		return newsDao.find(hql, values, news.getPage(), news.getRows());
	}
	private List<Tnews> find(News news) {
		String hql = "from Tnews t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(news, hql, values);
		news.setSort("NCREATEDATETIME");
		news.setOrder("desc");
		if (news.getSort() != null && news.getOrder() != null) {
			hql += " order by ntop," + news.getSort() + " " + news.getOrder();
		}
		
		return newsDao.find(hql, values, news.getPage(), news.getRows());
	
	}
	private Long total(News news) {
		String hql = "select count(*) from Tnews t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(news, hql, values);
		return newsDao.count(hql, values);
	}
	/*获取内容*/
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public String getContentById(String nid) {
		Tnews t = newsDao.get(Tnews.class, nid);
		
		return t.getNcontent();
	}
	/*获取标题*/
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public String getTitleById(String nid) {
		Tnews t = newsDao.get(Tnews.class, nid);
		
		return t.getNtitle();
	}
	//前台获取内容
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<News> getAllById(String nid) {
		Tnews t = newsDao.get(Tnews.class, nid);
		List<News> news = new ArrayList<News>();
		News n = new News();
		n.setIds(t.getNid());
		n.setTitle(t.getNtitle());
		n.setCtime((DateFormat.getDateInstance().format(t.getNcreatedatetime())));
		n.setContent(t.getNcontent());
		n.setAuthor(t.getNauthor());
		news.add(n);
		return news;
	}
	/*获取状态*/
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public String getTypeById(String nid) {
		Tnews t = newsDao.get(Tnews.class, nid);
		
		return t.getNtype();
	}
	/*获取置顶信息*/
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public String getTopById(String nid) {
		Tnews t = newsDao.get(Tnews.class, nid);
		
		return t.getNtop();
	}
	/*改变Type值*/
	public void updateTop(String nid,String type) {
		Tnews t = newsDao.get(Tnews.class, nid);
		if(Integer.parseInt(type) == 1)
		{
			t.setNtop("1");
		}else{
			t.setNtop("0");
		}
		newsDao.update(t);
	}
	/*删除*/
	public void delete(String nid) {
		if (nid != null) {
			/*,解析每个ID*/
			for (String id : nid.split(",")) {
				if (!id.trim().equals("0")) {
					Tnews n = newsDao.get(Tnews.class, id.trim());
					if (n != null) {
						
						newsDao.delete(n);
					}
				}
			}
		}
	}
	
	/*更新对象*/
	public void update(News news) throws ValidateFieldsException {

		Tnews t = newsDao.get(Tnews.class, news.getIds());
		
		t.setNtitle(news.getTitle());
		t.setNauthor(news.getAuthor());
		t.setNcontent(news.getContent());
		t.setNtype(news.getType());
		t.setNtop(news.getTop());
		
		
		try {
			//存入一个对象
			newsDao.update(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
