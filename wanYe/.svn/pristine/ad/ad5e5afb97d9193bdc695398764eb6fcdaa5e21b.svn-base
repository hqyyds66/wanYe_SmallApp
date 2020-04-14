package com.mindor.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.mindor.entity.MessageManage;
import com.mindor.serivce.MessageManageService;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

@SuppressWarnings("serial")
public class WanYeApiMessage extends ActionSupport {

	public void setMessageManageService(
			MessageManageService messageManageService) {
		this.messageManageService = messageManageService;
	}

	private MessageManageService messageManageService;

	/**
	 *@author huangqin 查询信息 2019年7月30日
	 * @throws IOException
	 */
	public void selectMessage() throws IOException {
		JSONObject json = new JSONObject();
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();

		String userId = ServletActionContext.getRequest()
				.getParameter("userId");
		List<MessageManage> messageManages = messageManageService
				.selectMessage(userId);

		System.out.println("messageManages========" + messageManages);

		int code = 200;
		String Message = "SUCCESS";
		json.put("code", code);
		json.put("Message", Message);
		json.put("data", messageManages);
		out.print(json);
		out.flush();
		out.close();

	}

	/**
	 *@author huangqin 删除信息 2019年7月30日
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void delMessage() throws IOException {

		JSONObject json = new JSONObject();
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();

		String messageManageId = ServletActionContext.getRequest()
				.getParameter("messageId");

		String[] messageManageIds = messageManageId.split(",");
		List idStrs = Arrays.asList(messageManageIds);
		List ids = new ArrayList(idStrs);

		for (int i = 0; i < ids.size(); i++) {
			messageManageService.delMessage(ids.get(i).toString());
		}

		int code = 200;
		String Message = "删除成功！";
		json.put("code", code);
		json.put("Message", Message);
		out.print(json);
		out.flush();
		out.close();
	}

	/**
	 *@author huangqin 更新是否已读信息 2019年7月30日
	 * @throws IOException
	 */
	public void updMessage() throws IOException {

		JSONObject json = new JSONObject();
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();

		String messageManageId = ServletActionContext.getRequest()
				.getParameter("messageId");
		// String
		// readIf=ServletActionContext.getRequest().getParameter("readIf");

		messageManageService.updorderIf(messageManageId, "1");

		int code = 200;
		String Message = "更新成功！";
		json.put("code", code);
		json.put("Message", Message);
		out.print(json);
		out.flush();
		out.close();

	}

	/**
	 *@author huangqin 更新分享操作状态 2019年7月31日
	 */
	public void updOperation() throws IOException {

		JSONObject json = new JSONObject();
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();

		String messageManageId = ServletActionContext.getRequest()
				.getParameter("messageId");

		messageManageService.updOperation(messageManageId);

		int code = 200;
		String Message = "更新成功！";
		json.put("code", code);
		json.put("Message", Message);
		out.print(json);
		out.flush();
		out.close();

	}

}
