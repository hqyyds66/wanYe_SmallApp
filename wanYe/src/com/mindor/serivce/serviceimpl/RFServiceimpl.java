package com.mindor.serivce.serviceimpl;

import java.util.List;

import com.mindor.dao.RFdao;
import com.mindor.entity.Intermediate;
import com.mindor.entity.RF;
import com.mindor.serivce.RFService;

public  class RFServiceimpl implements RFService{
 
	private RFdao rfdao;
	public RFdao getRfdao() {
		return rfdao;
	}

	public void setRfdao(RFdao rfdao) {
		this.rfdao = rfdao;
	}

	@Override
	public List<Intermediate> selectRfeq(String userId) {
		// TODO Auto-generated method stub
		return rfdao.selectRfeq(userId);
	}

	@Override
	public List<RF> selectRf(String userId) {
		// TODO Auto-generated method stub
		return rfdao.selectRf(userId);
	}

	@Override
	public void addRf(RF rf) {
		// TODO Auto-generated method stub
		rfdao.addRf(rf);
	}

	@Override
	public void delRf(int rfId) {
		// TODO Auto-generated method stub
		rfdao.delRf(rfId);
	}


	@Override
	public String state(String equipmentId) {
		// TODO Auto-generated method stub
		return rfdao.state(equipmentId);
	}

	@Override
	public void updRf(int rfId, String nickname, String btnName) {
		// TODO Auto-generated method stub
		rfdao.updRf(rfId, nickname, btnName);
	}

	@Override
	public String equipmentNote(String userId, String equipmentId) {
		// TODO Auto-generated method stub
		return rfdao.equipmentNote(userId, equipmentId);
	}
	

}
