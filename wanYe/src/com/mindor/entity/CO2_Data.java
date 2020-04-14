package com.mindor.entity;

public class CO2_Data {
	int co2DataId;
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

	public int getCo2DataId() {
		return co2DataId;
	}

	public void setCo2DataId(int co2DataId) {
		this.co2DataId = co2DataId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
