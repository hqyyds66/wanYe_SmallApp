package com.mindor.serivce;


public interface PowerCountService {
	 public String powerList(String equipmentId,String time,String timeStr);
	 public void delPower(String equipmentId);
}
