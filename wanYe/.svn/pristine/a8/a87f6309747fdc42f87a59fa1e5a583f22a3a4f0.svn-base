package com.mindor.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.mindor.util.ClientMQTT;

public class PushCallback implements MqttCallback {

	public static String str;
	ClientMQTT clientm;

	public PushCallback() {
	}

	public PushCallback(ClientMQTT clientm) {
		this.clientm = clientm;
	}

	public void connectionLost(Throwable cause) {
		// SimpleDateFormat df = new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		// String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
		// // 连接丢失后，一般在这里面进行重连
		// System.out.println("[MQTT] 连接断开，30S之后尝试重连..."+date);
		// while(true) {
		// try {
		// Thread.sleep(30000);
		// clientm.reConnect();
		// break;
		// } catch (Exception e) {
		// e.printStackTrace();
		// continue;
		// }
		// }
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("deliveryComplete---------" + token.isComplete());
	}

	public void messageArrived(String topic, MqttMessage message)
			throws Exception {
		str = new String(message.getPayload());
		System.out.println("str=============" + str);
		resc();
	}

	// 用来给调用处返回消息内容
	public String resc() {
		return str;
	}

}
