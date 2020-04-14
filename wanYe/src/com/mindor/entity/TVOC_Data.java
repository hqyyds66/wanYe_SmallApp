package com.mindor.entity;

public class TVOC_Data {
	int tvocDataId;
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

	public int getTvocDataId() {
		return tvocDataId;
	}

	public void setTvocDataId(int tvocDataId) {
		this.tvocDataId = tvocDataId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
