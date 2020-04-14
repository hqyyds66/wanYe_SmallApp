package com.mindor.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.quartz.SchedulerException;

import com.mindor.entity.ChargeGuard;
import com.mindor.entity.TimeDelay;
import com.mindor.entity.Timing;
import com.mindor.serivce.ChargeGuardService;
import com.mindor.serivce.TimeDelayService;
import com.mindor.serivce.TimingService;
import com.mindor.util.ClientMQTT;

public class WanYeApiTiming {
	private TimingService timingService;
	private TimeDelayService timeDelayService;
	private ChargeGuardService chargeGuardService;

	public TimeDelayService getTimeDelayService() {
		return timeDelayService;
	}

	public void setTimeDelayService(TimeDelayService timeDelayService) {
		this.timeDelayService = timeDelayService;
	}

	/**
	 *@author huangqin 添加定时信息 Jun 23, 2019
	 * @throws IOException
	 */
	public void addTiming() throws IOException {
		JSONObject json = new JSONObject();
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		Timing timing = new Timing();
		String equipmentId = ServletActionContext.getRequest().getParameter(
				"equipmentId");
		String userId = ServletActionContext.getRequest()
				.getParameter("userId");

		String productId = ServletActionContext.getRequest().getParameter(
				"productId");
		String beginTime = ServletActionContext.getRequest().getParameter(
				"beginTime");
		String endTime = ServletActionContext.getRequest().getParameter(
				"endTime");
		String openIf = ServletActionContext.getRequest()
				.getParameter("openIf");
		String colseIf = ServletActionContext.getRequest().getParameter(
				"colseIf");
		String repeatTime = ServletActionContext.getRequest().getParameter(
				"repeatTime");
		String state = ServletActionContext.getRequest().getParameter("state");
		
		int taskIDFromApp = -1;
		if(ServletActionContext.getRequest().getParameter("taskIDFromApp")!=null&&ServletActionContext.getRequest().getParameter("taskIDFromApp")!="") {
			 taskIDFromApp=Integer.parseInt(ServletActionContext.getRequest().getParameter("taskIDFromApp"));
		}
		
		 List<Timing> timings=timingService.selectTimings(equipmentId,
		 userId);

		 if(timings.size()>=10){
			 json.put("code", 500);
			 json.put("Message", "最多添加10条定时！");
		 }else{
			 //发送mqtt指令  开始
			 String[] openTimes=beginTime.split(":");//开始时间字符串转数组
			 String[] closeTimes=endTime.split(":");//关闭时间字符串转数组
			 Date date=new Date();
			 int minute=date.getMinutes();//获取当前时间总分钟
			 int hour=date.getHours();
			 int allMinute=hour*60+minute;
			 
			 int Seconds=date.getSeconds();
			 
			 Calendar cal = Calendar.getInstance();
			 int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);// 获取当前时间星期
			 
			 int openTimeInt=Integer.parseInt(openTimes[0])*60+Integer.parseInt(openTimes[1]);
			 int closeTimeInt=Integer.parseInt(closeTimes[0])*60+Integer.parseInt(closeTimes[1]);
			 int repInt=0;
			 if(repeatTime!="0"){
				  repInt=repeatTimeStr(repeatTime);
			 }
			 
			 String Message = "WY+DateTime=set:{\"Date_ID\":"+taskIDFromApp+",\"openIf\":"+openIf+",\"openTime\":"+openTimeInt+",\"closeIf\":"+colseIf+",\"closeTime\":"+closeTimeInt+",\"repetition\":"+repInt+",\"current_week\":"+(WeekOfYear-1)+",\"current_time\":"+allMinute+",\"current_seconds\":"+Seconds+"}";
			 if(taskIDFromApp!=-1) {
				 connectMqtt(productId,equipmentId,Message);
			 }
			    //发送mqtt指令  结束
				timing.setEquipmentId(equipmentId);
				timing.setUserId(userId);
				timing.setProductId(productId);
				timing.setOver("false");
				timing.setRepeatTime(repeatTime);
				timing.setState(state);
				timing.setBeginTime(beginTime);
				timing.setEndTime(endTime);
				timing.setOpenIf(openIf);
				timing.setColseIf(colseIf);
				timing.setTaskIDFromApp(taskIDFromApp);
				timingService.addTiming(timing);
				json.put("code", 200);
				json.put("Message", "添加成功！");
		 }

