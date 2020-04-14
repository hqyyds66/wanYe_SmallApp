package com.mindor.dao;

import java.util.List;
import java.util.Map;

import com.mindor.entity.Equipment;
import com.mindor.entity.Intermediate;
import com.mindor.entity.ShareRecord;

public interface Equipmentdao {
	@SuppressWarnings("rawtypes")
	public List<Map> query(String sql);
	
	public int excuteBySql(String sql);
	
	public void del(int id);
	
	public Equipment findOne(int id);
	
	public List<String> equipmentList();
	
	public List<ShareRecord> subpermission(String equipmentId,String userId);
	
	public List<String> bigSubpermission(String equipmentId);
	
	public void ShareRecord(String userId,String userRoot,String equipmentId);
	
	public boolean ifShareRecord(String userId,String equipmentId);
	
	public String equipmentName(String userId,String equipmentId);
	
	public List<Object> Intermediates(String hql);
	
	public void deleteRecord(String hql);
	
	//--------------------------------------------------------------------------------------------------------
	
	public List<Object>  selectEquipmentBySql(String sql);
	
	public List<Object> selectEquipmentByHql(String hql);
	
	public void saveIntermediate(Intermediate intermediate);
	
	public void updEquipmentState(String equipmentId,int state);
	
	public void updEquipmentState();
	
	public void updateEquipmentByhql(String hql);
	
	public String equVersion(String equipmentId);
	
	public void updLocation(String equipmentId,String locationStr,String longitude,String latitude);
	
	public void deleteInfrared(String eqipmentId);
	
	public void deleteInfrared(String eqipmentId,String userId);
	
	public String tokenGet(String userId);
	
	public String phoneGet(String userId);
	
	public List<Object> getAllEquipmentByUserId(String userId);

}
