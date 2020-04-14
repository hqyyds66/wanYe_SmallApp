package com.mindor.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mindor.dao.Timingdao;
import com.mindor.entity.Timing;

public class Timingimpl extends HibernateDaoSupport implements Timingdao {

	@Override
	public void addTiming(Timing timing) {
		// TODO Auto-generated method stub
		Serializable id = getHibernateTemplate().save(timing);
		System.out.print("id============" + id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateTiming(Timing timing) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(timing);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Timing> selectTimings(String equipmentId, String userId) {
		// TODO Auto-generated method stub
		List<Timing> timing;
		String hql = "select t from Timing t where t.equipmentId='"
				+ equipmentId + "' and t.timeDelayId=0";
		timing = getHibernateTemplate().find(hql);
		return timing;
	}

	@Override
	public void deleteTiming(int timingId) {
		// TODO Auto-generated method stub
		Timing timing = new Timing();
		timing = getHibernateTemplate().get(Timing.class, timingId);
		getHibernateTemplate().delete(timing);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteTimings(final String equipmentId, final String userId) {
		// TODO Auto-generated method stub
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				final String hql = "delete from Timing t where t.userId='"
						+ userId + "' and t.equipmentId='" + equipmentId + "'";
				session.createQuery(hql).executeUpdate();
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteTimings(final String equipmentId) {
		// TODO Auto-generated method stub
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				final String hql = "delete from Timing t where t.equipmentId='"
						+ equipmentId + "'";
				session.createQuery(hql).executeUpdate();
				return null;
			}
		});
	}

	@Override
	public Timing selectTiming(int timingId) {
		// TODO Auto-generated method stub
		Timing timing = new Timing();
		timing = getHibernateTemplate().get(Timing.class, timingId);
		return timing;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Timing selectTimingByDelayId(int timeDelayId) {
		// TODO Auto-generated method stub
		List<Timing> timings;
		Timing timing = new Timing();
		String hql = "select t from Timing t where  t.timeDelayId='"
				+ timeDelayId + "'";
		timings = getHibernateTemplate().find(hql);
		timing = timings.get(0);
		return timing;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Timing selectHomePage(String equipmentId, String userId) {
		// TODO Auto-generated method stub
		List<Timing> timingsb;
		List<Timing> timingse;

		Timing timingb = new Timing();
		Timing timinge = new Timing();
		Timing timing = new Timing();
		String hqlb = "SELECT t FROM Timing t WHERE "
				+ "STR_TO_DATE(t.beginTime, '%H:%i:%s') >CURTIME()  "
				+ "AND t.equipmentId='" + equipmentId + "' "
				+ "AND t.state='1' " + "AND t.openIf='1' "
				+ "AND t.repeatTime!='9' " + "AND t.repeatTime!='over' "
				+ "AND t.timeDelayId='0' " + "ORDER BY t.beginTime";

		String hqle = "SELECT t FROM Timing t WHERE "
				+ "STR_TO_DATE(t.endTime, '%H:%i:%s') >CURTIME()  "
				+ "AND t.equipmentId='" + equipmentId + "' "
				+ "AND t.state='1' " + "AND t.colseIf='1' "
				+ "AND t.repeatTime!='9' " + "AND t.repeatTime!='over' "
				+ "AND t.timeDelayId='0' " + "ORDER BY t.endTime ";

		timingsb = getHibernateTemplate().find(hqlb);
		timingse = getHibernateTemplate().find(hqle);

		if (timingsb.size() > 0 && timingse.size() > 0) {
			SimpleDateFormat nowFormat = new SimpleDateFormat("HH:mm");
			timingb = timingsb.get(0);
			timinge = timingse.get(0);

			Date timingbData;
			Date timingeData;
			try {
				timingbData = nowFormat.parse(timingb.getBeginTime());
				timingeData = nowFormat.parse(timinge.getEndTime());
				if (timingbData.compareTo(timingeData) > 0) {
					timing = timinge;
				} else {
					timing = timingb;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (timingsb.size() > 0) {
			timingb = timingsb.get(0);
			timing = timingb;
		} else if (timingse.size() > 0) {
			timinge = timingse.get(0);
			timing = timinge;
		} else {

			hqlb = "SELECT t FROM Timing t WHERE " + "t.equipmentId='"
					+ equipmentId + "' " + "AND t.state='1' "
					+ "AND t.openIf='1' " + "AND t.repeatTime!='9' "
					+ "AND t.repeatTime!='over' " + "AND t.timeDelayId='0'"
					+ "ORDER BY t.beginTime ";

			hqle = "SELECT t FROM Timing t WHERE " + "t.equipmentId='"
					+ equipmentId + "' " + "AND t.state='1' "
					+ "AND t.colseIf='1' " + "AND t.repeatTime!='9' "
					+ "AND t.repeatTime!='over' " + "AND t.timeDelayId='0'"
					+ "ORDER BY t.endTime ";

			timingsb = getHibernateTemplate().find(hqlb);
			timingse = getHibernateTemplate().find(hqle);
			if (timingsb.size() > 0 && timingse.size() > 0) {
				SimpleDateFormat nowFormat = new SimpleDateFormat("HH:mm");
				timingb = timingsb.get(0);
				timinge = timingse.get(0);

				Date timingbData;
				Date timingeData;
				try {
					timingbData = nowFormat.parse(timingb.getBeginTime());
					timingeData = nowFormat.parse(timinge.getEndTime());
					if (timingbData.compareTo(timingeData) > 0) {
						timing = timinge;
					} else {
						timing = timingb;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (timingsb.size() > 0) {
				timingb = timingsb.get(0);
				timing = timingb;
			} else if (timingse.size() > 0) {
				timinge = timingse.get(0);
				timing = timinge;
			} else {
				timing = null;
			}

		}
		return timing;
	}

}
