package com.news.service.sys.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dj.dao.base.BaseDaoI;
import com.dj.exception.ValidateFieldsException;
import com.dj.service.base.impl.BaseServiceImpl;
import com.news.model.sys.Tnews;
import com.news.model.sys.Tpicnews;
import com.news.pageModel.base.DataGrid;
import com.news.pageModel.sys.News;
import com.news.pageModel.sys.PicNews;
import com.news.service.sys.NewsServiceI;
import com.news.service.sys.PicNewsServiceI;
/*@Service用于标注业务层组件

@Controller用于标注控制层组件（如struts中的action）

@Repository用于标注数据访问组件，即DAO组件

@Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。*/
//注解标注业务层组件，配置spring.xml字段扫描service包
@Service("picnewsService")
public class PicNewsServiceImpl extends BaseServiceImpl implements PicNewsServiceI{
	
	/*数据持就成注入*/
	private BaseDaoI<Tpicnews> picnewsDao;
	
	public BaseDaoI<Tpicnews> getPicNewsDao() {
		return picnewsDao;
	}
	//注入BaseDao层
	@Autowired
	public void setPicNewsDao(BaseDaoI<Tpicnews> picnewsDao) {
		this.picnewsDao = picnewsDao;
	}

	private NewsServiceI newsService;
	
	
	@Autowired
	public void setNewsService(NewsServiceI newsService) {
		this.newsService = newsService;
	}


	public void save(PicNews picnews) throws ValidateFieldsException {
		//实例化数据模型
		Tpicnews t = new Tpicnews();
		t.setPtitle(picnews.getTitle());
		t.setPsort(picnews.getSort());
		t.setPsrc(picnews.getPicfileFileName());
		t.setPdate(new Date());
		t.setPtype(picnews.getType());
		try {
			//存入一个对象
			picnewsDao.save(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(PicNews picnews) {
		DataGrid j = new DataGrid();
		j.setRows(this.changeModel(this.findlist(picnews)));
		return j;
	}
	private List<PicNews> changeModel(List<Tpicnews> tpic) {
		List<PicNews> pic = new ArrayList<PicNews>();
		if (tpic != null && tpic.size() > 0) {
			
			for (Tpicnews tu : tpic) {
				PicNews p = new PicNews();
				p.setId(tu.getPid());
				p.setDate(tu.getPdate());
				p.setSort(tu.getPsort());
				String newsid = tu.getPnews();
				if(newsid==null||newsid.equals("")){
					p.setNews("无链接新闻");
				}else{
					p.setNews(newsService.getTitleById(newsid));
				}
				if(tu.getPtype()==1){
					p.setTtype("首页展示");
				}else{
					p.setTtype("学院风采");
				}
				
				p.setPicfileFileName(tu.getPsrc());
				p.setTitle(tu.getPtitle());
				pic.add(p);
			}
		}
		return pic;
	}
	//前台获取图片查询转变
	private List<PicNews> qchangeModel(List<Tpicnews> tpic) {
		List<PicNews> pic = new ArrayList<PicNews>();
		if (tpic != null && tpic.size() > 0) {
			
			for (Tpicnews tu : tpic) {
				PicNews p = new PicNews();
				p.setId(tu.getPid());
				p.setNews(tu.getPnews());
				p.setTitle(tu.getPtitle());
				p.setPicfileFileName(tu.getPsrc());
				pic.add(p);
			}
		}
		return pic;
	}
	private  List<Tpicnews> findlist(PicNews picnews){
		String hql = "from Tpicnews  where 1=1";
		return picnewsDao.find(hql);
	}
	//前台获取图片查询
	private  List<Tpicnews> qfindlist(PicNews picnews){
		String hql = "select new Tpicnews(t.pid,t.psrc,t.ptitle,t.pnews) from Tpicnews t where t.ptype=1";
		return picnewsDao.find(hql);
	}
	//前台获取图片查询2
	private  List<Tpicnews> qfindlist2(PicNews picnews){
		String hql = "select new Tpicnews(t.pid,t.psrc,t.ptitle,t.pnews) from Tpicnews t where t.ptype=2";
		return picnewsDao.find(hql);
	}
	
	/*获取图片*/
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public String getPicById(int pid) {
		Tpicnews t = picnewsDao.get(Tpicnews.class, pid);
		return t.getPsrc();
	}
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public int counta (int type){
		int a = picnewsDao.count("select count(*) from Tpicnews where ptype="+type+"").intValue();
		return a;
	}
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public int count (int sort,int type){
		int a = picnewsDao.count("select count(*) from Tpicnews where psort="+sort+" and  ptype="+type+"").intValue();
		return a;
	}
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public int count1 (String news){
		int a = picnewsDao.count("select count(*) from Tpicnews where pnews='"+news+"'").intValue();
		return a;
	}
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public String getNewId(int sort) {
		String hql ="select new Tpicnews(t.pnews) from Tpicnews t where ptype=2 and psort="+sort+"";
		List<Object> values = new ArrayList<Object>();
		Tpicnews t = picnewsDao.get(hql, values);
		return t.getPnews();
	}
	/**
	 * 更新一个Newid对象
	 */
	public void update(int sort,String id) throws ValidateFieldsException {

		String hql ="from Tpicnews  where ptype=2 and psort="+sort+"";
		List<Object> values = new ArrayList<Object>();
		Tpicnews t = picnewsDao.get(hql, values);
		
		t.setPnews(id);
		
		
		try {
			//存入一个对象
			picnewsDao.update(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 更新一个Newid对象
	 */
	public void update1(String news) throws ValidateFieldsException {

		String hql ="from Tpicnews  where pnews='"+news+"'";
		List<Object> values = new ArrayList<Object>();
		Tpicnews t = picnewsDao.get(hql, values);
		
		t.setPnews("");
		
		
		try {
			//存入一个对象
			picnewsDao.update(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public int getSort(String id){
		String hql ="select new Tpicnews(t.psort) from Tpicnews t where t.pnews='"+id+"'";
		List<Object> values = new ArrayList<Object>();
		Tpicnews t = picnewsDao.get(hql, values);
		if(t==null){
			
			return 0;
			
		}else{
			return t.getPsort();
		}
	}
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<PicNews> qPicnews(PicNews picnews){
		 List<PicNews> news1 = new ArrayList<PicNews>();
		 news1 =  this.qchangeModel(this.qfindlist(picnews));
		 return news1;
	}

	public List<PicNews> qPicnews2(PicNews picnews){
		 List<PicNews> news1 = new ArrayList<PicNews>();
		 news1 =  this.qchangeModel(this.qfindlist2(picnews));
		 return news1;
	}
	/*删除*/
	public void delete(String pid) {
		if (pid != null) {
			/*,解析每个ID*/
			for (String id : pid.split(",")) {
				if (!id.trim().equals("0")) {
					Tpicnews p = picnewsDao.get(Tpicnews.class, Integer.parseInt(id.trim()));
					if (p != null) {
						
						picnewsDao.delete(p);
					}
				}
			}
		}
	}
	/*获取标题*/
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public String getSrcById(int pid) {
		Tpicnews t = picnewsDao.get(Tpicnews.class, pid);
		
		return t.getPsrc();
	}
}
