package com.mindor.serivce.serviceimpl;

import java.util.List;

import com.mindor.dao.Infrareddao;
import com.mindor.entity.InfraredBin;
import com.mindor.entity.Intermediate;
import com.mindor.serivce.InfraredService;

public class InfraredServiceimpl implements InfraredService {

	private Infrareddao infrareddao;

	public Infrareddao getInfrareddao() {
		return infrareddao;
	}

	public void setInfrareddao(Infrareddao infrareddao) {
		this.infrareddao = infrareddao;
	}

	@Override
	public List<InfraredBin> selectInfrared(String userId) {
		// TODO Auto-generated method stub
		return infrareddao.selectInfrared(userId);
	}

	@Override
	public void addInfrared(InfraredBin infraredBin) {
		// TODO Auto-generated method stub
		infrareddao.addInfrared(infraredBin);
	}

	@Override
	public void delInfrared(int infraredBinId) {
		// TODO Auto-generated method stub
		infrareddao.delInfrared(infraredBinId);
	}

	@Override
	public void updateInfrared(String infraredBinId, String nick) {
		// TODO Auto-generated method stub
		infrareddao.updateInfrared(infraredBinId, nick);
	}

	@Override
	public String state(String equipmentId) {
		// TODO Auto-generated method stub
		return infrareddao.state(equipmentId);
	}

	@Override
	public String equipmentMac(String equipmentId) {
		// TODO Auto-generated method stub
		return infrareddao.equipmentMac(equipmentId);
	}

	@Override
	public String equipmentNote(String userId,String equipmentId) {
		// TODO Auto-generated method stub
		return infrareddao.equipmentNote(userId,equipmentId);
	}

	@Override
	public List<Intermediate> selectInfrared_eq(String userId) {
		// TODO Auto-generated method stub
		return infrareddao.selectInfrared_eq(userId);
	}

	@Override
	public String userIdGet(int infraredBinId) {
		// TODO Auto-generated method stub
		return infrareddao.userIdGet(infraredBinId);
	}

}
