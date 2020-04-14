package com.mindor.util;


import org.apache.struts2.ServletActionContext;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.mindor.mqtt.PushCallback;

public class ClientMQTT {
	
	
   public static final String HOST = "tcp://112.74.48.180:1883";	//作为服务器使用（收集客户端发送的消息）
   public static String TOPICTemperature;	//定义消息的主题，客户端接收消息的根据
   public static  String TOPICPm25;
   public static  String TOPICCO2;
   public static  String TOPICHCHO;
   public static  String TOPICTVOC;
   public static  String TOPICHumidity;
   //public static final String TOPICTemperature = "/mindor/509061565/S2/Temperature_Data/";
   
   
   private static String clientid;
   private MqttClient client;
   private MqttConnectOptions options;
	private static MemoryPersistence memoryPersistence;
   private String userName = "admin";
   private String passWord = "1234";

   
   /**
 *@author huangqin
 *  连接服务器(实时数据)
 * May 16, 2019
 */
public void start(String productId,String equipmentId) {
//	  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式 
//	  System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
//	  String date=df.format(new Date());
	   clientid="server";
	   TOPICTemperature="/"+productId+"/"+equipmentId+"/D1/Temperature_Data/";
	   TOPICPm25="/"+productId+"/"+equipmentId+"/D1/Pm25_Data/";
	   TOPICCO2="/"+productId+"/"+equipmentId+"/D1/CO2_Data/";
	   TOPICHCHO="/"+productId+"/"+equipmentId+"/D1/HCHO_Data/";
	   TOPICTVOC="/"+productId+"/"+equipmentId+"/D1/TVOC_Data/";
	   TOPICHumidity="/"+productId+"/"+equipmentId+"/D1/Humidity_Data/";
       try {
           // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
			memoryPersistence = new MemoryPersistence();
           client = new MqttClient(HOST, clientid, memoryPersistence);
           // MQTT的连接设置
           options = new MqttConnectOptions();
           // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
           options.setCleanSession(true);
           // 设置连接的用户名
           options.setUserName(userName);
           // 设置连接的密码
           options.setPassword(passWord.toCharArray());
           // 设置超时时间 单位为秒
           options.setConnectionTimeout(10);
           // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
           options.setKeepAliveInterval(20);
           // 设置回调
           client.setCallback(new PushCallback());
           MqttTopic topic = client.getTopic(TOPICTemperature);
           MqttTopic topic02 = client.getTopic(TOPICPm25);
           MqttTopic topic03 = client.getTopic(TOPICCO2);
           MqttTopic topic04 = client.getTopic(TOPICHCHO); 
           MqttTopic topic05 = client.getTopic(TOPICTVOC);
           MqttTopic topic06 = client.getTopic(TOPICHumidity);
           //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
           options.setWill(topic, "close".getBytes(), 2, true);
           options.setWill(topic02, "close".getBytes(), 2, true);
           options.setWill(topic03, "close".getBytes(), 2, true);
           options.setWill(topic04, "close".getBytes(), 2, true);
           options.setWill(topic05, "close".getBytes(), 2, true);
           options.setWill(topic06, "close".getBytes(), 2, true);

           client.connect(options);
           //订阅消息
           int[] Qos  = {1};
           String[] topic1 = {TOPICTemperature};
           String[] topic2 = {TOPICPm25};
           String[] topic3 = {TOPICCO2};
           String[] topic4 = {TOPICHCHO};
           String[] topic5 = {TOPICTVOC};
           String[] topic6 = {TOPICHumidity};
           client.subscribe(topic1, Qos);
           client.subscribe(topic2, Qos);
           client.subscribe(topic3, Qos);
           client.subscribe(topic4, Qos);
           client.subscribe(topic5, Qos);
           client.subscribe(topic6, Qos);

       } catch (Exception e) {
           e.printStackTrace();
       }
   }

/**
 *@author huangqin
 *  连接服务器(下发指令)
 * May 16, 2019
 */
public void startInstruction(String productId,String equipmentId) {
	   String clientid=equipmentId+"Ins";
	   String TOPIC = null;
	   
	   String productIdStr=productId.substring(0, 3);
	   if(productIdStr.equals("kqy")) {
		   TOPIC="/"+productId+"/"+equipmentId+"/D1/LED/";
	   }else if(productIdStr.equals("zcz")){  //WY+SWITCH
		   TOPIC="/"+productId+"/"+equipmentId+"/D1/";
	   }
	   
       try {
           // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
    	   memoryPersistence = new MemoryPersistence();
           client = new MqttClient(HOST, clientid, memoryPersistence);
           // MQTT的连接设置
           options = new MqttConnectOptions();
           // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
           options.setCleanSession(true);
           // 设置连接的用户名
           options.setUserName(userName);
           // 设置连接的密码
           options.setPassword(passWord.toCharArray());
           // 设置超时时间 单位为秒
           options.setConnectionTimeout(10);
           // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
           options.setKeepAliveInterval(20);
           // 设置回调
           client.setCallback(new PushCallback());
           MqttTopic topic = client.getTopic(TOPIC);
           //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
           options.setWill(topic, "close".getBytes(), 2, true);

           client.connect(options);
           //订阅消息
           int[] Qos  = {1};
           String[] topic1 = {TOPIC};
           client.subscribe(topic1, Qos);
           
         //关闭存储方式
			if(null != memoryPersistence) {
				try {
					memoryPersistence.close();
				} catch (MqttPersistenceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				System.out.println("memoryPersistence is null");
			}
			
//			关闭连接
			if(null != client) {
				if(client.isConnected()) {
					try {
						client.disconnect();
						client.close();
					} catch (MqttException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					System.out.println("mqttClient is not connect");
				}
			}else {
				System.out.println("mqttClient is null");
			}

       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   
   /**
 *@author huangqin
 *  发送消息
 * May 20, 2019
 */
public void publishMessage(String productId,String equipmentId,String message){
	   ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
	   System.out.println("message=================="+message);
	   
	   String clientid=equipmentId+"pub00";
	   String TOPIC="/"+productId+"/"+equipmentId+"/C1/";
//	   String productIdStr=productId.substring(0, 3);
//	   if(productIdStr.equals("kqy")) {
//		   message="WY+RGB_LED="+message;
//	   }else if(productIdStr.equals("zcz")){  //WY+SWITCH
//		   if(message.equals("voiceOn")) {  //声音开
//			   message="WY+BEEP=ON";
//		   }else if(message.equals("voiceOff")){//声音关
//			   message="WY+BEEP=OFF";
//		   }else if(message.equals("do0")){
//			   message="WY+OVER_DO=2";
//		   }else if(message.equals("do1")){
//			   message="WY+OVER_DO=1";
//		   }else if(message.equals("do2")){
//			   message="WY+OVER_DO=0";
//		   }else if(message.length()>50){//发送延时任务指令
//			   message="WY+DelayTime="+message;
//		   }else if(message.equals("del")){//删除延时任务
//			   message="WY+DelayTime="+message;
//		   }else{
//			   message="WY+EEPROM=CO_DATA:"+message;
//		   }
//	   }
	   System.out.println("指令内容："+message);
       try {
           // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
    	   memoryPersistence= new MemoryPersistence();
           client = new MqttClient(HOST, clientid, memoryPersistence);
           // MQTT的连接设置
           options = new MqttConnectOptions();
           // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
           options.setCleanSession(true);
           // 设置连接的用户名
           options.setUserName(userName);
           // 设置连接的密码
           options.setPassword(passWord.toCharArray());
           // 设置超时时间 单位为秒
           options.setConnectionTimeout(10);
           // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
           options.setKeepAliveInterval(20);
           // 设置回调
           client.setCallback(new PushCallback());
           MqttTopic topic = client.getTopic(TOPIC);
           //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
           options.setWill(topic, "close".getBytes(), 2, true);

           client.connect(options);
           //发送消息
		  MqttMessage mqttMessage = new MqttMessage();
		  mqttMessage.setPayload(message.getBytes());
		  mqttMessage.setQos(1);
		  client.publish(TOPIC,mqttMessage);
		  
		  if(null != memoryPersistence) {
				try {
					memoryPersistence.close();
				} catch (MqttPersistenceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				System.out.println("memoryPersistence is null");
			}
			
//			关闭连接
			if(null != client) {
				if(client.isConnected()) {
					try {
						client.disconnect();
						client.close();
					} catch (MqttException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					System.out.println("mqttClient is not connect");
				}
			}else {
				System.out.println("mqttClient is null");
			}
          
          System.out.println("");
       } catch (Exception e) {
           e.printStackTrace();
       }
	   
   }
   
   

}
