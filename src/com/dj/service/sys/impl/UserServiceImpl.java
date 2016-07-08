package com.dj.service.sys.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dj.dao.base.BaseDaoI;
import com.dj.exception.ValidateFieldsException;
import com.dj.model.sys.Tauth;
import com.dj.model.sys.Tdept;
import com.dj.model.sys.Trole;
import com.dj.model.sys.Troletauth;
import com.dj.model.sys.Tuser;
import com.dj.model.sys.Tusertrole;
import com.dj.pageModel.base.DataGrid;
import com.dj.pageModel.sys.User;
import com.dj.service.base.impl.BaseServiceImpl;
import com.dj.service.sys.UserServiceI;
import com.dj.util.base.Encrypt;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserServiceI {

	private BaseDaoI<Tuser> userDao;
	private BaseDaoI<Trole> roleDao;
	private BaseDaoI<Tdept> deptDao;
	private BaseDaoI<Tusertrole> userroleDao;

	public BaseDaoI<Trole> getRoleDao() {
		return roleDao;
	}

	@Autowired
	public void setRoleDao(BaseDaoI<Trole> roleDao) {
		this.roleDao = roleDao;
	}

	public BaseDaoI<Tusertrole> getUserroleDao() {
		return userroleDao;
	}

	@Autowired
	public void setUserroleDao(BaseDaoI<Tusertrole> userroleDao) {
		this.userroleDao = userroleDao;
	}

	public BaseDaoI<Tuser> getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(BaseDaoI<Tuser> userDao) {
		this.userDao = userDao;
	}

	public BaseDaoI<Tdept> getDeptDao() {
		return deptDao;
	}
	
	@Autowired
	public void setDeptDao(BaseDaoI<Tdept> deptDao) {
		this.deptDao = deptDao;
	}
	 //只读型事务
	 // 如果其他bean调用这个方法,在其他bean中声明事务,那就用事务.如果其他bean没有声明事务,那就不用事务.
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public User login(User user) {
		Tuser t = userDao.get("from Tuser t where t.cname = ? and t.cpwd = ?", new Object[] { user.getCname(), Encrypt.e(user.getCpwd()) });
		if (t != null) {
			user.setCid(t.getCid());
			user.setCname(t.getCname());
			user.setCemail(t.getCemail());
			user.setCrealname(t.getCrealname());
			user.setCcreatedatetime(t.getCcreatedatetime());
			user.setCmodifydatetime(t.getCmodifydatetime());
			user.setCstatus(t.getCstatus());

			Map<String, String> authIdsMap = new HashMap<String, String>();
			String authIds = "";
			String authNames = "";
			Map<String, String> authUrlsMap = new HashMap<String, String>();
			String authUrls = "";
			String roleIds = "";
			String roleNames = "";
			boolean b1 = false;
			Set<Tusertrole> tusertroles = t.getTusertroles();
			if (tusertroles != null && tusertroles.size() > 0) {
				for (Tusertrole tusertrole : tusertroles) {
					Trole trole = tusertrole.getTrole();
					if (b1) {
						roleIds += ",";
						roleNames += ",";
					}
					roleIds += trole.getCid();
					roleNames += trole.getCname();
					b1 = true;

					Set<Troletauth> troletauths = trole.getTroletauths();
					if (troletauths != null && troletauths.size() > 0) {
						for (Troletauth troletauth : troletauths) {
							Tauth tauth = troletauth.getTauth();
							authIdsMap.put(tauth.getCid(), tauth.getCname());
							authUrlsMap.put(tauth.getCid(), tauth.getCurl());
						}
					}
				}
			}
			boolean b2 = false;
			for (String id : authIdsMap.keySet()) {
				if (b2) {
					authIds += ",";
					authNames += ",";
				}
				authIds += id;
				authNames += authIdsMap.get(id);
				b2 = true;
			}
			user.setAuthIds(authIds);
			user.setAuthNames(authNames);
			user.setRoleIds(roleIds);
			user.setRoleNames(roleNames);
			boolean b3 = false;
			for (String id : authUrlsMap.keySet()) {
				if (b3) {
					authUrls += ",";
				}
				authUrls += authUrlsMap.get(id);
				b3 = true;
			}
			user.setAuthUrls(authUrls);

			return user;
		}
		return null;
	}

	public void save(User user) throws ValidateFieldsException {
		Tuser u = new Tuser();
		if (isUniqueUser(user.getCname(), null)) {
			throw new ValidateFieldsException("用户名已存在,请重新输入. ");
		}
		u.setCname(user.getCname());
		u.setCemail(user.getCemail());
		u.setCrealname(user.getCrealname());
		u.setCstatus(user.getCstatus());

		u.setCid(UUID.randomUUID().toString());
		if (user.getCcreatedatetime() == null) {
			u.setCcreatedatetime(new Date());
		}
		if (user.getCmodifydatetime() == null) {
			u.setCmodifydatetime(new Date());
		}
		u.setCpwd(Encrypt.e(user.getCpwd()));
		
		u.setTdept(deptDao.get(Tdept.class, user.getDeptId()));
		userDao.save(u);

		this.saveUserRole(user, u);
	}

	/**
	 * 保存用户和角色的关系
	 * 
	 * @param user
	 * @param u
	 */
	private void saveUserRole(User user, Tuser u) {
		userroleDao.executeHql("delete Tusertrole t where t.tuser = ?", new Object[] { u });
		if (user.getRoleIds() != null) {
			for (String id : user.getRoleIds().split(",")) {
				Tusertrole tusertrole = new Tusertrole();
				tusertrole.setCid(UUID.randomUUID().toString());
				tusertrole.setTrole(roleDao.get(Trole.class, id.trim()));
				tusertrole.setTuser(u);
				userroleDao.save(tusertrole);
			}
		}
	}

	public void update(User user) throws ValidateFieldsException {
		if (isUniqueUser(user.getCname(), user.getCid())) {
			throw new ValidateFieldsException("用户名已存在,请重新输入. ");
		}
		Tuser u = userDao.get(Tuser.class, user.getCid());
		
		if(user.getCname()!=null&&!"".equals(user.getCname())){
			u.setCname(user.getCname());
		}
		if(user.getCemail()!=null&&!"".equals(user.getCemail())){
			u.setCemail(user.getCemail());
		}
		if(user.getCrealname()!=null&&!"".equals(user.getCrealname())){
			u.setCrealname(user.getCrealname());
		}
		if(user.getDeptId()!=null&&!"".equals(user.getDeptId())){
			u.setTdept(deptDao.get(Tdept.class, user.getDeptId()));
		}
		if(user.getCstatus()!=null&&!"".equals(user.getCstatus())){
			u.setCstatus(user.getCstatus());
		}
		if (user.getCmodifydatetime() == null) {
			u.setCmodifydatetime(new Date());
		}
		if (user.getCpwd() != null && !user.getCpwd().trim().equals("")) {
			u.setCpwd(Encrypt.e(user.getCpwd()));
		}
		this.saveUserRole(user, u);
	}

	/**
	 * 判断用户名是否唯一
	 * 
	 * @param user
	 * @param u
	 */
	public boolean isUniqueUser(String userName, String id) {
		Tuser tu = null;
		if (id == null) {
			tu = this.userDao.get("from Tuser t where t.cname = ?", new String[] { userName });
			if (tu == null){
				return false;
			}
		}else{
			tu = this.userDao.get("from Tuser t where t.cname = ?", new String[] { userName });
			if (tu == null){
				return false;
			}
			if(tu!=null&&tu.getCid().equals(id)){
				return false;
			}
		}
		return true;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(User user) {
		DataGrid j = new DataGrid();
		j.setRows(this.changeModel(this.find(user)));
		j.setTotal(this.total(user));
		return j;
	}

	private List<User> changeModel(List<Tuser> tusers) {
		List<User> users = new ArrayList<User>();
		if (tusers != null && tusers.size() > 0) {
			for (Tuser tu : tusers) {
				User u = new User();
				u.setCsn(tu.getCsn());
				u.setCid(tu.getCid());
				u.setCname(tu.getCname());
				u.setCemail(tu.getCemail());
				u.setCrealname(tu.getCrealname());
				u.setCcreatedatetime(tu.getCcreatedatetime());
				u.setCmodifydatetime(tu.getCmodifydatetime());
				u.setCstatus(tu.getCstatus());
				
				u.setDeptId(tu.getTdept().getCid());
				u.setDeptName(tu.getTdept().getCname());

				Set<Tusertrole> tusertroles = tu.getTusertroles();
				String roleIds = "";
				String roleNames = "";
				boolean b = false;
				if (tusertroles != null && tusertroles.size() > 0) {
					for (Tusertrole tusertrole : tusertroles) {
						if (tusertrole.getTrole() != null) {
							if (b) {
								roleIds += ",";
								roleNames += ",";
							}
							roleIds += tusertrole.getTrole().getCid();
							roleNames += tusertrole.getTrole().getCname();
							b = true;
						}
					}
				}
				u.setRoleIds(roleIds);
				u.setRoleNames(roleNames);

				users.add(u);
			}
		}
		return users;
	}

	private List<Tuser> find(User user) {
		String hql = "from Tuser t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(user, hql, values);

		if (user.getSort() != null && user.getOrder() != null) {
			hql += " order by " + user.getSort() + " " + user.getOrder();
		}
		return userDao.find(hql, values, user.getPage(), user.getRows());
	}

	private Long total(User user) {
		String hql = "select count(*) from Tuser t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(user, hql, values);
		return userDao.count(hql, values);
	}

	private String addWhere(User user, String hql, List<Object> values) {
		if (user.getCname() != null && !user.getCname().trim().equals("")) {
			hql += " and t.cname like ? ";
			values.add("%%" + user.getCname().trim() + "%%");
		}
		if (user.getCcreatedatetimeStart() != null) {
			hql += " and t.ccreatedatetime>=? ";
			values.add(user.getCcreatedatetimeStart());
		}
		if (user.getCcreatedatetimeEnd() != null) {
			hql += " and t.ccreatedatetime<=? ";
			values.add(user.getCcreatedatetimeEnd());
		}
		if (user.getCmodifydatetimeStart() != null) {
			hql += " and t.cmodifydatetime>=? ";
			values.add(user.getCmodifydatetimeStart());
		}
		if (user.getCmodifydatetimeEnd() != null) {
			hql += " and t.cmodifydatetime<=? ";
			values.add(user.getCmodifydatetimeEnd());
		}
		return hql;
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				if (!id.trim().equals("0")) {
					Tuser u = userDao.get(Tuser.class, id.trim());
					if (u != null) {
						userroleDao.executeHql("delete Tusertrole t where t.tuser = ?", new Object[] { u });
						userDao.delete(u);
					}
				}
			}
		}
	}

	public void roleEdit(User user) {
		if (user.getIds() != null) {
			for (String id : user.getIds().split(",")) {
				Tuser u = userDao.get(Tuser.class, id);
				this.saveUserRole(user, u);
			}
		}
	}

	public void editUserInfo(User user) {
		if (user.getCpwd() != null && !user.getCpwd().trim().equals("")) {
			Tuser t = userDao.get(Tuser.class, user.getCid());
			t.setCpwd(Encrypt.e(user.getCpwd()));
		}
	}
}
