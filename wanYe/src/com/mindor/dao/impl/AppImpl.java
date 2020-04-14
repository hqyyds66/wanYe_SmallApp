package com.mindor.dao.impl;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mindor.dao.Appdao;
import com.mindor.entity.AppVersion;

public class AppImpl extends HibernateDaoSupport implements Appdao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public AppVersion selectVersionsById(int AppVersionId) {
		// TODO Auto-generated method stub
		AppVersion versions = null;
		try {
			Configuration cfg = new Configuration();
			sessionFactory = cfg.configure().buildSessionFactory();

		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			versions = new AppVersion();
			versions = (AppVersion) session.get(AppVersion.class, AppVersionId);
			System.gc();// 执行垃圾回收
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.getSessionFactory().close();
			session.close();
			System.gc();// 执行垃圾回收
		}

		return versions;
	}

	@SuppressWarnings( { "unchecked", "rawtypes" })
	@Override
	public void updateWjMinstore(final String wjMinstore) {
		// TODO Auto-generated method stub
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				String hql = "update AppVersion  e set e.wjMinstore='"
						+ wjMinstore + "' where e.appVersionId='2'";
				Query query = session.createQuery(hql);
				query.executeUpdate();
				return null;
			}
		});
	}

}
