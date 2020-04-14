package com.mindor.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.mindor.entity.EquipmentInfo;
import com.mindor.entity.EquipmentModel;
import com.mindor.serivce.EquipmentModelService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class WanYeApiEquipmentModel extends ActionSupport {

	private EquipmentModelService equipmentModelService;
	int code;
	String Message;
	private static SessionFactory factory;

	/**
	 *@author huangqin 模式加载 Apr 1, 2019
	 */
	@SuppressWarnings("rawtypes")
	public void equipmentModelList() throws IOException {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "openUser" });// 在这里添加要过滤的属性名
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONArray jsonObject = null;
		JSONObject json = new JSONObject();

		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();

		String userId = ServletActionContext.getRequest()
				.getParameter("userId");
		List<Map> equipmentModel = equipmentModelService
				.equipmentModelList(userId);

		jsonObject = JSONArray.fromObject(equipmentModel, jsonConfig);// 转json

		code = 200;
		Message = "SUCCESS";
		json.put("code", code);
		json.put("Message", Message);
		json.put("data", jsonObject);
		out.print(json);
		out.flush();
		out.close();
	}

	/**
	 *@author huangqin 根据模式id查询 Apr 1, 2019
	 */
	public void equipmentModelListById() throws IOException {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "openUser" });// 在这里添加要过滤的属性名
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONArray jsonObject = null;
		JSONObject json = new JSONObject();

		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		String equipmentModelId = ServletActionContext.getRequest()
				.getParameter("equipmentModelId");
		String userId = ServletActionContext.getRequest()
				.getParameter("userId");
		EquipmentModel equipmentModel = equipmentModelService
				.equipmentModelListById(equipmentModelId, userId);

		if (equipmentModel.getEquipmentModelId() != null) {
			code = 200;
			Message = "SUCCESS";
		} else {
			code = 500;
			Message = "failure";
		}

		jsonObject = JSONArray.fromObject(equipmentModel, jsonConfig);// 转json

		json.put("code", code);
		json.put("Message", Message);
		json.put("data", jsonObject);
		out.print(json);
		out.flush();
		out.close();

	}

	/**
	 *@author huangqin 更新模式 Apr 1, 2019
	 */
	public void updateEquipmentModel() throws IOException {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		JSONObject json = new JSONObject();

		String equipmentModelId = ServletActionContext.getRequest()
				.getParameter("equipmentModelId");
		String equipmentModelName = ServletActionContext.getRequest()
				.getParameter("equipmentModelName");
		String equipmentModelIcon = ServletActionContext.getRequest()
				.getParameter("equipmentModelIcon");
		// String
		// equipmentModelTime=ServletActionContext.getRequest().getParameter("equipmentModelTime");
		String equipmentModelBeginTime = ServletActionContext.getRequest()
				.getParameter("equipmentModelBeginTime");
		String equipmentModelEndTime = ServletActionContext.getRequest()
				.getParameter("equipmentModelEndTime");

		String beginIf = ServletActionContext.getRequest().getParameter(
				"beginIf");
		String endIf = ServletActionContext.getRequest().getParameter("endIf");
		String orderOnOff = ServletActionContext.getRequest().getParameter(
				"orderOnOff");

		String equipmentModelRepeat = ServletActionContext.getRequest()
				.getParameter("equipmentModelRepeat");
		String equipmentList = ServletActionContext.getRequest().getParameter(
				"equipmentList");
		String stateList = ServletActionContext.getRequest().getParameter(
				"stateList");// 设备状态集合
		String onOff = ServletActionContext.getRequest().getParameter("onOff");

		System.out.println("equipmentList====================" + equipmentList);

		equipmentModelService.updateEquipmentModel(beginIf, endIf, orderOnOff,
				equipmentModelId, equipmentModelName, equipmentModelIcon,
				equipmentModelBeginTime, equipmentModelEndTime,
				equipmentModelRepeat, equipmentList, onOff, stateList);

		code = 200;
		Message = "更新模式成功！";
		json.put("code", code);
		json.put("Message", Message);
		out.print(json);
		out.flush();
		out.close();
	}

	/**
	 *@author huangqin 添加模式 Apr 1, 2019
	 */
	public void addEquipmentModel() throws IOException {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		int size;
		JSONObject json = new JSONObject();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String creationDate = df.format(new Date());// new
													// Date()为获取当前系统时间，也可使用当前时间戳

		EquipmentModel equipmentModel = new EquipmentModel();

		String userId = ServletActionContext.getRequest()
				.getParameter("userId");
		String EquipmentModelId = setModelId();

		String equipmentModelName = ServletActionContext.getRequest()
				.getParameter("equipmentModelName");
		String equipmentModelIcon = ServletActionContext.getRequest()
				.getParameter("equipmentModelIcon");
		String equipmentModelRepeat = ServletActionContext.getRequest()
				.getParameter("equipmentModelRepeat");
		// String
		// equipmentModelTime=ServletActionContext.getRequest().getParameter("equipmentModelTime");
		String equipmentModelBeginTime = ServletActionContext.getRequest()
				.getParameter("equipmentModelBeginTime");
		String equipmentModelEndTime = ServletActionContext.getRequest()
				.getParameter("equipmentModelEndTime");
		String beginIf = ServletActionContext.getRequest().getParameter(
				"beginIf");
		String endIf = ServletActionContext.getRequest().getParameter("endIf");
		String orderOnOff = ServletActionContext.getRequest().getParameter(
				"orderOnOff");
		String equipmentList = ServletActionContext.getRequest().getParameter(
				"equipmentList");// 设备集合
		String stateList = ServletActionContext.getRequest().getParameter(
				"stateList");// 设备状态集合

		System.out.println("name-----=========-=-=-=" + equipmentModelName);

		HashMap<String, String> hashMap = new HashMap<String, String>();
		if(userId!=null&&userId!=" ") {
			
		equipmentModel.setEquipmentModelId(EquipmentModelId);
		equipmentModel.setEquipmentModelName(equipmentModelName);
		equipmentModel.setEquipmentModelIcon(equipmentModelIcon);
		equipmentModel.setEquipmentModelRepeat(equipmentModelRepeat);
		// equipmentModel.setEquipmentModelTime(equipmentModelTime);
		equipmentModel.setEquipmentModelBeginTime(equipmentModelBeginTime);
		equipmentModel.setEquipmentModelEndTime(equipmentModelEndTime);
		equipmentModel.setBeginIf(beginIf);
		equipmentModel.setEndIf(endIf);
		equipmentModel.setOrderOnOff(orderOnOff);
		equipmentModel.setEquipmentList(equipmentList);
		equipmentModel.setStateList(stateList);
		equipmentModel.setOver("false");// 是否执行完默认设置未执行
		equipmentModel.setOnOff("1");

		equipmentModel.setCreationDate(creationDate);

		size = equipmentModelService.addEquipmentModel(userId, equipmentModel);
		if (size == 1) {
			hashMap.put("equipmentModelId", EquipmentModelId);
			hashMap.put("equipmentModelName", equipmentModelName);
			hashMap.put("equipmentModelIcon", equipmentModelIcon);
			hashMap.put("equipmentModelRepeat", equipmentModelRepeat);
			hashMap.put("equipmentList", equipmentList);
			hashMap.put("stateList", stateList);
			hashMap.put("equipmentModelBeginTime", equipmentModelBeginTime);
			hashMap.put("equipmentModelEndTime", equipmentModelEndTime);

			hashMap.put("beginIf", beginIf);
			hashMap.put("endIf", endIf);
			hashMap.put("orderOnOff", orderOnOff);

			// hashMap.put("equipmentModelTime", equipmentModelTime);

			hashMap.put("OnOff", "1");
			code = 200;
			Message = "添加模式成功！";
			json.put("code", code);
			json.put("Message", Message);
			json.put("data", hashMap);
		} else {
			code = 500;
			Message = "添加模式失败！";
			json.put("code", code);
			json.put("Message", Message);
		}
		}else {
			code = 500;
			Message = "未登录状态！";
			json.put("code", code);
			json.put("Message", Message);
		}
		out.print(json);
		out.flush();
		out.close();
	}

	/**
	 *@author huangqin 查询设备 Apr 2, 2019
	 */
	@SuppressWarnings("unchecked")
	public void selectEquipment() throws IOException {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		JSONArray jsonObject = null;
		JSONObject json = new JSONObject();
		try {
			Configuration cfg = new Configuration();
			factory = cfg.configure().buildSessionFactory();

		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		Session session = factory.openSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			// 查询该用户下所有设备
			String userId = ServletActionContext.getRequest().getParameter(
					"userId");
			String sql = "SELECT b.equipmentId,d.equipmentNote,a.productIcon,d.specificEquipmentLabel FROM product a,equipment b,openuser c,intermediate d WHERE  a.productId=d.productId AND b.equipmentId=d.equipmentId AND c.userId=d.userId AND c.userId='"
					+ userId + "' AND d.`productId`!='kqy001'";
			List<Object> equipmentList = session.createSQLQuery(sql).list();

			EquipmentInfo equipmentInfo = null;// 创建实体类
			List<EquipmentInfo> mylist = new LinkedList<EquipmentInfo>();

			Iterator<Object> it = equipmentList.iterator();
			while (it.hasNext()) {
				equipmentInfo = new EquipmentInfo();
				Object[] objs = (Object[]) it.next();
				equipmentInfo.setEquipmentId(String.valueOf(objs[0]));
				equipmentInfo.setEquipmentNote(String.valueOf(objs[1]));
				equipmentInfo.setProductIcon(String.valueOf(objs[2]));
				equipmentInfo
						.setSpecificEquipmentLabel(String.valueOf(objs[3]));
				mylist.add(equipmentInfo);
			}

			jsonObject = JSONArray.fromObject(mylist, jsonConfig);// 转json
			code = 200;
			Message = "sussess";
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
	 *@author huangqin 删除模式 Apr 2, 2019
	 */
	public void deleteEquipmentModel() throws IOException {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		JSONObject json = new JSONObject();
		String equipmentModelId = ServletActionContext.getRequest()
				.getParameter("equipmentModelId");
		int size = equipmentModelService.deleteEquipmentModel(equipmentModelId);
		if (size == 0) {
			code = 500;
			Message = "删除失败！";
		} else {
			code = 200;
			Message = "删除成功！";

		}
		json.put("code", code);
		json.put("Message", Message);
		out.print(json);
		out.flush();
		out.close();

	}

	/**
	 *@author huangqin 设置模式id Apr 2, 2019
	 */
	@SuppressWarnings("rawtypes")
	public String setModelId() {
		String equipmentModelId = null;

		try {
			Configuration cfg = new Configuration();
			factory = cfg.configure().buildSessionFactory();

		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query q = session
					.createQuery("SELECT a.equipmentModelId FROM EquipmentModel a ORDER BY a.creationDate DESC");
			q.setMaxResults(1);
			List a = q.list();
			if (a.size() == 0 || a.get(0) == null) {
				equipmentModelId = "model10001";
			} else {
				String b = a.toString();

				b = b.substring(6, b.indexOf("]"));
				int newId = Integer.parseInt(b) + 1;
				equipmentModelId = "model" + newId;

			}

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
		return equipmentModelId;

	}

	/**
	 *@author huangqin 删除模式下设备 Apr 4, 2019
	 */
	public void deleteEquipment() throws IOException {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		JSONObject json = new JSONObject();
		String equipmentModelId = ServletActionContext.getRequest()
				.getParameter("equipmentModelId");
		String equipmentId = ServletActionContext.getRequest().getParameter(
				"equipmentId");
		int size = equipmentModelService.deleteEquipment(equipmentModelId,
				equipmentId);
		if (size == 0) {
			code = 500;
			Message = "删除设备失败！";
		} else {
			code = 200;
			Message = "删除设备成功！";

		}
		json.put("code", code);
		json.put("Message", Message);
		out.print(json);
		out.flush();
		out.close();
	}

	public EquipmentModelService getEquipmentModelService() {
		return equipmentModelService;
	}

	public void setEquipmentModelService(
			EquipmentModelService equipmentModelService) {
		this.equipmentModelService = equipmentModelService;
	}

}
