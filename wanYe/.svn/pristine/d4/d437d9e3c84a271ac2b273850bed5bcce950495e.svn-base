package com.mindor.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.quartz.SchedulerException;

import com.mindor.entity.TimeDelay;
import com.mindor.entity.Timing;
import com.mindor.serivce.TimeDelayService;
import com.mindor.serivce.TimingService;
import com.mindor.util.ClientMQTT;

public class WanYeApiTimeDelay {
	private TimeDelayService timeDelayService;
	private TimingService timingService;

	/**
	 *@author huangqin 添加延时信息 Jun 23, 2019
	 * @throws IOException
	 * @throws ParseException
	 */
	public void addTimeDelay() throws IOException, ParseException {

		JSONObject json = new JSONObject();
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		TimeDelay timeDelay = new TimeDelay();
		Timing timing = new Timing();
		String equipmentId = ServletActionContext.getRequest().getParameter(
				"equipmentId");
		String userId = ServletActionContext.getRequest()
				.getParameter("userId");

		String productId = ServletActionContext.getRequest().getParameter(
				"productId");
		String openTime = ServletActionContext.getRequest().getParameter(
				"openTime");
		String closeTime = ServletActionContext.getRequest().getParameter(
				"closeTime");
		String openIf = ServletActionContext.getRequest()
				.getParameter("openIf");
		String closeIf = ServletActionContext.getRequest().getParameter(
				"closeIf");
		String orderOnOff = ServletActionContext.getRequest().getParameter(
				"orderOnOff");

		String repeatTimes = ServletActionContext.getRequest().getParameter(
				"repeatTimes");
		String state = ServletActionContext.getRequest().getParameter("state");

		if (openIf.equals("2") && closeIf.equals("2")) {
			json.put("code", 500);
			json.put("Message", "必须添加一个延时任务！");

		} else {

			TimeDelay timeDelays = timeDelayService.selectTimeDelays(
					equipmentId, userId);

			if (timeDelays != null) {
				json.put("code", 500);
				json.put("Message", "已经添加过了延时！");

			} else if(repeatTimes!=null&&repeatTimes!=""&&userId!=null&&userId!=""&&closeIf!=null&&closeIf!=""&&openIf!=null&&openIf!=""){
				
				 String[] openTimes=openTime.split(":");
				 String[] closeTimes=closeTime.split(":");
				 int repeatTimesInt=0;
				 
				 System.out.println("repeatTimes==="+repeatTimes);
				 
				 if(repeatTimes.equals("0")) {
					 repeatTimesInt=1;
				 }else if(repeatTimes.equals("always")){
					 repeatTimesInt=250;
				 }else {
					 repeatTimesInt=Integer.parseInt(repeatTimes);
					 
				 }
				 int openTimeInt=Integer.parseInt(openTimes[0])*60+Integer.parseInt(openTimes[1]);
				 int closeTimeInt=Integer.parseInt(closeTimes[0])*60+Integer.parseInt(closeTimes[1]);
				 
				 String Value="set:{"+"\""+"openTime"+"\":"+openTimeInt+","+"\""+"closeTime"+"\":"+closeTimeInt+","+"\""+"repeatTimes"+"\":"+repeatTimesInt+","+"\""+"openIf"+"\":"+openIf+","+"\""+"closeIf"+"\":"+closeIf+","+"\""+"orderOnOff"+"\":"+orderOnOff+"}";
				
				 System.out.println("Value:"+Value);
				 //发送mqtt指令  写入硬件
				ClientMQTT client = new ClientMQTT();
				 // client.startInstruction(productId,equipmentId);//连接服务器
				client.publishMessage(productId, equipmentId, Value);// 发送指令
				
				if (state.equals("1")) {// 如果设备开启记录开启时间
					Date now = new Date();
					timeDelay.setStartTime(String.valueOf(now.getTime()));
				} else {
					timeDelay.setStartTime("null");
				}

				timeDelay.setEquipmentId(equipmentId);
				timeDelay.setUserId(userId);
				timeDelay.setProductId(productId);
				timeDelay.setRepeatTimes(repeatTimes);
				timeDelay.setRepeatTimeStr(repeatTimes);
				timeDelay.setState(state);
				timeDelay.setOpenTime(openTime);
				timeDelay.setCloseTime(closeTime);
				timeDelay.setOpenIf(openIf);
				timeDelay.setCloseIf(closeIf);
				timeDelay.setOrderOnOff(orderOnOff);
				if (openIf.equals("1") && closeIf.equals("1")) {
					timeDelay.setExecuteNow(orderOnOff);
				} else {
					if (openIf.equals("2")) {
						timeDelay.setExecuteNow("2");
					}
					if (closeIf.equals("2")) {
						timeDelay.setExecuteNow("1");
					}
				}
				Integer timeDelayId = timeDelayService.addTimeDelay(timeDelay);

				// 添加定时
				String timeStr = changTime(openTime, closeTime, orderOnOff,
						openIf, closeIf);
				String[] timeStrs = timeStr.split(",");
				String beginTime = timeStrs[0];
				String endTime = timeStrs[1];

				timing.setUserId(userId);
				timing.setEquipmentId(equipmentId);
				timing.setProductId(productId);
				timing.setState(state);
				timing.setColseIf(closeIf);
				timing.setOpenIf(openIf);
				timing.setBeginTime(beginTime);
				timing.setEndTime(endTime);
				timing.setTimeDelayId(timeDelayId);
				timing.setRepeatTime(repeatTimes);
				timingService.addTiming(timing);
				// 添加定时结束
				json.put("code", 200);
				json.put("Message", "添加成功！");
			}else {
				json.put("code", 500);
				json.put("Message", "传参异常！");
			}

		}
		out.print(json);

	}

