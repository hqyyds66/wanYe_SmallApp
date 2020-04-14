package com.mindor.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.mindor.entity.Sensor;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WanYeApiHistorical {

	private SessionFactory sessionFactory;

	/**
	 *@author huangqin 加载历史数据 Apr 5, 2019
	 * @throws IOException
	 */
	@SuppressWarnings( { "unchecked", "rawtypes" })
	public void loadData() throws IOException {

		JSONObject json = new JSONObject();
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();

		try {
			Configuration cfg = new Configuration();
			sessionFactory = cfg.configure().buildSessionFactory();

		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String sql = null;
			JSONArray jsonObject = null;
			String beforeDateStr;
			String defaultStr = null;

			String equipmentId = ServletActionContext.getRequest()
					.getParameter("equipmentId");
			String sensorTable = ServletActionContext.getRequest()
					.getParameter("sensor"); // `co2_data``hcho_data``humidity_data``pm25_data``temperature``tvoc_data`
			String time = ServletActionContext.getRequest()
					.getParameter("time");// 时,天,周,月 hour,day,week,month

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
			Date now = new Date();
			Sensor sensor = null;// 创建实体类
			List<Sensor> mylist = new LinkedList<Sensor>();

			if (time.equals("hour")) {// 当前时间 一小时前的数据
				sql = "SELECT  AVG(a.value) FROM " + sensorTable
						+ " a where a.equipmentId='" + equipmentId
						+ "' and a.date>=DATE_SUB(NOW(), INTERVAL 60 MINUTE)";
			}
			if (time.equals("day")) {// 当前时间 一天前的数据
				sql = "SELECT AVG(a.value) FROM " + sensorTable
						+ " a where a.equipmentId='" + equipmentId
						+ "' and a.date>=DATE_SUB(NOW(), INTERVAL 24 HOUR)";
			}
			if (time.equals("week")) {// 当前时间 一周前的数据
				sql = "SELECT AVG(a.value) FROM " + sensorTable
						+ " a where a.equipmentId='" + equipmentId
						+ "' and a.date>DATE_SUB(NOW(), INTERVAL 7 day)";
			}
			if (time.equals("month")) {// 当前时间 一月前的数据
				sql = "SELECT AVG(a.value) FROM " + sensorTable
						+ " a where a.equipmentId='" + equipmentId
						+ "' and a.date>=DATE_SUB(NOW(), INTERVAL 30 day)";
			}

			List listStr01 = session.createSQLQuery(sql).list();
			double aa;
			if (listStr01.size() > 0 && listStr01.get(0) != null) {
				aa = stringToDouble(listStr01.get(0).toString());
			} else {
				aa = 0;
			}

			if (time.equals("hour")) {
				long time01 = 61 * 60 * 1000;// 前一小时
				Long nowDateStr = now.getTime() - time01;// 前一小时

				long time02 = 60 * 1000;// 设置隔多久时间
				for (int i = 0; i <= 60; i++) {// 循环60遍获取前一分钟时间
					Date beforeDate = new Date(nowDateStr + time02);// 前一分钟
					beforeDateStr = sdf.format(beforeDate);// 格式化前一分钟时间 去除秒
					sql = "SELECT a.date,a.value FROM " + sensorTable
							+ " a where a.equipmentId='" + equipmentId
							+ "' and a.date='" + beforeDateStr + "'";
					List<Object> listStr = session.createSQLQuery(sql).list();
					if (listStr.size() > 0) {

						Iterator<Object> it = listStr.iterator();
						while (it.hasNext()) {
							sensor = new Sensor();
							Object[] objs = (Object[]) it.next();

							if (isNumeric(String.valueOf(objs[1]))
									&& String.valueOf(objs[1]).equals("0") == false
									&& String.valueOf(objs[1]).equals("0.00") == false) {
								sensor.setValue(String.valueOf(objs[1]));
								defaultStr = String.valueOf(objs[1]);

							} else {
								sensor.setValue(String.valueOf(defaultStr));
							}

							sensor.setDate(String.valueOf(objs[0]));

							mylist.add(sensor);
							nowDateStr = beforeDate.getTime();
						}
					} else {
						sensor = new Sensor();
						sensor.setDate(String.valueOf(beforeDateStr));
						if (defaultStr == null) {
							sensor.setValue(String.valueOf(aa));
						} else {
							sensor.setValue(String.valueOf(defaultStr));
						}

						mylist.add(sensor);
						nowDateStr = beforeDate.getTime();
					}
				}

			}

			if (time.equals("day")) {
				long time01 = 25 * 60 * 60 * 1000;// 前一天
				Long nowDateStr = now.getTime() - time01;// 前一天

				long time02 = 60 * 60 * 1000;// 设置隔多久时间
				for (int i = 0; i <= 24; i++) {
					Date beforeDate = new Date(nowDateStr + time02);
					beforeDateStr = sdf.format(beforeDate);// 格式化前一分钟时间 去除秒
					sql = "SELECT a.date,a.value FROM " + sensorTable
							+ " a where a.equipmentId='" + equipmentId
							+ "' and a.date='" + beforeDateStr + "'";
					List<Object> listStr = session.createSQLQuery(sql).list();
					if (listStr.size() > 0) {

						Iterator<Object> it = listStr.iterator();
						while (it.hasNext()) {
							sensor = new Sensor();
							Object[] objs = (Object[]) it.next();
							if (isNumeric(String.valueOf(objs[1]))
									&& String.valueOf(objs[1]).equals("0") == false
									&& String.valueOf(objs[1]).equals("0.00") == false) {
								sensor.setValue(String.valueOf(objs[1]));
								defaultStr = String.valueOf(objs[1]);

							} else {
								sensor.setValue(String.valueOf(defaultStr));
							}

							sensor.setDate(String.valueOf(objs[0]));

							mylist.add(sensor);
							nowDateStr = beforeDate.getTime();
						}
					} else {
						sensor = new Sensor();

						sensor.setDate(String.valueOf(beforeDateStr));
						if (defaultStr == null) {
							sensor.setValue(String.valueOf(aa));
						} else {
							sensor.setValue(String.valueOf(defaultStr));
						}

						mylist.add(sensor);
						nowDateStr = beforeDate.getTime();
					}
				}

			}

			if (time.equals("week")) {
				long time01 = 7 * 24 * 60 * 60 * 1000;// 前一周
				Long nowDateStr = now.getTime() - time01;// 前一周

				long time02 = 24 * 60 * 60 * 1000;// 设置隔多久时间
				for (int i = 0; i < 7; i++) {
					Date beforeDate = new Date(nowDateStr + time02);
					beforeDateStr = sdf.format(beforeDate);// 格式化前一分钟时间 去除秒
					sql = "SELECT a.date,a.value FROM " + sensorTable
							+ " a where a.equipmentId='" + equipmentId
							+ "' and a.date='" + beforeDateStr + "'";
					List<Object> listStr = session.createSQLQuery(sql).list();
					if (listStr.size() > 0) {

						Iterator<Object> it = listStr.iterator();
						while (it.hasNext()) {
							sensor = new Sensor();
							Object[] objs = (Object[]) it.next();

							if (isNumeric(String.valueOf(objs[1]))
									&& String.valueOf(objs[1]).equals("0") == false
									&& String.valueOf(objs[1]).equals("0.00") == false) {
								sensor.setValue(String.valueOf(objs[1]));
								defaultStr = String.valueOf(objs[1]);

							} else {
								sensor.setValue(String.valueOf(defaultStr));
							}

							sensor.setDate(String.valueOf(objs[0]));

							mylist.add(sensor);
							nowDateStr = beforeDate.getTime();
						}
					} else {
						sensor = new Sensor();

						sensor.setDate(String.valueOf(beforeDateStr));
						if (defaultStr == null) {
							sensor.setValue(String.valueOf(aa));
						} else {
							sensor.setValue(String.valueOf(defaultStr));
						}

						mylist.add(sensor);
						nowDateStr = beforeDate.getTime();
					}
				}

			}

			if (time.equals("month")) {
				// long time01 = 24*60*60*1000;//前一周

				Date startDate = DateUtils.addDays(now, -31);
				Long nowDateStr = startDate.getTime();

				long time02 = 24 * 60 * 60 * 1000;// 设置隔多久时间
				for (int i = 0; i <= 30; i++) {
					Date beforeDate = new Date(nowDateStr + time02);
					beforeDateStr = sdf.format(beforeDate);// 格式化前一分钟时间 去除秒
					sql = "SELECT a.date,a.value FROM " + sensorTable
							+ " a where a.equipmentId='" + equipmentId
							+ "' and a.date='" + beforeDateStr + "'";
					List<Object> listStr = session.createSQLQuery(sql).list();
					if (listStr.size() > 0) {

						Iterator<Object> it = listStr.iterator();
						while (it.hasNext()) {
							sensor = new Sensor();
							Object[] objs = (Object[]) it.next();

							if (isNumeric(String.valueOf(objs[1]))
									&& String.valueOf(objs[1]).equals("0") == false
									&& String.valueOf(objs[1]).equals("0.00") == false) {
								sensor.setValue(String.valueOf(objs[1]));
								defaultStr = String.valueOf(objs[1]);

							} else {
								sensor.setValue(String.valueOf(defaultStr));
							}

							sensor.setDate(String.valueOf(objs[0]));

							mylist.add(sensor);
							nowDateStr = beforeDate.getTime();
						}
					} else {
						sensor = new Sensor();

						sensor.setDate(String.valueOf(beforeDateStr));
						if (defaultStr == null) {
							sensor.setValue(String.valueOf(aa));
						} else {
							sensor.setValue(String.valueOf(defaultStr));
						}

						mylist.add(sensor);
						nowDateStr = beforeDate.getTime();
					}
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
			System.gc();// 执行垃圾回收
			tx.commit();

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.getSessionFactory().close();
			session.close();
			System.gc();// 执行垃圾回收
		}

	}

	/**
	 *@author huangqin 判断是否为数字 May 24, 2019
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		if (str.indexOf(".") > 0) {// 判断是否有小数点
			if (str.indexOf(".") == str.lastIndexOf(".")
					&& str.split("\\.").length == 2) { // 判断是否只有一个小数点
				return pattern.matcher(str.replace(".", "")).matches();
			} else {
				return false;
			}
		} else {
			return pattern.matcher(str).matches();
		}
	}

	public static double stringToDouble(String a) {
		double b = Double.valueOf(a);
		DecimalFormat df = new DecimalFormat("#.00");// 此为保留1位小数，若想保留2位小数，则填写#.00
														// ，以此类推
		String temp = df.format(b);
		b = Double.valueOf(temp);
		return b;
	}

}
