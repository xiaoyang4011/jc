package com.news.service.sys.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.dj.dao.base.BaseDaoI;
import com.dj.pageModel.base.TreeNode;
import com.dj.service.base.impl.BaseServiceImpl;
import com.news.comparator.NavComparator;
import com.news.model.sys.Tnav;
import com.news.model.sys.Tpicnews;
import com.news.pageModel.sys.Nav;
import com.news.service.sys.NavServiceI;

@Service("navService")
public class NavServiceImpl extends BaseServiceImpl implements NavServiceI {

	private BaseDaoI<Tnav> navDao;

	public BaseDaoI<Tnav> getNavDao() {
		return navDao;
	}

	@Autowired
	public void setNavDao(BaseDaoI<Tnav> navDao) {
		this.navDao = navDao;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Nav> findAll() {
		List<Nav> lm = new ArrayList<Nav>();
		String hql = "from Tnav t order by t.cseq";
		List<Tnav> ltm = navDao.find(hql);
		for (Tnav tm : ltm) {
			Nav m = new Nav();
			m.setCid(tm.getCid());
			m.setCname(tm.getCname());
			m.setCiconcls(tm.getCiconcls());
			m.setCurl(tm.getCurl());
			if(tm.getTnav()!=null){
				m.setPid(tm.getTnav().getCid());
			}
			lm.add(m);
		}
		return lm;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<TreeNode> tree(Nav nav, Boolean b) {
		List<Object> param = new ArrayList<Object>();
		String hql = "from Tnav t where t.tnav is null order by t.cseq";
		if (nav != null && nav.getId() != null && !nav.getId().trim().equals("")) {
			hql = "from Tnav t where t.tnav.cid = ? order by t.cseq";
			param.add(nav.getId());
		}
		List<Tnav> l = navDao.find(hql, param);
		List<TreeNode> tree = new ArrayList<TreeNode>();
		for (Tnav t : l) {
			tree.add(this.tree(t, b));
		}
		return tree;
	}

	private TreeNode tree(Tnav t, boolean recursive) {
		TreeNode node = new TreeNode();
		node.setId(t.getCid());
		node.setText(t.getCname());
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("url", t.getCurl());
		node.setAttributes(attributes);
		if (t.getCiconcls() != null) {
			node.setIconCls(t.getCiconcls());
		} else {
			node.setIconCls("");
		}
		if (t.getTnavs() != null && t.getTnavs().size() > 0) {
			node.setState("closed");
			if (recursive) {// 递归查询子节点
				List<Tnav> l = new ArrayList<Tnav>(t.getTnavs());
				Collections.sort(l, new NavComparator());// 排序
				List<TreeNode> children = new ArrayList<TreeNode>();
				for (Tnav r : l) {
					TreeNode tn = tree(r, true);
					children.add(tn);
				}
				node.setChildren(children);
				node.setState("open");
			}
		}
		return node;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Nav> treegrid(Nav nav) {
		List<Tnav> l;
		if (nav != null && nav.getId() != null) {
			l = navDao.find("from Tnav t where t.tnav.cid = ? order by t.cseq", new Object[] { nav.getId() });
		} else {
			l = navDao.find("from Tnav t where t.tnav is null order by t.cseq");
		}
		return changeModel(l);
	}

	private List<Nav> changeModel(List<Tnav> Tnavs) {
		List<Nav> l = new ArrayList<Nav>();
		if (Tnavs != null && Tnavs.size() > 0) {
			for (Tnav t : Tnavs) {
				Nav m = new Nav();
				BeanUtils.copyProperties(t, m);
				if (t.getTnav() != null) {
					m.setPid(t.getTnav().getCid());
					m.setPname(t.getTnav().getCname());
				}
				if (countChildren(t.getCid()) > 0) {
					m.setState("closed");
				}
				if (t.getCiconcls() == null) {
					m.setIconCls("");
				} else {
					m.setIconCls(t.getCiconcls());
				}
				l.add(m);
			}
		}
		return l;
	}

	/**
	 * 统计有多少子节点
	 */
	private Long countChildren(String pid) {
		return navDao.count("select count(*) from Tnav t where t.tnav.cid = ?", new Object[] { pid });
	}

	public void delete(Nav nav) {
		del(nav.getCid());
	}

	private void del(String cid) {
		Tnav t = navDao.get(Tnav.class, cid);
		if (t != null) {
			Set<Tnav> navs = t.getTnavs();
			if (navs != null && !navs.isEmpty()) {
				// 说明当前菜单下面还有子菜单
				for (Tnav tnav : navs) {
					del(tnav.getCid());
				}
			}
			navDao.delete(t);
		}
	}
	private String write_datetime2(){
		
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		Calendar ca = Calendar.getInstance();
	    int year = ca.get(Calendar.YEAR);//获取年份
	    int month=ca.get(Calendar.MONTH);//获取月份
	    int day=ca.get(Calendar.DATE);//获取日
	    int hour=ca.get(Calendar.HOUR_OF_DAY);//小时
	    int minute=ca.get(Calendar.MINUTE);//分
	    int second=ca.get(Calendar.SECOND);//秒
	    String name = year+""+(month + 1 )+ "" + day + ""+ hour + "" + minute + "" + second;
	    return name;
	}

	public void add(Nav nav) {
		Tnav t = new Tnav();
		BeanUtils.copyProperties(nav, t);
		t.setCid(write_datetime2());
		if (nav.getPid() != null) {
			t.setTnav(navDao.get(Tnav.class, nav.getPid()));
		}
		if (nav.getIconCls() != null) {
			t.setCiconcls(nav.getIconCls());
		}
		navDao.save(t);
	}

	public void edit(Nav nav) {
		Tnav t = navDao.get(Tnav.class, nav.getCid());
		BeanUtils.copyProperties(nav, t);
		if (nav.getIconCls() != null) {
			t.setCiconcls(nav.getIconCls());
		}
		if (nav.getPid() != null && !nav.getPid().equals(nav.getCid())) {
			Tnav pt = navDao.get(Tnav.class, nav.getPid());
			if (pt != null) {
				if (isDown(t, pt)) {// 要将当前节点修改到当前节点的子节点中
					Set<Tnav> tnavs = t.getTnavs();// 当前要修改的节点的所有下级节点
					if (tnavs != null && tnavs.size() > 0) {
						for (Tnav tnav : tnavs) {
							if (tnav != null) {
								tnav.setTnav(null);
							}
						}
					}
				}
				t.setTnav(pt);
			}

		}
	}

	/**
	 * 判断是否是将当前节点修改到当前节点的子节点
	 * 
	 * @param t
	 * @param pt
	 * @return
	 */
	private boolean isDown(Tnav t, Tnav pt) {
		if (pt.getTnav() != null) {
			if (pt.getTnav().getCid().equals(t.getCid())) {
				return true;
			} else {
				return isDown(t, pt.getTnav());
			}
		}
		return false;
	}

	/**
	 * 查询父节点条数
	 * 
	 * @return
	 */
	public int count (){
		int a = navDao.count("select count(*) from Tnav where cpid is null").intValue();
		return a;
	}
	
	/*获取PID*/
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public String getPidById(String id) {
		Tnav t;
		try {
			t = navDao.get(Tnav.class, id);
			return t.getTnav().getCid();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		
		
		/*if(t.getTnav()==null){
			return "";
			
		}else{
			return t.getTnav().getCid();
		}*/
	}
}