	public String changTime(String openTime, String closeTime,
			String orderOnOff, String openIf, String closeIf)
			throws ParseException {
		SimpleDateFormat nowFormat = new SimpleDateFormat("HH:mm:ss");
		Date now = new Date();
		String[] openTimeStr = openTime.split(":");
		String[] closeTimeStr = closeTime.split(":");
		int openHourStr = Integer.parseInt(openTimeStr[0]);
		int openMinuteStr = Integer.parseInt(openTimeStr[1]);
		int closeHourStr = Integer.parseInt(closeTimeStr[0]);
		int closeMinuteStr = Integer.parseInt(closeTimeStr[1]);

		long openLon = (openHourStr * 60 * 60 * 1000) + openMinuteStr * 60
				* 1000;
		long closeLon = (closeHourStr * 60 * 60 * 1000) + closeMinuteStr * 60
				* 1000;

		long beginTime = 0;
		long endTime = 0;
		// 判断开关顺序
		if (openIf.equals("1") && closeIf.equals("1")) {
			if (orderOnOff.equals("1")) {
				beginTime = now.getTime() + openLon;
				endTime = beginTime + closeLon;
			} else {
				endTime = now.getTime() + closeLon;
				beginTime = endTime + openLon;
			}
		} else {
			if (openIf.equals("2")) {
				beginTime = 0;
				endTime = now.getTime() + closeLon;
			}
			if (closeIf.equals("2")) {
				beginTime = now.getTime() + openLon;
				endTime = 0;
			}

		}

		Date beginDate = new Date(beginTime);
		Date endDate = new Date(endTime);

		String beginStr = nowFormat.format(beginDate);
		String endStr = nowFormat.format(endDate);

		String timeStr = beginStr + "," + endStr;
		return timeStr;
	};

