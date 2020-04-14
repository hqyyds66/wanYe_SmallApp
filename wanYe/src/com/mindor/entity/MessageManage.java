package com.mindor.entity;

public class MessageManage {
	private int messageManageId;// 消息管理信息id
	private String messageType;// 消息类型 1：警告 2：分享 3：解除授权
	private String messageContent;// 消息内容
	private String messageTime;// 消息时间
	private String readIf;// 是否已读 1:已读 2：未读
	private String productId;// 产品id
	private String equipmentId;// 设备id
	private String userId;// 用户id
	private String operationIf;// 分享是否已做处理 1：已处理 2：未处理
	private String link;//跳转链接

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getOperationIf() {
		return operationIf;
	}

	public void setOperationIf(String operationIf) {
		this.operationIf = operationIf;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getMessageManageId() {
		return messageManageId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public void setMessageManageId(int messageManageId) {
		this.messageManageId = messageManageId;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(String messageTime) {
		this.messageTime = messageTime;
	}

	public String getReadIf() {
		return readIf;
	}

	public void setReadIf(String readIf) {
		this.readIf = readIf;
	}
}
