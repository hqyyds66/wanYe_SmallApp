package com.mindor.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.mindor.entity.InfraredBin;
import com.mindor.entity.Intermediate;
import com.mindor.serivce.EquipmentService;
import com.mindor.serivce.InfraredService;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAilabsIotDeviceListUpdateNotifyRequest;
import com.taobao.api.response.AlibabaAilabsIotDeviceListUpdateNotifyResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WanYeApiInfrared {

	private InfraredService infraredService;
	private EquipmentService equipmentService;
	public EquipmentService getEquipmentService() {
		return equipmentService;
	}

	public void setEquipmentService(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}

	int code;
	String Message;

	public InfraredService getInfraredService() {
		return infraredService;
	}

	public void setInfraredService(InfraredService infraredService) {
		this.infraredService = infraredService;
	}

	public void selectInfrared() throws IOException {

		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		String userId = ServletActionContext.getRequest().getParameter("userId");
		JSONObject json = new JSONObject();
		JSONArray jsonObject = null;
		if (userId != "" && userId != null) {
			code = 200;
			Message = "success";
			List<InfraredBin> infraredBins = infraredService.selectInfrared(userId);

			for (int i = 0; i < infraredBins.size(); i++) {
//				infraredBins.get(i).setMac(infraredService.equipmentMac(infraredBins.get(i).getEquipmentId()));
				infraredBins.get(i).setEquipmentState(infraredService.state(infraredBins.get(i).getEquipmentId()));
				infraredBins.get(i).setEquipmentNote(infraredService.equipmentNote(userId,infraredBins.get(i).getEquipmentId()));
			}
			jsonObject = JSONArray.fromObject(infraredBins);

			json.put("code", code);
			json.put("Message", Message);
			json.put("data", jsonObject);
		} else {
			code = 500;
			Message = "用户id为空！";
			json.put("code", code);
			json.put("Message", Message);
		}

		out.print(json);
		out.flush();
		out.close();
	}

	public void addInfrared() throws IOException {

		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		JSONObject json = new JSONObject();
		String userId = ServletActionContext.getRequest().getParameter("userId");
		String equipmentId = ServletActionContext.getRequest().getParameter("equipmentId");
		String nick = ServletActionContext.getRequest().getParameter("nick");
		String device_id = ServletActionContext.getRequest().getParameter("device_id");
		String brand_id = ServletActionContext.getRequest().getParameter("brand_id");
		String kfid = ServletActionContext.getRequest().getParameter("kfid");
		String bn = ServletActionContext.getRequest().getParameter("bn");

		InfraredBin infraredBin = new InfraredBin();
		infraredBin.setUserId(userId);
		infraredBin.setEquipmentId(equipmentId);
		infraredBin.setProductId(equipmentId.substring(0, 6));
		infraredBin.setNick(nick);
		infraredBin.setMac("df3c66db8eded811");
		infraredBin.setDevice_id(device_id);
		infraredBin.setBrand_id(brand_id);
		infraredBin.setKfid(kfid);
		infraredBin.setImage("http://112.74.48.180/wanYe/images/equipment/remote_control.png");
		infraredBin.setBn(bn);

		infraredService.addInfrared(infraredBin);

		code = 200;
		Message = "success";
		
		AliGenie(userId);

		json.put("code", code);
		json.put("Message", "新增成功！");

		out.print(json);
		out.flush();
		out.close();

	}

	public void delInfrared() throws IOException {

		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		JSONObject json = new JSONObject();
		String infraredBinId = ServletActionContext.getRequest().getParameter("infraredBinId");

		String userId=infraredService.userIdGet(Integer.parseInt(infraredBinId));
		infraredService.delInfrared(Integer.parseInt(infraredBinId));
		
		code = 200;
		Message = "success";
		
		AliGenie(userId);

		json.put("code", code);
		json.put("Message", "删除成功！");

		out.print(json);
		out.flush();
		out.close();
	}

	public void updateInfrared() throws IOException {
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		JSONObject json = new JSONObject();

		String infraredBinId = ServletActionContext.getRequest().getParameter("infraredBinId");
		String nick = ServletActionContext.getRequest().getParameter("nick");

		infraredService.updateInfrared(infraredBinId, nick);

		code = 200;
		Message = "success";

		json.put("code", code);
		json.put("Message", "更新成功！");

		out.print(json);
		out.flush();
		out.close();

	}
	
	public void selectInfrared_eq() throws IOException {
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		String userId = ServletActionContext.getRequest().getParameter("userId");
		JSONObject json = new JSONObject();
		JSONArray jsonObject = null;
		List<Intermediate> Intermediates = infraredService.selectInfrared_eq(userId);
		jsonObject = JSONArray.fromObject(Intermediates);
		code = 200;
		Message = "success";
		System.out.println("json==" + json);
		
		json.put("code", code);
		json.put("Message", Message);
		json.put("data", jsonObject);

		out.print(json);
		out.flush();
		out.close();
	}
	
	/**
	 *@author huangqin
	 *  设备删除，添加上报给天猫精灵
	 * 2020年1月20日
	 * @throws ApiException 
	 */
	public void AliGenie(String userId){
		System.out.println("用户id是："+userId);
		String phone=equipmentService.phoneGet(userId);
		String userToken=equipmentService.tokenGet(userId);
		
		System.out.println("根据用户id获取的Token是："+userToken);
		System.out.println("绑定的手机号是："+phone);
		
		if(userToken==null||userToken==""&&phone!=null||phone!="") {
			System.out.println("用户"+userId+"绑定了手机号");
			userToken=equipmentService.tokenGet(phone);
		}
		
		System.out.println("最终Token是："+userToken);
		
		try {
			String appkey="27803406";
			String secret="729ded12a4a9be618be61c1777ae6ec7";
			String url="http://gw.api.taobao.com/router/rest";
			TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
			AlibabaAilabsIotDeviceListUpdateNotifyRequest req = new AlibabaAilabsIotDeviceListUpdateNotifyRequest();
			req.setToken(userToken);
			req.setSkillId("39446");
			AlibabaAilabsIotDeviceListUpdateNotifyResponse rsp;
			rsp = client.execute(req);
			System.out.println(rsp.getBody());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
}
}