	/**
	 *@author huangqin 查询延时信息 Jun 21, 2019
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public void selectTimeDelays() throws IOException {
		JSONObject json = new JSONObject();
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		SimpleDateFormat nowFormat = new SimpleDateFormat("HH:mm");
		SimpleDateFormat nowFormat01 = new SimpleDateFormat("mm:ss");
		int code;
		String Message;
		JSONArray jsonObject = null;
		String equipmentId = ServletActionContext.getRequest().getParameter(
				"equipmentId");
		String userId = ServletActionContext.getRequest()
				.getParameter("userId");
		TimeDelay timeDelay = timeDelayService.selectTimeDelays(equipmentId,
				userId);

		List<TimeDelay> mylist = new LinkedList<TimeDelay>();

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

				Date endTime = new Date(a);
				Date beginDate = new Date(startTime);
				Date remainDate = new Date(aa);

				String endStr = nowFormat01.format(endTime);
				String beginStr = nowFormat.format(beginDate);
				String remainStr = nowFormat01.format(remainDate);

				timeDelay.setRemainTime(String.valueOf(remainDate.getMinutes()
						* 60 + remainDate.getSeconds()));

				System.out.println("开始时间==============" + beginStr);
				System.out.println("相差时间==============" + endStr);
				System.out.println("剩余时间==============" + remainStr);
			} else {
				timeDelay.setRemainTime("over");
			}
			mylist.add(timeDelay);
		}

		code = 200;
		Message = "success";
		jsonObject = JSONArray.fromObject(mylist);// 转json

		json.put("code", code);
		json.put("Message", Message);

		json.put("data", jsonObject);

		out.print(json);
	}

	/**
	 *@author huangqin 更新延时信息 Jun 21, 2019
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public void updateTimeDelay() throws IOException, SchedulerException,
			ParseException {

		JSONObject json = new JSONObject();
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();

		TimeDelay timeDelay = new TimeDelay();
		Timing timing = new Timing();
		String equipmentId = ServletActionContext.getRequest().getParameter(
				"equipmentId");
		String userId = ServletActionContext.getRequest()
				.getParameter("userId");
		int timeDelayId = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("timeDelayId"));

		String productId = ServletActionContext.getRequest().getParameter(
				"productId");
		String openTime = ServletActionContext.getRequest().getParameter(
				"openTime");
		String closeTime = ServletActionContext.getRequest().getParameter(
				"closeTime");
		String openIf = ServletActionContext.getRequest()
				.getParameter("openIf");
		String closeIf = ServletActionContext.getRequest().getParameter(
				"closeIf");
		String orderOnOff = ServletActionContext.getRequest().getParameter(
				"orderOnOff");

		String repeatTimes = ServletActionContext.getRequest().getParameter(
				"repeatTimes");
		String state = ServletActionContext.getRequest().getParameter("state");

		if (openIf.equals("2") && closeIf.equals("2")) {
			json.put("code", 500);
			json.put("Message", "必须添加一个延时任务！");
		} else {
			
			String[] openTimes=openTime.split(":");
			 String[] closeTimes=closeTime.split(":");
			 int repeatTimesInt=0;
			 if(repeatTimes.equals("0")) {
				 repeatTimesInt=1;
			 }else if(repeatTimes.equals("always")){
				 repeatTimesInt=250;
			 }else {
				 repeatTimesInt=Integer.parseInt(repeatTimes);
			 }
			 int openTimeInt=Integer.parseInt(openTimes[0])*60+Integer.parseInt(openTimes[1]);
			 int closeTimeInt=Integer.parseInt(closeTimes[0])*60+Integer.parseInt(closeTimes[1]);
			 String Value;
			 if (state.equals("1")) {
				 Value="WY+DelayTime=set:{"+"\""+"openTime"+"\":"+openTimeInt+","+"\""+"closeTime"+"\":"+closeTimeInt+","+"\""+"repeatTimes"+"\":"+repeatTimesInt+","+"\""+"openIf"+"\":"+openIf+","+"\""+"closeIf"+"\":"+closeIf+","+"\""+"orderOnOff"+"\":"+orderOnOff+"}";
			 }else {
				 Value="WY+DelayTime=del";
			 }
			
			 System.out.println("Value:"+Value);
			 //发送mqtt指令  写入硬件
			ClientMQTT client = new ClientMQTT();
			 // client.startInstruction(productId,equipmentId);//连接服务器
			client.publishMessage(productId, equipmentId, Value);// 发送指令
			

			if (state.equals("1")) {// 如果设备开启记录开启时间
				Date now = new Date();
				timeDelay.setStartTime(String.valueOf(now.getTime()));
			} else {
				timeDelay.setStartTime("null");
			}

			timeDelay.setTimeDelayId(timeDelayId);
			timeDelay.setEquipmentId(equipmentId);
			timeDelay.setUserId(userId);
			timeDelay.setProductId(productId);
			timeDelay.setRepeatTimes(repeatTimes);
			timeDelay.setRepeatTimeStr(repeatTimes);
			timeDelay.setState(state);
			timeDelay.setOpenTime(openTime);
			timeDelay.setCloseTime(closeTime);
			timeDelay.setOpenIf(openIf);
			timeDelay.setCloseIf(closeIf);
			timeDelay.setOrderOnOff(orderOnOff);

			if (openIf.equals("1") && closeIf.equals("1")) {
				timeDelay.setExecuteNow(orderOnOff);
			} else {
				if (openIf.equals("2")) {
					timeDelay.setExecuteNow("2");
				}
				if (closeIf.equals("2")) {
					timeDelay.setExecuteNow("1");
				}
			}

			timing = timingService.selectTimingByDelayId(timeDelayId);
			String timeStr = changTime(openTime, closeTime, orderOnOff, openIf,
					closeIf);
			// 修改定时
			System.out.println("timeStr===============" + timeStr);
			String[] timeStrs = timeStr.split(",");
			String beginTime = timeStrs[0];
			String endTime = timeStrs[1];

			timing.setUserId(userId);
			timing.setEquipmentId(equipmentId);
			timing.setProductId(productId);
			timing.setState(state);
			timing.setColseIf(closeIf);
			timing.setOpenIf(openIf);
			timing.setBeginTime(beginTime);
			timing.setEndTime(endTime);
			timing.setTimeDelayId(timeDelayId);
			timing.setRepeatTime(repeatTimes);
			timing.setTimeDelayId(timeDelayId);
			timingService.updateTiming(timing);
			// 修改定时结束

			timeDelayService.updateTimeDelay(timeDelay);
			// perform();
			json.put("code", 200);
			json.put("Message", "更新成功！");
		}
		out.print(json);
	}

	/**
	 *@author huangqin 删除延时 Jun 23, 2019
	 */
	public void deleteTimeDelay() throws IOException {
		JSONObject json = new JSONObject();
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		int timeDelayId = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("timeDelayId"));
		TimeDelay timeDelay = new TimeDelay();
		timeDelay=timeDelayService.selectTimeDelay(timeDelayId);
		String productId=timeDelay.getProductId();
		String equipmentId=timeDelay.getEquipmentId();
		
