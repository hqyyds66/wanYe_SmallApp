package com.mindor.serivce.serviceimpl;

import com.mindor.dao.PowerCountdao;
import com.mindor.serivce.PowerCountService;

public class PowerCountServiceimpl implements PowerCountService {
	private PowerCountdao powerCountdao;

	public PowerCountdao getPowerCountdao() {
		return powerCountdao;
	}

	public void setPowerCountdao(PowerCountdao powerCountdao) {
		this.powerCountdao = powerCountdao;
	}

	@Override
	public String powerList(String equipmentId, String time, String timeStr) {
		// TODO Auto-generated method stub
		return powerCountdao.powerList(equipmentId, time, timeStr);
	}

	@Override
	public void delPower(String equipmentId) {
		// TODO Auto-generated method stub
		powerCountdao.delPower(equipmentId);
	}

}