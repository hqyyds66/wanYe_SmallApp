package com.mindor.entity;

public class EquipmentInfo {
	private String equipmentId;
	private String equipmentName;
	private String equipmentNote;
	private String equipmentLabel;
	private String specificEquipmentLabel;
	private String productId;// 产品id
	private String productImage;// 产品图片
	private String productIcon;// 产品图标
	private String equipmentModelState;// 设备模式状态
	private String equipmentState;// 设备状态
	private String role;// 权限
	private String warnValue;// 警告值

	public String getWarnOperate() {
		return warnOperate;
	}

	public void setWarnOperate(String warnOperate) {
		this.warnOperate = warnOperate;
	}

	private String blackout;// 延时断电
	private String warnOperate;// 警告操作

	public String getRole() {
		return role;
	}

	public String getWarnValue() {
		return warnValue;
	}

	public void setWarnValue(String warnValue) {
		this.warnValue = warnValue;
	}

	public String getBlackout() {
		return blackout;
	}

	public void setBlackout(String blackout) {
		this.blackout = blackout;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getProductIcon() {
		return productIcon;
	}

	public void setProductIcon(String productIcon) {
		this.productIcon = productIcon;
	}

	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getEquipmentNote() {
		return equipmentNote;
	}

	public void setEquipmentNote(String equipmentNote) {
		this.equipmentNote = equipmentNote;
	}

	public String getEquipmentLabel() {
		return equipmentLabel;
	}

	public void setEquipmentLabel(String equipmentLabel) {
		this.equipmentLabel = equipmentLabel;
	}

	public String getSpecificEquipmentLabel() {
		return specificEquipmentLabel;
	}

	public void setSpecificEquipmentLabel(String specificEquipmentLabel) {
		this.specificEquipmentLabel = specificEquipmentLabel;
	}

	public String getEquipmentModelState() {
		return equipmentModelState;
	}

	public void setEquipmentModelState(String equipmentModelState) {
		this.equipmentModelState = equipmentModelState;
	}

	public String getEquipmentState() {
		return equipmentState;
	}

	public void setEquipmentState(String equipmentState) {
		this.equipmentState = equipmentState;
	}
}
