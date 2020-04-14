package com.mindor.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mindor.dao.ChargeGuarddao;
import com.mindor.entity.ChargeGuard;

public class ChargeGuardimpl extends HibernateDaoSupport implements
		ChargeGuarddao {

	@SuppressWarnings("unchecked")
	@Override
	public ChargeGuard selectState(String userId, String equipmentId) {
		// TODO Auto-generated method stub
		String hql = "select c from ChargeGuard c where c.userId='" + userId
				+ "' and c.equipmentId='" + equipmentId + "'";
		List<ChargeGuard> States = getHibernateTemplate().find(hql);
		ChargeGuard chargeGuard;
		if (States.size() > 0) {
			chargeGuard = States.get(0);
		} else {
			chargeGuard = null;
		}
		return chargeGuard;
	}

	@SuppressWarnings( { "unchecked", "rawtypes" })
	@Override
	public void updateState(final String userId, final String equipmentId,
			final int phstate, final int hmstate, final int voicestate) {
		// TODO Auto-generated method stub
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				final String hql = "update ChargeGuard c set c.phstate="
						+ phstate + ",c.hmstate=" + hmstate + ",c.voicestate="
						+ voicestate + " where c.userId='" + userId
						+ "' and c.equipmentId='" + equipmentId + "'";
				session.createQuery(hql).executeUpdate();
				return null;
			}
		});
	}

	@Override
	public void addChargeGuard(ChargeGuard chargeGuard) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(chargeGuard);
	}

	@SuppressWarnings( { "unchecked", "rawtypes" })
	@Override
	public void delChargeGuard(final String equipmentId) {
		// TODO Auto-generated method stub
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				final String hql = "delete ChargeGuard  c where c.equipmentId='"
						+ equipmentId + "'";
				session.createQuery(hql).executeUpdate();
				return null;
			}
		});
	}

}
