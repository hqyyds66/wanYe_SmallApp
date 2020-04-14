package com.mindor.serivce.serviceimpl;

import java.util.List;
import java.util.Map;

import com.mindor.dao.Equipmentdao;
import com.mindor.entity.Equipment;
import com.mindor.entity.Intermediate;
import com.mindor.entity.ShareRecord;
import com.mindor.serivce.EquipmentService;

public class EquipmentServiceimpl implements EquipmentService {

	private Equipmentdao equipmentdao;

	public Equipmentdao getEquipmentdao() {
		return equipmentdao;
	}

	public void setEquipmentdao(Equipmentdao equipmentdao) {
		this.equipmentdao = equipmentdao;
	}

	@Override
	public List<ShareRecord> subpermission(String equipmentId, String userId) {
		// TODO Auto-generated method stub
		List<ShareRecord> subpermissions = equipmentdao.subpermission(
				equipmentId, userId);
		return subpermissions;
	}

	@Override
	public List<String> bigSubpermission(String equipmentId) {
		// TODO Auto-generated method stub
		List<String> subpermissions = equipmentdao
				.bigSubpermission(equipmentId);
		return subpermissions;
	}

	@Override
	public void ShareRecord(String userId, String userRoot, String equipmentId) {
		// TODO Auto-generated method stub
		equipmentdao.ShareRecord(userId, userRoot, equipmentId);
	}

	@Override
	public boolean ifShareRecord(String userId, String equipmentId) {
		// TODO Auto-generated method stub
		boolean ifo = equipmentdao.ifShareRecord(userId, equipmentId);
		return ifo;
	}

	@Override
	public String equipmentName(String userId, String equipmentId) {
		// TODO Auto-generated method stub
		return equipmentdao.equipmentName(userId, equipmentId);
	}

	@Override
	public List<Object> Intermediates(String hql) {
		// TODO Auto-generated method stub
		return equipmentdao.Intermediates(hql);
	}

	@Override
	public void deleteRecord(String hql) {
		// TODO Auto-generated method stub
		equipmentdao.deleteRecord(hql);

	}

	@Override
	public List<Object> selectEquipmentByHql(String hql) {
		// TODO Auto-generated method stub
		List<Object> equipments = equipmentdao.selectEquipmentByHql(hql);
		return equipments;
	}

	@Override
	public List<Object> selectEquipmentBySql(String sql) {
		// TODO Auto-generated method stub
		List<Object> equipments = equipmentdao.selectEquipmentBySql(sql);
		return equipments;
	}

	@Override
	public void saveIntermediate(Intermediate intermediate) {
		// TODO Auto-generated method stub
		equipmentdao.saveIntermediate(intermediate);

	}

	@Override
	public void updEquipmentState(String equipmentId, int state) {
		// TODO Auto-generated method stub
		equipmentdao.updEquipmentState(equipmentId, state);
	}

	@Override
	public void updEquipmentState() {
		// TODO Auto-generated method stub
		equipmentdao.updEquipmentState();
	}

	@Override
	public void updateEquipmentByhql(String hql) {
		// TODO Auto-generated method stub
		equipmentdao.updateEquipmentByhql(hql);
	}

	@Override
	public String equVersion(String equipmentId) {
		// TODO Auto-generated method stub
		return equipmentdao.equVersion(equipmentId);
	}

	@Override
	public void updLocation(String equipmentId, String locationStr,
			String longitude, String latitude) {
		// TODO Auto-generated method stub
		equipmentdao.updLocation(equipmentId, locationStr, longitude, latitude);
	}

	@Override
	public void deleteInfrared(String equipmentId) {
		// TODO Auto-generated method stub
		equipmentdao.deleteInfrared(equipmentId);
	}

	@Override
	public void deleteInfrared(String equipmentId, String userId) {
		// TODO Auto-generated method stub
		equipmentdao.deleteInfrared(equipmentId,userId);
	}

	@Override
	public String tokenGet(String userId) {
		// TODO Auto-generated method stub
		return equipmentdao.tokenGet(userId);
	}

	@Override
	public String phoneGet(String userId) {
		// TODO Auto-generated method stub
		return equipmentdao.phoneGet(userId);
	}

	@Override
	public List<Object> getAllEquipmentByUserId(String userId) {
		// TODO Auto-generated method stub
		return equipmentdao.getAllEquipmentByUserId(userId);
	}

}