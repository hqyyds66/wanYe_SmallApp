package com.mindor.dao;

import java.util.List;
import java.util.Map;

import com.mindor.entity.EquipmentModel;

public interface EquipmentModeldao {
       @SuppressWarnings("unchecked")
	   public List<Map> equipmentModelList(String userId);
       public EquipmentModel equipmentModelListById(String equipmentModelId,String userId);
       public void updateEquipmentModel(String beginIf,String endIf,String orderOnOff,String equipment,String equipmentModelName,String equipmentModelIcon,String equipmentModelBeginTime,String equipmentModelEndTime,String equipmentModelRepeat,String equipmentList,String onOff,String stateList);
       public int addEquipmentModel(String userId,EquipmentModel equipmentModel);
       public int deleteEquipmentModel(String equipmentModelId);
       public int deleteEquipment(String equipmentModelId,String equipmentId);
}
