package com.mindor.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.mindor.entity.Sensor;
import com.mindor.serivce.PowerCountService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WanYeApiPowerCount {
	private PowerCountService powerCountService;

	public PowerCountService getPowerCountService() {
		return powerCountService;
	}

	public void setPowerCountService(PowerCountService powerCountService) {
		this.powerCountService = powerCountService;
	}

	/**
	 *@author huangqin 加载历史数据 2019年9月10日
	 * @throws IOException
	 */
	public void loadData() throws IOException {
		JSONObject json = new JSONObject();
		JSONArray jsonObject = null;
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();

		String equipmentId = ServletActionContext.getRequest().getParameter(
				"equipmentId");
		String time = ServletActionContext.getRequest().getParameter("time");// 天,周,月
																				// day,week,month

		SimpleDateFormat sdfs = new SimpleDateFormat("YY-MM-dd");// 设置日期格式
		SimpleDateFormat sdfshour = new SimpleDateFormat("HH");// 设置日期格式
		SimpleDateFormat sdfsyear = new SimpleDateFormat("YY");// 设置日期格式

		String timeStr = time;
		Date now = new Date();
		int hour = Integer.parseInt(sdfshour.format(now));
		int day = now.getDate();
		int month = now.getMonth() + 1;
		int year = Integer.parseInt(sdfsyear.format(now));

		System.out.println("年：" + year + "月：" + month + "日：" + day);

		List<Sensor> mylist = new LinkedList<Sensor>();

		if (time.equals("day")) {
			for (int i = 0; i <= 24; i++) {
				// time=sdfs.format(now)+" "+hour+":00:00";
				String monthStr = appendZero(String.valueOf(month));
				String dayStr = appendZero(String.valueOf(day));
				String hourStr = appendZero(String.valueOf(hour));
				time = year + "-" + monthStr + "-" + dayStr + " " + hourStr
						+ ":00:00";

				System.out.println("time====" + time);
				Sensor sensor = new Sensor();// 创建实体类
				String power = powerCountService.powerList(equipmentId, time,
						timeStr);
				sensor.setDate(time);
				if (power == null) {
					sensor.setValue("0.0000");
				} else {
					// power=power.substring(1, power.indexOf("]"));
					sensor.setValue(power);
				}
				mylist.add(sensor);
				hour = hour - 1;
				if (hour == 0) {
					hour = 24;
					day = day - 1;
				}
			}
		}

		if (time.equals("week")) {
			for (int i = 0; i <= 7; i++) {
				String monthStr = appendZero(String.valueOf(month));
				String dayStr = appendZero(String.valueOf(day));
				String hourStr = appendZero(String.valueOf(hour));
				time = year + "-" + monthStr + "-" + dayStr;
				// System.out.println("time===="+time);
				Sensor sensor = new Sensor();// 创建实体类
				String power = powerCountService.powerList(equipmentId, time,
						timeStr);
				sensor.setDate(time + " 23:59:59");
				System.out.println("power==" + power);
				if (power == null) {
					sensor.setValue("0.0000");
				} else {
					// power=power.substring(1, power.indexOf("]"));
					sensor.setValue(power);
				}
				mylist.add(sensor);
				day = day - 1;
				if (day == 00) {
					month = month - 1;
					if(month==0) {
						month=12;
						year = year - 1;
					}
					day = dateStr(month);
				}
				;
				
			}
		}

		if (time.equals("month")) {
			for (int i = 0; i <= 30; i++) {
				String monthStr = appendZero(String.valueOf(month));
				String dayStr = appendZero(String.valueOf(day));
				time = year + "-" + monthStr + "-" + dayStr;
				Sensor sensor = new Sensor();// 创建实体类
				String power = powerCountService.powerList(equipmentId, time,
						timeStr);
				sensor.setDate(time + " 23:59:59");
				if (power == null) {
					sensor.setValue("0.0000");
				} else {
					sensor.setValue(power);
				}
				mylist.add(sensor);
				day = day - 1;
				if (day == 0) {
					month = month - 1;
					if(month==0) {
						month=12;
						year = year - 1;
					}
					day = dateStr(month);
				}
				;
			}
		}

		jsonObject = JSONArray.fromObject(mylist);// 转json
		int code = 200;
		String Message = "SUCCESS";
		json.put("code", code);
		json.put("Message", Message);
		json.put("data", jsonObject);
		out.print(json);
		out.flush();
		out.close();

	}

	public int dateStr(int date) {
		Date now = new Date();
		int year = now.getYear();
		int str = 0;
		if (date == 1 || date == 3 || date == 5 || date == 7 || date == 8
				|| date == 10 || date == 12) {
			str = 31;
		}

		if (date == 4 || date == 6 || date == 9 || date == 11) {
			str = 30;
		}
		if (date == 2) {
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
				str = 29;
			} else {
				str = 28;
			}
		}
		return str;
	};

	public String appendZero(String obj) {
		if (Integer.parseInt(obj) < 10) {
			return "0" + obj;
		} else {
			return obj;
		}
	}

}
