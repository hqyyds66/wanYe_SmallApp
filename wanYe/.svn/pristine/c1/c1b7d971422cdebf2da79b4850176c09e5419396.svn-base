package com.mindor.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mindor.dao.TimeDelaydao;
import com.mindor.entity.TimeDelay;
import com.mindor.entity.Timing;

public class TimeDelayimpl extends HibernateDaoSupport implements TimeDelaydao {

	@Override
	public Integer addTimeDelay(TimeDelay timeDelay) {
		// TODO Auto-generated method stub
		Integer timeDelayId = (Integer) getHibernateTemplate().save(timeDelay);
		return timeDelayId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteTimeDelay(int timeDelayId) {
		// TODO Auto-generated method stub
		TimeDelay timeDelay = new TimeDelay();
		Timing timing = new Timing();
		timeDelay = getHibernateTemplate().get(TimeDelay.class, timeDelayId);
		String hql = "select e from Timing e where e.timeDelayId='"
				+ timeDelayId + "'";
		List<Object> timings = getHibernateTemplate().find(hql);
		timing = (Timing) timings.get(0);
		getHibernateTemplate().delete(timing);// 删除延时对应的定时
		getHibernateTemplate().delete(timeDelay);
	}

	@SuppressWarnings( { "unchecked", "rawtypes" })
	@Override
	public void deleteTimeDelays(final String equipmentId, final String userId) {
		// TODO Auto-generated method stub
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				final String hql = "delete from TimeDelay t where t.userId='"
						+ userId + "' and t.equipmentId='" + equipmentId + "'";
				session.createQuery(hql).executeUpdate();
				return null;
			}
		});
	}

	@SuppressWarnings( { "unchecked", "rawtypes" })
	@Override
	public void deleteTimeDelays(final String equipmentId) {
		// TODO Auto-generated method stub
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				final String hql = "delete from TimeDelay t where t.equipmentId='"
						+ equipmentId + "'";
				session.createQuery(hql).executeUpdate();
				return null;
			}
		});
	}

	@Override
	public TimeDelay selectTimeDelay(int timeDelayId) {
		// TODO Auto-generated method stub
		TimeDelay timeDelay = new TimeDelay();
		timeDelay=getHibernateTemplate().get(TimeDelay.class, timeDelayId);
		return timeDelay;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TimeDelay selectTimeDelays(String equipmentId, String userId) {
		// TODO Auto-generated method stub
		List<TimeDelay> timeDelays;
		TimeDelay timeDelay = new TimeDelay();
		String hql = "select t from TimeDelay t where t.equipmentId='"
				+ equipmentId + "'";
		timeDelays = getHibernateTemplate().find(hql);
		if (timeDelays.size() > 0) {
			timeDelay = timeDelays.get(0);
		} else {
			timeDelay = null;
		}
		return timeDelay;
	}

	@Override
	public void updateTimeDelay(TimeDelay timeDelay) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(timeDelay);
	}

}
