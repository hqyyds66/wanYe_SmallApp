package com.mindor.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mindor.dao.RFdao;
import com.mindor.entity.InfraredBin;
import com.mindor.entity.Intermediate;
import com.mindor.entity.RF;

public class RFimpl extends HibernateDaoSupport implements RFdao{

	@Override
	public List<Intermediate> selectRfeq(String userId) {
		// TODO Auto-generated method stub
		String sql = "select i from Intermediate i where i.userId='" + userId + "' and (i.productId='zcz004' or i.productId='zcz005')";
		List<Intermediate> Intermediates = getHibernateTemplate().find(sql);
		return Intermediates;
	}

	@Override
	public List<RF> selectRf(String userId) {
		// TODO Auto-generated method stub
		String sql = "select i from RF i where i.userId='" + userId + "'";
		List<RF> RFs = getHibernateTemplate().find(sql);
		return RFs;
	}

	@Override
	public void addRf(RF rf) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(rf);
	}

	@Override
	public void delRf(int rfId) {
		// TODO Auto-generated method stub
		RF rf = getHibernateTemplate().get(RF.class, rfId);
		getHibernateTemplate().delete(rf);
	}

	@Override
	public void updRf(int rfId, String nickname, String btnName) {
		// TODO Auto-generated method stub
		System.out.println("nickname:"+nickname);
		String queryString = "update RF i set i.nickName='" + nickname + "', i.btnName='" + btnName
				+ "' where i.rfId='"+rfId+"'";
		getHibernateTemplate().bulkUpdate(queryString);
	}

	@Override
	public String state(String equipmentId) {
		// TODO Auto-generated method stub
		String sql = "select i.equipmentState from Equipment i where i.equipmentId='" + equipmentId + "'";
		String stateStr = getHibernateTemplate().find(sql).toString();
		stateStr=stateStr.substring(1, stateStr.indexOf(']'));
		return stateStr;
	}

	@Override
	public String equipmentNote(String userId, String equipmentId) {
		// TODO Auto-generated method stub
		String sql = "select i.equipmentNote from Intermediate i where i.userId='" + userId + "' and i.equipmentId='"+equipmentId+"'";
		String stateStr = getHibernateTemplate().find(sql).toString();
		stateStr=stateStr.substring(1, stateStr.indexOf(']'));
		return stateStr;
	}

}
