package com.mindor.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mindor.dao.PowerCountdao;

public class PowerCountimpl extends HibernateDaoSupport implements
		PowerCountdao {

	@SuppressWarnings( { "unchecked", "rawtypes" })
	@Override
	public String powerList(final String equipmentId, final String time,
			final String timeStr) {
		// TODO Auto-generated method stub

		// powerListStr=getHibernateTemplate().find(hql).toString();

		String powerListStr = (String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						// TODO Auto-generated method stub

						String hql = null;
						System.out.println(time);
						if (timeStr.equals("day")) {
							hql = "select a.power from PowerCount a where a.equipmentId='"
									+ equipmentId
									+ "' and a.time='"
									+ time
									+ "'";
						}

						if (timeStr.equals("week") || timeStr.equals("month")) {// SUM(CAST(a.power
																				// AS
																				// DECIMAL(18,4)))
							hql = "select SUM(CAST(a.power AS DECIMAL(18,4))) AS power_0 from PowerCount a where a.equipmentId='"
									+ equipmentId
									+ "' and a.time like'%"
									+ time + "%'";
						}

						final List<Object> productListStr;
						Query query;
						query = session.createSQLQuery(hql);
						productListStr = query.list();
						String powerStr = null;
						if (productListStr.size() > 0) {
							if (productListStr.get(0) != null) {
								powerStr = productListStr.get(0).toString();
							}
						}
						return powerStr;
					}
				});

		return powerListStr;
	}

	@Override
	public void delPower(String equipmentId) {
		// TODO Auto-generated method stub
		String hql="delete from PowerCount p where p.equipmentId='"+equipmentId+"'";
		getHibernateTemplate().find(hql);
		
	}

}
