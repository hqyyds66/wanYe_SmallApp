package com.mindor.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mindor.dao.Userdao;
import com.mindor.entity.ClientId;
import com.mindor.entity.OpenUser;

public class Userimpl extends HibernateDaoSupport implements Userdao {

	@Override
	public OpenUser selectNamebyId(String userId) {
		// TODO Auto-generated method stub
		OpenUser openUser = getHibernateTemplate().get(OpenUser.class, userId);
		return openUser;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> openUsers(String hql) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find(hql);
	}

	@SuppressWarnings( { "unchecked", "rawtypes" })
	@Override
	public List<String> userIds(final String hql) {
		// TODO Auto-generated method stub
		List<String> userIdStrs = (List<String>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						// TODO Auto-generated method stub
						final List<String> ids;
						ids = session.createQuery(hql).setMaxResults(1).list();
						return ids;
					}
				});
		return userIdStrs;
	}

	@Override
	public Serializable saveOpuser(OpenUser openUser) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().save(openUser);
	}

	@Override
	public OpenUser selectOpUser(String userId) {
		// TODO Auto-generated method stub
		OpenUser openUser = new OpenUser();
		openUser = getHibernateTemplate().get(OpenUser.class, userId);
		return openUser;
	}

	@Override
	public void upaClientId(ClientId clientId) {
		// TODO Auto-generated method stub
		getHibernateTemplate().saveOrUpdate(clientId);
	}

	@Override
	public void upOpuser(OpenUser openUser) {
		// TODO Auto-generated method stub
		System.out.println(openUser.getNickName());
		getHibernateTemplate().update(openUser);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> selectStr(String hql) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<String> allIds() {
		// TODO Auto-generated method stub
		String hql="select o.userId from  OpenUser o ";
		List<String> ids=getHibernateTemplate().find(hql);
		return ids;
	}

}
