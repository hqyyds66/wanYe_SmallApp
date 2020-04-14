package com.mindor.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mindor.dao.MessageManagedao;
import com.mindor.entity.MessageManage;

public class MessageManageimpl extends HibernateDaoSupport implements
		MessageManagedao {

	@Override
	public int addMessage(MessageManage messageManage) {
		// TODO Auto-generated method stub
		int messageManageId = (Integer) getHibernateTemplate().save(
				messageManage);
		return messageManageId;
	}

	@Override
	public void delMessage(String messageManageId) {
		// TODO Auto-generated method stub
		MessageManage messageManage = getHibernateTemplate().get(
				MessageManage.class, Integer.parseInt(messageManageId));
		getHibernateTemplate().delete(messageManage);
	}

	@Override
	public void updorderIf(String messageManageId, String readIf) {
		// TODO Auto-generated method stub
		MessageManage messageManage = getHibernateTemplate().get(
				MessageManage.class, Integer.parseInt(messageManageId));
		messageManage.setReadIf(readIf);
		getHibernateTemplate().update(messageManage);

	}

	@Override
	public List<MessageManage> selectMessage(String userId) {
		// TODO Auto-generated method stub
		String hql = "select m from MessageManage m where m.userId='" + userId
				+ "' order by m.messageTime desc ";
		List<MessageManage> messageManages = getHibernateTemplate().find(hql);
		return messageManages;
	}

	@Override
	public Boolean readIfs(String userId) {
		// TODO Auto-generated method stub
		String hql = "select m from MessageManage m where m.readIf='2' and m.userId='"
				+ userId + "'";
		List<MessageManage> messageManages = getHibernateTemplate().find(hql);
		if (messageManages.size() > 0) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public void updOperation(String messageManageId) {
		// TODO Auto-generated method stub
		MessageManage messageManage = getHibernateTemplate().get(
				MessageManage.class, Integer.parseInt(messageManageId));
		messageManage.setOperationIf("1");
		getHibernateTemplate().update(messageManage);

	}

}