		out.print(json);
	}
	
	public int repeatTimeStr(String repeatTime){
		String[] repeatTimes=repeatTime.split(",");
		String value = "";
		String value02 = "";
		for(int i=1;i<=7;i++) {
			for(int j=0;j<repeatTimes.length;j++) {
				System.out.println(repeatTimes[j]);
				if(i==Integer.parseInt(repeatTimes[j])) {
					value02="1";
					break;
				}else {
					value02="0";
				}
			}
			value=value+value02;
		}
		System.out.println("星期的二进制数为："+value);
		return convertToDecimal(value);
	}
	
	public int convertToDecimal(String binary) {
	    return Integer.valueOf(binary, 2);
	}

	/**
	 *@author huangqin 查询定时信息 Jun 21, 2019
	 * @throws IOException
	 */
	public void selectTimings() throws IOException {
		JSONObject json = new JSONObject();
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();

		List<Timing> Timing;
		JSONArray jsonObject = null;
		String equipmentId = ServletActionContext.getRequest().getParameter(
				"equipmentId");
		String userId = ServletActionContext.getRequest()
				.getParameter("userId");
		Timing = timingService.selectTimings(equipmentId, userId);
		jsonObject = JSONArray.fromObject(Timing);// 转json
		json.put("code", 200);
		json.put("Message", "success");
		json.put("data", jsonObject);
		out.print(json);
	}

	/**
	 *@author huangqin 更新定时信息 Jun 21, 2019
	 * @throws SchedulerException
	 */
	public void updateTiming() throws IOException, SchedulerException {

		JSONObject json = new JSONObject();
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();

		Timing timing = new Timing();
		String equipmentId = ServletActionContext.getRequest().getParameter(
				"equipmentId");
		String userId = ServletActionContext.getRequest()
				.getParameter("userId");
		int timingId = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("timingId"));

		String productId = ServletActionContext.getRequest().getParameter(
				"productId");
		String beginTime = ServletActionContext.getRequest().getParameter(
				"beginTime");
		String endTime = ServletActionContext.getRequest().getParameter(
				"endTime");
		String openIf = ServletActionContext.getRequest()
				.getParameter("openIf");
		String colseIf = ServletActionContext.getRequest().getParameter(
				"colseIf");

		String repeatTime = ServletActionContext.getRequest().getParameter(
				"repeatTime");
		String state = ServletActionContext.getRequest().getParameter("state");
		
		timing = timingService.selectTiming(timingId);
		int taskIDFromApp=timing.getTaskIDFromApp();//任务id
		
		if(taskIDFromApp!=-1) {//兼容还未更新app的用户
			 //发送mqtt指令  开始
			 String[] openTimes=beginTime.split(":");//开始时间字符串转数组
			 String[] closeTimes=endTime.split(":");//关闭时间字符串转数组
			 Date date=new Date();
			 int minute=date.getMinutes();//获取当前时间总分钟
			 int hour=date.getHours();
			 int allMinute=hour*60+minute;
			 int Seconds=date.getSeconds();
			 
			 Calendar cal = Calendar.getInstance();
			 int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);// 获取当前时间星期
			 
			 int openTimeInt=Integer.parseInt(openTimes[0])*60+Integer.parseInt(openTimes[1]);
			 int closeTimeInt=Integer.parseInt(closeTimes[0])*60+Integer.parseInt(closeTimes[1]);
			 int repInt=0;
			 if(repeatTime!="0"){
				  repInt=repeatTimeStr(repeatTime);
			 }
			 
			 String Message = "WY+DateTime=set:{\"Date_ID\":"+taskIDFromApp+",\"openIf\":"+openIf+",\"openTime\":"+openTimeInt+",\"closeIf\":"+colseIf+",\"closeTime\":"+closeTimeInt+",\"repetition\":"+repInt+",\"current_week\":"+(WeekOfYear-1)+",\"current_time\":"+allMinute+",\"current_seconds\":"+Seconds+"}";
			 if(taskIDFromApp!=-1) {
				 connectMqtt(productId,equipmentId,Message);
			 }
			    //发送mqtt指令  结束
		}
		
		timing.setTimingId(timingId);
		timing.setEquipmentId(equipmentId);
		timing.setUserId(userId);
		timing.setProductId(productId);
		timing.setRepeatTime(repeatTime);
		timing.setState(state);
		timing.setOver("false");
		timing.setBeginTime(beginTime);
		timing.setEndTime(endTime);
		timing.setOpenIf(openIf);
		timing.setColseIf(colseIf);

		timingService.updateTiming(timing);
		// perform();
		json.put("code", 200);
		json.put("Message", "更新成功！");
		out.print(json);
	}

	/**
	 *@author huangqin 删除定时 Jun 23, 2019
	 */
	public void deleteTiming() throws IOException {
		JSONObject json = new JSONObject();
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		int timingId = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("timingId"));
		Timing timing=new Timing();
		timing = timingService.selectTiming(timingId);
		int taskIDFromApp=timing.getTaskIDFromApp();//任务id
		String productId=timing.getProductId();
		String equipmentId=timing.getEquipmentId();
		 Date date=new Date();
		 int minute=date.getMinutes();//获取当前时间总分钟
		 int hour=date.getHours();
		 int allMinute=hour*60+minute;
		 int Seconds=date.getSeconds();
		 
		 Calendar cal = Calendar.getInstance();
		 int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);// 获取当前时间星期
		 
		 if(taskIDFromApp!=-1) {//兼容还未更新app的用户
			 String Message="WY+DateTime=del_ID:{\"Date_ID\":"+taskIDFromApp+",\"current_week\":"+(WeekOfYear-1)+",\"current_time\":"+allMinute+",\"current_seconds\":"+Seconds+"}";
			 connectMqtt(productId,equipmentId,Message);
		 }
		
		 timingService.deleteTiming(timingId);
		 json.put("code", 200);
		 json.put("Message", "删除成功！");
		 out.print(json);
	}

	/**
	 *@author huangqin 主界面数据 Jul 1, 2019
	 * @throws IOException
	 * @throws ParseException
	 */
	@SuppressWarnings( { "rawtypes", "deprecation" })
	public void homePage() throws IOException, ParseException {
		JSONObject json = new JSONObject();

		Date now01 = new Date();
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		SimpleDateFormat nowFormat = new SimpleDateFormat("HH:mm");
		JSONArray jsonObject = null;

		String equipmentId = ServletActionContext.getRequest().getParameter(
				"equipmentId");
		String userId = ServletActionContext.getRequest()
				.getParameter("userId");
		TimeDelay timeDelay = timeDelayService.selectTimeDelays(equipmentId,
				userId);
		Timing timing = timingService.selectHomePage(equipmentId, userId);
		ChargeGuard chargeGuard = chargeGuardService.selectState(userId,
				equipmentId);// 获取充电保护状态

		List<HashMap> mylist = new LinkedList<HashMap>();
		HashMap<String, String> hashMapObject = new HashMap<String, String>();

		if (timing != null) {
			System.out.println("timing=====" + timing);
			System.out.println("timing=====" + timing.getTimingId());
			if (timing.getOpenIf().equals("1")
					&& timing.getColseIf().equals("1")) {

				Date timingbData;
				Date timingeData;
				String timeWeek = null;
				String timeExecute = null;
				try {
					timingbData = nowFormat.parse(timing.getBeginTime());
					timingeData = nowFormat.parse(timing.getEndTime());
					String nowDataStr = nowFormat.format(now01.getTime());
					Date nowData = nowFormat.parse(nowDataStr);

					if (nowData.compareTo(timingeData) > 0) {
						if (timingbData.compareTo(timingeData) < 0) {
							timeExecute = timing.getBeginTime();
							hashMapObject.put("timeExecuteNow", "1");
						} else {
							timeExecute = timing.getEndTime();
							hashMapObject.put("timeExecuteNow", "2");
						}
					} else if (nowData.compareTo(timingbData) > 0) {
						timeExecute = timing.getEndTime();
						hashMapObject.put("timeExecuteNow", "2");
					} else {
						if (timingbData.compareTo(timingeData) < 0) {
							timeExecute = timing.getBeginTime();
							hashMapObject.put("timeExecuteNow", "1");
						} else {
							timeExecute = timing.getEndTime();
							hashMapObject.put("timeExecuteNow", "2");
						}
					}
					timeWeek = weekStr(timeExecute, timing.getRepeatTime());
					System.out.println("timeWeek==" + timeWeek);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				hashMapObject.put("timeWeek", timeWeek);
				hashMapObject.put("timeExecute", timeExecute);

			} else if (timing.getOpenIf().equals("1")) {
				hashMapObject.put("timeWeek", weekStr(timing.getBeginTime(),
						timing.getRepeatTime()));
				hashMapObject.put("timeExecute", timing.getBeginTime());
				hashMapObject.put("timeExecuteNow", "1");
			} else {
				hashMapObject.put("timeWeek", weekStr(timing.getEndTime(),
						timing.getRepeatTime()));
				hashMapObject.put("timeExecute", timing.getEndTime());
				hashMapObject.put("timeExecuteNow", "2");
			}

		} else {
			hashMapObject.put("timeExecute", "");
			hashMapObject.put("timeExecuteNow", "");
			hashMapObject.put("timeWeek", "");
		}

		if (timeDelay != null) {

			// 获取延时时间
			String openTime = timeDelay.getOpenTime();
			String closeTime = timeDelay.getCloseTime();

			String[] openTimeStr = openTime.split(":");
			String[] closeTimeStr = closeTime.split(":");
			int openHourStr = Integer.parseInt(openTimeStr[0]);
			int openMinuteStr = Integer.parseInt(openTimeStr[1]);
			int closeHourStr = Integer.parseInt(closeTimeStr[0]);
			int closeMinuteStr = Integer.parseInt(closeTimeStr[1]);

			long openLon = (openHourStr * 60 * 60 * 1000) + openMinuteStr * 60
					* 1000;
			long closeLon = (closeHourStr * 60 * 60 * 1000) + closeMinuteStr
					* 60 * 1000;

			long Execute;
			if (timeDelay.getExecuteNow().equals("1")
					&& timeDelay.getOpenIf().equals("1")) {
				Execute = openLon;
			} else {
				Execute = closeLon;
			}
			// 计算剩余执行时间

			if (timeDelay.getState().equals("1")) {

				long startTime = Long.parseLong(timeDelay.getStartTime());
				Date now = new Date();
				long a = now.getTime() - startTime;

				long remainTime;
				int aa;
				if (a < Execute) {
					remainTime = Execute - a;// 剩余时间
					aa = (int) remainTime;
				} else {
					remainTime = a - Execute;// 剩余时间
					aa = (int) (a % Execute);
					aa = (int) (Execute - aa);
				}

				Date remainDate = new Date(aa);

				hashMapObject.put("delayRemainDate", String.valueOf(remainDate
						.getMinutes()
						* 60 + remainDate.getSeconds()));
				hashMapObject.put("delayExecuteNow", timeDelay.getExecuteNow());
			} else {
				hashMapObject.put("delayRemainDate", "");
				hashMapObject.put("delayExecuteNow", "");
			}

		} else {
			hashMapObject.put("delayRemainDate", "");
			hashMapObject.put("delayExecuteNow", "");
		}

		if (chargeGuard != null) {
			hashMapObject.put("phstate", String.valueOf(chargeGuard
					.getPhstate()));
			hashMapObject.put("hmstate", String.valueOf(chargeGuard
					.getHmstate()));
			hashMapObject.put("ChargingPro", String.valueOf(chargeGuard
					.getState()));
			hashMapObject.put("voicestate", String.valueOf(chargeGuard
					.getVoicestate()));
		} else {
			hashMapObject.put("phstate", "");
			hashMapObject.put("hmstate", "");
			hashMapObject.put("ChargingPro", "");
			hashMapObject.put("voicestate", "");
		}

		mylist.add(hashMapObject);
		jsonObject = JSONArray.fromObject(mylist);// 转json

		json.put("code", 200);
		json.put("Message", "success");
		json.put("data", jsonObject);
		out.print(json);
	}

	/**
	 *@author huangqin 获取定时下次执行的星期 Jul 2, 2019
	 */
	public String weekStr(String time, String repeatTimes)
			throws ParseException {

		String repeatTime;
		Date now = new Date();
		SimpleDateFormat nowFormat = new SimpleDateFormat("HH:mm");
		String nowDataStr = nowFormat.format(now.getTime());
		Date nowData = nowFormat.parse(nowDataStr);
		Date timeDate = nowFormat.parse(time);
		List<String> gameids = java.util.Arrays.asList(repeatTimes.split(","));

		boolean wIf = weekIf(gameids);
		if (timeDate.compareTo(nowData) > 0 && wIf) {// 当前时间比计划时间大 ，星期取今天
			Calendar cal = Calendar.getInstance();
			int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);// 一周的第几天
			repeatTime = String.valueOf(WeekOfYear - 1);
		} else {
			Calendar cal = Calendar.getInstance();
			int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);// 一周的第几天
			WeekOfYear = WeekOfYear - 1;

			if (gameids.get(0).equals("always")) {
				repeatTime = String.valueOf(WeekOfYear + 1);

			} else {
				List<Integer> mylist = new LinkedList<Integer>();
				List<Integer> mylist1 = new LinkedList<Integer>();
				for (int i = 0; i < gameids.size(); i++) {
					if (WeekOfYear < Integer.parseInt(gameids.get(i))) {
						mylist.add(Integer.parseInt(gameids.get(i)));
					}
				}

				for (int i = 0; i < gameids.size(); i++) {
					if (WeekOfYear > Integer.parseInt(gameids.get(i))) {
						mylist1.add(Integer.parseInt(gameids.get(i)));
					}
				}

				if (mylist.size() > 0) {
					for (int i = 0; i < mylist.size() - 1; i++) {// 冒泡排序，取最小值
						for (int j = 0; j < mylist.size() - 1 - i; j++) {
							if (mylist.get(j) > mylist.get(j + 1)) {
								int temp;
								temp = mylist.get(j);
								mylist.set(j, mylist.get(j + 1));
								mylist.set(j + 1, temp);
							}
						}
					}
					repeatTime = String.valueOf(mylist.get(0));
				} else if (mylist1.size() > 0) {
					for (int i = 0; i < mylist1.size() - 1; i++) {// 冒泡排序，取最小值
						for (int j = 0; j < mylist1.size() - 1 - i; j++) {
							if (mylist1.get(j) > mylist1.get(j + 1)) {
								int temp;
								temp = mylist1.get(j);
								mylist1.set(j, mylist1.get(j + 1));
								mylist1.set(j + 1, temp);
							}
						}
					}
					repeatTime = String.valueOf(mylist1.get(0));

				} else {
					repeatTime = "";

				}
			}
		}
		return repeatTime;
	}

	public Boolean weekIf(List<String> gameids) {
		Boolean wIf;
		int a = 0;
		Calendar cal = Calendar.getInstance();
		int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);// 一周的第几天
		String repeatTime = String.valueOf(WeekOfYear - 1);
		for (int i = 0; i < gameids.size(); i++) {
			if (gameids.get(i).equals(repeatTime)) {
				a = a + 1;
			}
		}
		if (a > 0) {
			wIf = true;
		} else {
			wIf = false;
		}
		return wIf;
	}

	/**
	 *@author huangqin 修改充电保护状态 Jul 3, 2019
	 * @throws IOException
	 */
	public void updateChargeState() throws IOException {
		JSONObject json = new JSONObject();
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		String equipmentId = ServletActionContext.getRequest().getParameter(
				"equipmentId");
		String userId = ServletActionContext.getRequest()
				.getParameter("userId");

		String phstate = ServletActionContext.getRequest().getParameter(
				"phstate");
		String hmstate = ServletActionContext.getRequest().getParameter(
				"hmstate");
		String voicestate = ServletActionContext.getRequest().getParameter(
				"voicestate");

		int phstateI;
		int hmstateI;
		int voicestateI;

		if (voicestate != null && voicestate != "") {
			ClientMQTT client = new ClientMQTT();
			// client.startInstruction(productId,equipmentId);//连接服务器
			String productId = equipmentId.substring(0, 6);
			if (voicestate.equals("1")) {
				client.publishMessage(productId, equipmentId, "voiceOn");// 发送指令
			} else if (voicestate.equals("2")) {
				client.publishMessage(productId, equipmentId, "voiceOff");// 发送指令
			}
		} else {
			voicestate = "1";
		}

		if (phstate != null && hmstate != null) {
			phstateI = Integer.parseInt(phstate);
			hmstateI = Integer.parseInt(hmstate);
			voicestateI = Integer.parseInt(voicestate);

			chargeGuardService.updateState(userId, equipmentId, phstateI,
					hmstateI, voicestateI);
			json.put("code", 200);
			json.put("Message", "更新成功！");
		} else {
			json.put("code", 500);
			json.put("Message", "请更新版本！");
		}

		out.print(json);
	}
	
	//发送mqtt 指令写入硬件
	public void connectMqtt(String productId,String equipmentId,String Message) {
		//发送mqtt指令  写入硬件
		ClientMQTT client = new ClientMQTT();
		 // client.startInstruction(productId,equipmentId);//连接服务器
		client.publishMessage(productId, equipmentId, Message);// 发送指令
		
	}

	/**
	 *@author huangqin 根据id查找定时 Jun 23, 2019
	 */
	public void selectTiming() throws IOException {
		JSONObject json = new JSONObject();
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();

		Timing timing = new Timing();
		JSONArray jsonObject = null;
		int timingId = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("timingId"));
		timing = timingService.selectTiming(timingId);
		jsonObject = JSONArray.fromObject(timing);// 转json
		json.put("code", 200);
		json.put("Message", "success");
		json.put("data", jsonObject);
		out.print(json);
	}

	public TimingService getTimingService() {
		return timingService;
	}

	public void setTimingService(TimingService timingService) {
		this.timingService = timingService;
	}

	public ChargeGuardService getChargeGuardService() {
		return chargeGuardService;
	}

	public void setChargeGuardService(ChargeGuardService chargeGuardService) {
		this.chargeGuardService = chargeGuardService;
	};
}
