package com.mindor.serivce.serviceimpl;

import java.io.Serializable;
import java.util.List;

import com.mindor.dao.Userdao;
import com.mindor.entity.ClientId;
import com.mindor.entity.OpenUser;
import com.mindor.serivce.UserService;

public class UserServiceimpl implements UserService {

	private Userdao userdao;

	@Override
	public OpenUser selectNamebyId(String userId) {
		// TODO Auto-generated method stub
		return userdao.selectNamebyId(userId);
	}

	public Userdao getUserdao() {
		return userdao;
	}

	public void setUserdao(Userdao userdao) {
		this.userdao = userdao;
	}

	@Override
	public List<Object> openUsers(String hql) {
		// TODO Auto-generated method stub
		return userdao.openUsers(hql);
	}

	@Override
	public List<String> userIds(String hql) {
		// TODO Auto-generated method stub
		return userdao.userIds(hql);
	}

	@Override
	public Serializable saveOpuser(OpenUser openUser) {
		// TODO Auto-generated method stub
		return userdao.saveOpuser(openUser);
	}

	@Override
	public OpenUser selectOpUser(String userId) {
		// TODO Auto-generated method stub
		return userdao.selectOpUser(userId);
	}

	@Override
	public void upaClientId(ClientId clientId) {
		// TODO Auto-generated method stub
		userdao.upaClientId(clientId);
	}

	@Override
	public void upOpuser(OpenUser openUser) {
		// TODO Auto-generated method stub
		userdao.upOpuser(openUser);
	}

	@Override
	public List<String> selectStr(String hql) {
		// TODO Auto-generated method stub
		return userdao.selectStr(hql);
	}

	@Override
	public List<String> allIds() {
		// TODO Auto-generated method stub
		return userdao.allIds();
	}

}
