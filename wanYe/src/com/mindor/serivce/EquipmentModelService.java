package com.mindor.serivce;

import java.util.List;
import java.util.Map;

import com.mindor.entity.EquipmentModel;

public interface EquipmentModelService {
	public List<Map> equipmentModelList(String userId);
    public EquipmentModel equipmentModelListById(String equipmentModelId,String userId);
    public void updateEquipmentModel(String beginIf,String endIf,String orderOnOff,String equipmentModelId,String equipmentModelName,String equipmentModelIcon,String equipmentModelBeginTime,String equipmentModelEndTime,String equipmentModelRepeat,String equipmentList,String onOff,String stateList);
    public int addEquipmentModel(String userId,EquipmentModel equipmentModel);
    public int deleteEquipmentModel(String equipmentModelId);
    public int deleteEquipment(String equipmentModelId,String equipmentId);
}
