package com.mindor.entity;

public class HCHO_Data {
	int HCHO_DataId;
	String date;
	String equipmentId;
	String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public int getHCHO_DataId() {
		return HCHO_DataId;
	}

	public void setHCHO_DataId(int dataId) {
		HCHO_DataId = dataId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
