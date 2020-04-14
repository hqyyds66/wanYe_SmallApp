package com.mindor.entity;

public class TmtData {
	int TmtDataId;//主键id
	String userId;//用户id
    String equipmentId;//设备Id
    String yearMonth;//年月
	String hourMinute;//时分
    String temp;//温度值
    String humOrThings;//区分是人或物体 0：人 1：物
    String dataTime;//时间
    
    public String getDataTime() {
		return dataTime;
	}
	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}
	public String getHumOrThings() {
		return humOrThings;
	}
	public void setHumOrThings(String humOrThings) {
		this.humOrThings = humOrThings;
	}
	public int getTmtDataId() {
		return TmtDataId;
	}
	public void setTmtDataId(int tmtDataId) {
		TmtDataId = tmtDataId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getYearMonth() {
		return yearMonth;
	}
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	public String getHourMinute() {
		return hourMinute;
	}
	public void setHourMinute(String hourMinute) {
		this.hourMinute = hourMinute;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	
}
