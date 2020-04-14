package com.mindor.serivce.serviceimpl;

import java.util.List;
import java.util.Map;

import com.mindor.dao.EquipmentModeldao;
import com.mindor.entity.EquipmentModel;
import com.mindor.serivce.EquipmentModelService;

public class EquipmentModelServiceImpl implements EquipmentModelService {

	private EquipmentModeldao equipmentModeldao;

	@Override
	public int addEquipmentModel(String userId, EquipmentModel equipmentModel) {
		// TODO Auto-generated method stub
		int size;
		size = equipmentModeldao.addEquipmentModel(userId, equipmentModel);
		return size;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map> equipmentModelList(String userId) {
		// TODO Auto-generated method stub
		List<Map> equipmentModel;
		equipmentModel = equipmentModeldao.equipmentModelList(userId);
		return equipmentModel;
	}

	@Override
	public EquipmentModel equipmentModelListById(String equipmentModelId,
			String userId) {
		// TODO Auto-generated method stub
		EquipmentModel equipmentModel;
		equipmentModel = equipmentModeldao.equipmentModelListById(
				equipmentModelId, userId);
		return equipmentModel;
	}

	@Override
	public void updateEquipmentModel(String beginIf, String endIf,
			String orderOnOff, String equipmentModelId,
			String equipmentModelName, String equipmentModelIcon,
			String equipmentModelBeginTime, String equipmentModelEndTime,
			String equipmentModelRepeat, String equipmentList, String onOff,
			String stateList) {
		// TODO Auto-generated method stub
		equipmentModeldao.updateEquipmentModel(beginIf, endIf, orderOnOff,
				equipmentModelId, equipmentModelName, equipmentModelIcon,
				equipmentModelBeginTime, equipmentModelEndTime,
				equipmentModelRepeat, equipmentList, onOff, stateList);
	}

	public EquipmentModeldao getEquipmentModeldao() {
		return equipmentModeldao;
	}

	public void setEquipmentModeldao(EquipmentModeldao equipmentModeldao) {
		this.equipmentModeldao = equipmentModeldao;
	}

	@Override
	public int deleteEquipmentModel(String equipmentModelId) {
		// TODO Auto-generated method stub
		int size = equipmentModeldao.deleteEquipmentModel(equipmentModelId);
		return size;
	}

	@Override
	public int deleteEquipment(String equipmentModelId, String equipmentId) {
		// TODO Auto-generated method stub
		int size = equipmentModeldao.deleteEquipment(equipmentModelId,
				equipmentId);
		return size;
	}

}
