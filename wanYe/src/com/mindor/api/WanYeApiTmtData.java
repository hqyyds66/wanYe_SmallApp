package com.mindor.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.mindor.entity.Sensor;
import com.mindor.entity.TmtData;
import com.mindor.serivce.TmtDataService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WanYeApiTmtData {
	
	private TmtDataService tmtDataService;
	
	/**
	 *@author huangqin
	 *  添加测温仪历史数据
	 * 2020年3月3日
	 * @throws IOException 
	 */
	public void addTmtdata() throws IOException {
		JSONObject json = new JSONObject();
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		
		String equipmentId = ServletActionContext.getRequest().getParameter(
				"equipmentId");
		String userId = ServletActionContext.getRequest()
				.getParameter("userId");
		String temp=ServletActionContext.getRequest()
				.getParameter("temp");
		String humOrThings=ServletActionContext.getRequest()
				.getParameter("humOrThings");
		
		TmtData tmtData=new TmtData();
		
		//获取时间
		Calendar c = Calendar.getInstance();
		
		int year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH); 
		int date = c.get(Calendar.DATE); 
		int hour = c.get(Calendar.HOUR_OF_DAY); 
		int minute = c.get(Calendar.MINUTE); 
		
		month=month+1;
		
		String monthStr=String.valueOf(month);
		String dateStr=String.valueOf(date);
		
		if(monthStr.length()<2) {
			monthStr="0"+monthStr;
		}
		
		if(dateStr.length()<2) {
			dateStr="0"+dateStr;
		}
		
		String yearMonth=year+"年"+monthStr+"月"+dateStr+"日";
		String hourMinute=String.format("%02d:%02d", hour, minute);
		
		String dataTime=year+"-"+monthStr+"-"+dateStr+" "+hourMinute;
		
		tmtData.setUserId(userId);
		tmtData.setEquipmentId(equipmentId);
		tmtData.setYearMonth(yearMonth);
		tmtData.setHourMinute(hourMinute);
		tmtData.setTemp(temp);
		tmtData.setHumOrThings(humOrThings);
		tmtData.setDataTime(dataTime);
		
		tmtDataService.addTmtdata(tmtData);
		
		json.put("code", 200);
		json.put("Message", "添加成功！");
		
		out.print(json);
	}
	
	public TmtDataService getTmtDataService() {
		return tmtDataService;
	}

	public void setTmtDataService(TmtDataService tmtDataService) {
		this.tmtDataService = tmtDataService;
	}

	/**
	 *@author huangqin
	 * 查找用户下所有的历史数据
	 * 2020年3月3日
	 * @throws IOException 
	 */
	public void selectDataByUser() throws IOException {
		JSONObject json = new JSONObject();
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		
		String userId = ServletActionContext.getRequest()
				.getParameter("userId");
		String humOrThings=ServletActionContext.getRequest()
				.getParameter("humOrThings");
		List<TmtData> tmtDatas=tmtDataService.selectDataByUser(userId,humOrThings);
		
		
		JSONArray jsonObject = null;
		
		jsonObject = JSONArray.fromObject(tmtDatas);// 转json
		json.put("code", 200);
		json.put("Message", "success");
		json.put("data", jsonObject);
		out.print(json);
		
	}
	
	/**
	 *@author huangqin
	 *  查找用户下 某个设备的历史数据
	 * 2020年3月3日
	 * @throws IOException 
	 */
	public void selectDataByUserEq() throws IOException {
		JSONObject json = new JSONObject();
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		
		String userId = ServletActionContext.getRequest()
				.getParameter("userId");
		String equipmentId= ServletActionContext.getRequest()
				.getParameter("equipmentId");
		String humOrThings=ServletActionContext.getRequest()
				.getParameter("humOrThings");
		List<TmtData> tmtDatas=tmtDataService.selectDataByUserEq(userId,equipmentId,humOrThings);
		
		JSONArray jsonObject = null;
		
		jsonObject = JSONArray.fromObject(tmtDatas);// 转json
		json.put("code", 200);
		json.put("Message", "success");
		json.put("data", jsonObject);
		out.print(json);
			
		}
	
	
	/**
	 *@author huangqin
	 *  查询历史体温 每天的平均值
	 * 2020年3月23日
	 * @throws IOException 
	 */
	public void  selectHisHum() throws IOException {
		
		JSONObject json = new JSONObject();
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		
		String userId = ServletActionContext.getRequest()
				.getParameter("userId");
		String equipmentId= ServletActionContext.getRequest()
				.getParameter("equipmentId");
		
		SimpleDateFormat sdfs = new SimpleDateFormat("YY-MM-dd");// 设置日期格式
		SimpleDateFormat sdfshour = new SimpleDateFormat("HH");// 设置日期格式
		SimpleDateFormat sdfsyear = new SimpleDateFormat("YYYY");// 设置日期格式

		Date now = new Date();
		int hour = Integer.parseInt(sdfshour.format(now));
		int day = now.getDate();
		int month = now.getMonth() + 1;
		int year = Integer.parseInt(sdfsyear.format(now));

		System.out.println("年：" + year + "月：" + month + "日：" + day);
		
		List<Sensor> mylist = new LinkedList<Sensor>();
		
		
		for(int i=0;i<30;i++) {
			
			String monthStr = appendZero(String.valueOf(month));
			String dayStr = appendZero(String.valueOf(day));
			String yearMonth = year + "年" + monthStr + "月" + dayStr+"日";
			String yearMonthStr=year + "-" + monthStr + "-" + dayStr;
			Sensor sensor = new Sensor();// 创建实体类
			String value = tmtDataService.selectHisHum(userId, equipmentId, yearMonth);
			sensor.setDate(yearMonthStr);
			System.out.println(value);
			if (value == null) {
				sensor.setValue("0.0");
			} else {
				sensor.setValue(value);
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
		}
		
		JSONArray jsonObject = null;
		
		jsonObject = JSONArray.fromObject(mylist);// 转json
		
		jsonObject = JSONArray.fromObject(jsonObject);// 转json
		json.put("code", 200);
		json.put("Message", "success");
		json.put("data", jsonObject);
		out.print(json);
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
