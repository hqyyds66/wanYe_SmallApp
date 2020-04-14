package com.mindor.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mindor.dao.Infrareddao;
import com.mindor.entity.InfraredBin;
import com.mindor.entity.Intermediate;

public class Infrareddaoimpl extends HibernateDaoSupport implements Infrareddao {

	@Override
	public List<InfraredBin> selectInfrared(String userId) {
		// TODO Auto-generated method stub
		String sql = "select i from InfraredBin i where i.userId='" + userId + "'";
		List<InfraredBin> InfraredBins = getHibernateTemplate().find(sql);
		return InfraredBins;
	}

	@Override
	public void addInfrared(InfraredBin infraredBin) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(infraredBin);
	}

	@Override
	public void delInfrared(int infraredBinId) {
		// TODO Auto-generated method stub
		InfraredBin infraredBin = getHibernateTemplate().get(InfraredBin.class, infraredBinId);
		getHibernateTemplate().delete(infraredBin);
	}

	@Override
	public void updateInfrared(String infraredBinId, String nick) {
		// TODO Auto-generated method stub
		String queryString = "update InfraredBin i set i.nick='" + nick + "' where i.infraredBinId='" + infraredBinId
				+ "'";
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
	public String equipmentMac(String equipmentId) {
		// TODO Auto-generated method stub
		String sql = "select i.equipmentMac from Intermediate i where i.equipmentId='" + equipmentId + "'";
		String stateStr = getHibernateTemplate().find(sql).toString();
		stateStr=stateStr.substring(1, stateStr.indexOf(']'));
		return stateStr;
	}

	@Override
	public String equipmentNote(String userId,String equipmentId) {
		// TODO Auto-generated method stub
		String sql = "select i.equipmentNote from Intermediate i where i.userId='" + userId + "' and i.equipmentId='"+equipmentId+"'";
		String stateStr = getHibernateTemplate().find(sql).toString();
		stateStr=stateStr.substring(1, stateStr.indexOf(']'));
		return stateStr;
	}

	@Override
	public List<Intermediate> selectInfrared_eq(String userId) {
		// TODO Auto-generated method stub
		String sql = "select i from Intermediate i where i.userId='" + userId + "' and (i.productId='zcz004' or i.productId='zcz005')";
		List<Intermediate> Intermediates = getHibernateTemplate().find(sql);
		return Intermediates;
	}

	@Override
	public String userIdGet(int infraredBinId) {
		// TODO Auto-generated method stub
		String sql = "select i.userId from InfraredBin i where i.infraredBinId=" + infraredBinId ;
		String userId=getHibernateTemplate().find(sql).toString();
		userId=userId.substring(1, userId.indexOf(']'));
		return userId;
	}

}
