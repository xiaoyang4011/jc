package com.dj.service.sys.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dj.dao.base.BaseDaoI;
import com.dj.model.sys.Tdic;
import com.dj.pageModel.base.DataGrid;
import com.dj.pageModel.sys.Dic;
import com.dj.service.base.impl.BaseServiceImpl;
import com.dj.service.sys.DicServiceI;

@Service("dicService")
public class DicServiceImpl extends BaseServiceImpl implements DicServiceI {

	private BaseDaoI<Tdic> dicDao;

	public BaseDaoI<Tdic> getDicDao() {
		return dicDao;
	}

	@Autowired
	public void setDicDao(BaseDaoI<Tdic> dicDao) {
		this.dicDao = dicDao;
	}

	public void add(Dic dic) {
		Tdic t = new Tdic();
		t.setCid(UUID.randomUUID().toString());
		t.setCkey(dic.getCkey());
		t.setCtype(dic.getCtype());
		t.setCvalue(dic.getCvalue());
		t.setCstatus(dic.getCstatus());
		dicDao.save(t);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(Dic dic) {
		DataGrid j = new DataGrid();
		j.setRows(getDicsFromTdics(find(dic)));
		j.setTotal(total(dic));
		return j;
	}

	private List<Dic> getDicsFromTdics(List<Tdic> Tdics) {
		List<Dic> ads = new ArrayList<Dic>();
		if (Tdics != null && Tdics.size() > 0) {
			for (Tdic t : Tdics) {
				Dic p = new Dic();
				p.setCid(t.getCid());
				p.setCkey(t.getCkey());
				p.setCvalue(t.getCvalue());
				p.setCtype(t.getCtype());
				p.setCstatus(t.getCstatus());
				ads.add(p);
			}
		}
		return ads;
	}

	private List<Tdic> find(Dic dic) {
		String hql = "from Tdic t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(dic, hql, values);

		if (dic.getSort() != null && dic.getOrder() != null) {
			hql += " order by " + dic.getSort() + " " + dic.getOrder();
		}
		return dicDao.find(hql, values, dic.getPage(), dic.getRows());
	}

	private Long total(Dic dic) {
		String hql = "select count(*) from Tdic t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(dic, hql, values);
		return dicDao.count(hql, values);
	}

	private String addWhere(Dic dic, String hql, List<Object> values) {
		if (dic.getCkey() != null && !"".equals(dic.getCkey())) {
			hql += " and t.ckey =? ";
			values.add(dic.getCkey());
		}
		if (dic.getCvalue() != null && !"".equals(dic.getCvalue())) {
			hql += " and t.cvalue =? ";
			values.add(dic.getCvalue());
		}
		if (dic.getCtype() != null && !"".equals(dic.getCtype())) {
			hql += " and t.ctype =? ";
			values.add(dic.getCtype());
		}
		return hql;
	}

	public void change(Dic dic) {
		if (dic.getIds() != null) {
			for (String id : dic.getIds().split(",")) {
				Tdic t = dicDao.get(Tdic.class, id.trim());
				if (t != null) {
					t.setCstatus(dic.getCstatus());
					dicDao.save(t);
				}
			}
		}
		
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				Tdic t = dicDao.get(Tdic.class, id.trim());
				if (t != null) {
					dicDao.delete(t);
				}
			}
		}
	}

	public void update(Dic dic) {
		Tdic t = dicDao.get(Tdic.class, dic.getCid());
		if (dic.getCkey() != null && dic.getCkey() != "") {
			t.setCkey(dic.getCkey());
		}
		if (dic.getCvalue() != null && dic.getCvalue() != "") {
			t.setCvalue(dic.getCvalue());
		}
		if (dic.getCtype() != null && dic.getCtype() != "") {
			t.setCtype(dic.getCtype());
		}
		if (dic.getCstatus() != null && dic.getCstatus() != "") {
			t.setCstatus(dic.getCstatus());
		}
		dicDao.save(t);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Dic> combobox(Dic dic) {
		return getDicsFromTdics(findAllTdic(dic));
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	private List<Tdic> findAllTdic(Dic dic) {
		String hql = "from Tdic t where t.ctype = '" + dic.getCtype() + "'";
		return dicDao.find(hql);
	}

}
