package com.mindor.entity;

public class TimeDelay {
	private int TimeDelayId;
	private String openTime;// 开启延时
	private String closeTime;// 关闭延时
	private String openIf;// 开启延时是否开启
	private String closeIf;// 关闭延时是否开启
	private String repeatTimes;// 重复次数
	private String state;// 开关状态
	private String equipmentId;// 设备id
	private String userId;// 用户id
	private String productId;// 产品id
	private String orderOnOff;// 开关顺序
	private String startTime;// 开始时间
	private String remainTime;// 剩余时间
	private String executeNow;// 当前执行的任务
	private String repeatTimeStr;// 重复次数不变

	public String getRepeatTimeStr() {
		return repeatTimeStr;
	}

	public void setRepeatTimeStr(String repeatTimeStr) {
		this.repeatTimeStr = repeatTimeStr;
	}

	public String getExecuteNow() {
		return executeNow;
	}

	public void setExecuteNow(String executeNow) {
		this.executeNow = executeNow;
	}

	public int getTimeDelayId() {
		return TimeDelayId;
	}

	public void setTimeDelayId(int timeDelayId) {
		TimeDelayId = timeDelayId;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	public String getOpenIf() {
		return openIf;
	}

	public void setOpenIf(String openIf) {
		this.openIf = openIf;
	}

	public String getCloseIf() {
		return closeIf;
	}

	public void setCloseIf(String closeIf) {
		this.closeIf = closeIf;
	}

	public String getRepeatTimes() {
		return repeatTimes;
	}

	public void setRepeatTimes(String repeatTimes) {
		this.repeatTimes = repeatTimes;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getOrderOnOff() {
		return orderOnOff;
	}

	public void setOrderOnOff(String orderOnOff) {
		this.orderOnOff = orderOnOff;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getRemainTime() {
		return remainTime;
	}

	public void setRemainTime(String remainTime) {
		this.remainTime = remainTime;
	}

}
