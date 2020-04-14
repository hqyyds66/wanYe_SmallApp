package com.mindor.mqtt;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.eclipse.paho.client.mqttv3.MqttException;

import com.mindor.util.ClientMQTT;

public class mqttLink {

	// 返回数据
	public void backData() throws IOException, MqttException {
		JSONObject json = new JSONObject();
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();

		PushCallback pushCallback = new PushCallback();
		String str = pushCallback.resc();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

		String str02 = str.substring(0, str.indexOf(":"));
		String str03 = str.substring(str.indexOf(":") + 1);

		json.put("name", str02);
		json.put("data", str03);
		json.put("time", date);
		out.print(json);
	}

	// 连接服务器
	public void serverLink() throws IOException {
		JSONObject json = new JSONObject();
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		ClientMQTT client = new ClientMQTT();

		String equipmentId = ServletActionContext.getRequest().getParameter(
				"equipmenId");
		String productId = ServletActionContext.getRequest().getParameter(
				"productId");

		client.start(productId, equipmentId);// 连接服务器
		json.put("size", 1);
		out.print(json);

	}
}