		ClientMQTT client = new ClientMQTT();
		 // client.startInstruction(productId,equipmentId);//连接服务器
		client.publishMessage(productId, equipmentId, "WY+DelayTime=del");// 发送指令
		timeDelayService.deleteTimeDelay(timeDelayId);
		
		
		json.put("code", 200);
		json.put("Message", "删除成功！");
		out.print(json);
	}

	/**
	 *@author huangqin 根据id查找延时 Jun 23, 2019
	 */
	public void selectTimeDelay() throws IOException {
		JSONObject json = new JSONObject();
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();

		TimeDelay timeDelay = new TimeDelay();
		JSONArray jsonObject = null;
		int timeDelayId = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("timeDelayId"));
		timeDelay = timeDelayService.selectTimeDelay(timeDelayId);
		jsonObject = JSONArray.fromObject(timeDelay);// 转json
		json.put("code", 200);
		json.put("Message", "success");
		json.put("data", jsonObject);
		out.print(json);
	}

	public TimeDelayService getTimeDelayService() {
		return timeDelayService;
	}

	public void setTimeDelayService(TimeDelayService timeDelayService) {
		this.timeDelayService = timeDelayService;
	}

	public TimingService getTimingService() {
		return timingService;
	}

	public void setTimingService(TimingService timingService) {
		this.timingService = timingService;
	}

}
