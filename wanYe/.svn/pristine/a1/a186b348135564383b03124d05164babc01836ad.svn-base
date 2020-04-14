package com.mindor.serivce.serviceimpl;

import java.util.List;

import com.mindor.dao.MessageManagedao;
import com.mindor.entity.MessageManage;
import com.mindor.serivce.MessageManageService;

public class MessageManageServiceimpl implements MessageManageService {

	private MessageManagedao messageManagedao;

	public MessageManagedao getMessageManagedao() {
		return messageManagedao;
	}

	public void setMessageManagedao(MessageManagedao messageManagedao) {
		this.messageManagedao = messageManagedao;
	}

	@Override
	public int addMessage(MessageManage messageManage) {
		// TODO Auto-generated method stub
		return messageManagedao.addMessage(messageManage);

	}

	@Override
	public void delMessage(String messageManageId) {
		// TODO Auto-generated method stub
		messageManagedao.delMessage(messageManageId);
	}

	@Override
	public void updorderIf(String messageManageId, String readIf) {
		// TODO Auto-generated method stub
		messageManagedao.updorderIf(messageManageId, readIf);
	}

	@Override
	public List<MessageManage> selectMessage(String userId) {
		// TODO Auto-generated method stub
		List<MessageManage> messageManages = messageManagedao
				.selectMessage(userId);
		return messageManages;
	}

	@Override
	public Boolean readIfs(String userId) {
		// TODO Auto-generated method stub
		return messageManagedao.readIfs(userId);
	}

	@Override
	public void updOperation(String messageManageId) {
		// TODO Auto-generated method stub
		messageManagedao.updOperation(messageManageId);
	}

}
