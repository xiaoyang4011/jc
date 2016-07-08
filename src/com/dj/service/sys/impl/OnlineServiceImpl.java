package com.dj.service.sys.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dj.dao.base.BaseDaoI;
import com.dj.model.sys.Tonline;
import com.dj.pageModel.base.DataGrid;
import com.dj.pageModel.sys.Online;
import com.dj.service.base.impl.BaseServiceImpl;
import com.dj.service.sys.OnlineServiceI;

@Service("onlineService")
public class OnlineServiceImpl extends BaseServiceImpl implements OnlineServiceI {

	private BaseDaoI<Tonline> onlineDao;

	public BaseDaoI<Tonline> getOnlineDao() {
		return onlineDao;
	}

	@Autowired
	public void setOnlineDao(BaseDaoI<Tonline> onlineDao) {
		this.onlineDao = onlineDao;
	}

	public void saveOrUpdateTonlineByLoginNameAndIp(String loginName, String ip) {
		Tonline t = onlineDao.get("from Tonline t where t.cname = ? and t.cip = ?", new Object[] { loginName, ip });
		if (t != null) {
			t.setCdatetime(new Date());
		} else {
			Tonline o = new Tonline();
			o.setCid(UUID.randomUUID().toString());
			o.setCdatetime(new Date());
			o.setCname(loginName);
			o.setCip(ip);
			onlineDao.save(o);
		}
	}

	public void deleteTonlineByLoginNameAndIp(String loginName, String ip) {
		Tonline t = onlineDao.get("from Tonline t where t.cname = ? and t.cip = ?", new Object[] { loginName, ip });
		if (t != null) {
			onlineDao.delete(t);
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(Online online) {
		DataGrid j = new DataGrid();
		j.setRows(find(online));
		j.setTotal(total(online));
		return j;
	}

	private List<Tonline> find(Online online) {
		String hql = "from Tonline t where 1=1 ";

		List<Object> parms = new ArrayList<Object>();
		hql = addWhere(online, hql, parms);

		if (online.getSort() != null && online.getOrder() != null) {
			hql += " order by " + online.getSort() + " " + online.getOrder();
		}
		return onlineDao.find(hql, parms, online.getPage(), online.getRows());
	}

	private Long total(Online online) {
		String hql = "select count(*) from Tonline t where 1=1 ";
		List<Object> parms = new ArrayList<Object>();
		hql = addWhere(online, hql, parms);
		return onlineDao.count(hql, parms);
	}

	private String addWhere(Online online, String hql, List<Object> parms) {
		return hql;
	}

}
