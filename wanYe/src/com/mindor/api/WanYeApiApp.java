package com.mindor.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

import com.mindor.entity.AppVersion;
import com.mindor.serivce.AppService;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import net.sf.json.JSONArray;

public class WanYeApiApp {

	private AppService appService;
	private int code;
	private String MessageS;

	public AppService getAppService() {
		return appService;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	public void selectVersion() throws IOException {

		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		JsonConfig jsonConfig = new JsonConfig();
		AppVersion versions = new AppVersion();
		int AppVersionId;
		String system = ServletActionContext.getRequest()
				.getParameter("system");// app系统 android
		System.out.println("system==" + system);
		JSONObject json = new JSONObject();
		JSONArray jsonObject = null;
		if (system.equals("android")) {
			AppVersionId = 1;
			code = 200;
			MessageS = "success";
			versions = appService.selectVersionsById(AppVersionId);
			jsonObject = JSONArray.fromObject(versions, jsonConfig);
			json.put("data", jsonObject);
		} else if (system.equals("ios")) {
			AppVersionId = 2;
			code = 200;
			MessageS = "success";
			versions = appService.selectVersionsById(AppVersionId);
			jsonObject = JSONArray.fromObject(versions, jsonConfig);
			json.put("data", jsonObject);
		} else {
			code = 500;
			MessageS = "failure";
		}
		json.put("code", code);
		json.put("Message", MessageS);
		out.print(json);
		out.flush();
		out.close();
	}
	
	
	public String mall(){
		return "mall";
	}

	public String updatejson(){
		return "updatejson";
	}

	public void updateStr() throws IOException {
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		String jsonStr = ServletActionContext.getRequest().getParameter(
				"jsonStr");
		appService.updateWjMinstore(jsonStr);
	}
	
	public void wjMinstore() throws IOException {
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		JsonConfig jsonConfig = new JsonConfig();
		JSONArray jsonObject = null;
		JSONObject job = null;
		JSONObject json = new JSONObject();
		AppVersion versions = new AppVersion();
		code = 200;
		MessageS = "success";
		versions = appService.selectVersionsById(2);
		if (versions.getWjMinstore() != null && versions.getWjMinstore() != ""
				&& versions.getWjMinstore().length() > 0) {
			jsonObject = JSONArray.fromObject(versions.getWjMinstore(),
					jsonConfig);
			job = jsonObject.getJSONObject(0);
		}
		json.put("data", job);
		json.put("code", code);
		json.put("Message", MessageS);
		out.print(json);
		out.flush();
		out.close();

	}

}
