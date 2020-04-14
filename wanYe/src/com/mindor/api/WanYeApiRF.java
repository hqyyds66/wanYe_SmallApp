package com.mindor.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.mindor.entity.Intermediate;
import com.mindor.entity.RF;
import com.mindor.serivce.RFService;
import com.mindor.util.RedisUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

public class WanYeApiRF {
     private RFService rfService;
     int code;
 	 String Message;

	public RFService getRfService() {
		return rfService;
	}

	public void setRfService(RFService rfService) {
		this.rfService = rfService;
	}
	
	
	/**
	 *@author huangqin
	 *  查找射频插座
	 * 2019年11月30日
	 * @throws IOException 
	 */
	public void selectRfeq() throws IOException{ 
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		String userId = ServletActionContext.getRequest().getParameter("userId");
		JSONObject json = new JSONObject();
		JSONArray jsonObject = null;
		List<Intermediate> Intermediates = rfService.selectRfeq(userId);
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
     *  查找射频开关
     * 2019年11月30日
     * @throws IOException 
     */
    public void selectRf() throws IOException{
    	ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		String userId = ServletActionContext.getRequest().getParameter("userId");
		JSONObject json = new JSONObject();
		JSONArray jsonObject = null;
		if (userId != "" && userId != null) {
			code = 200;
			Message = "success";
			List<RF> rf = rfService.selectRf(userId);

			for (int i = 0; i < rf.size(); i++) {
				rf.get(i).setEquipmentNote(rfService.equipmentNote(userId,rf.get(i).getEquipmentId()));
				rf.get(i).setEquipmentState(rfService.state(rf.get(i).getEquipmentId()));
			}
			jsonObject = JSONArray.fromObject(rf);

			json.put("code", code);
			json.put("Message", Message);
			json.put("data", jsonObject);
		} else {
			code = 500;
			Message = "用户id为空！";
			json.put("code", code);
			json.put("Message", Message);
		}

		System.out.println("json==" + json);

		out.print(json);
		out.flush();
		out.close();
    	
    }
    /**
     *@author huangqin
        *  添加射频开关
     * 2019年11月30日
     * @throws IOException 
     */
    public void addRf() throws IOException {
    	ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		JSONObject json = new JSONObject();
		String userId = ServletActionContext.getRequest().getParameter("userId");
		String equipmentId = ServletActionContext.getRequest().getParameter("equipmentId");
		String nickName = ServletActionContext.getRequest().getParameter("nickName");
		String btnSize=ServletActionContext.getRequest().getParameter("btnSize");
        String switchId=ServletActionContext.getRequest().getParameter("switchId");
		 
        String btnName="";
        int btnSizeInt=Integer.parseInt(btnSize);
        for(int i=0;i<btnSizeInt;i++) {
        	btnName+="按钮"+(i+1)+",";
        }
        
		RF rf = new RF();
		rf.setUserId(userId);
		rf.setEquipmentId(equipmentId);
		rf.setProductId(equipmentId.substring(0, 6));
		rf.setNickName(nickName);
		rf.setBtnSize(btnSize);;
		rf.setSwitchId(switchId);
        rf.setBtnName(btnName);
        
		rfService.addRf(rf);

		code = 200;
		Message = "success";

		json.put("code", code);
		json.put("Message", "新增成功！");

		out.print(json);
		out.flush();
		out.close();
    	
    }
    /**
     *@author huangqin
     * 删除射频开关
     * 2019年11月30日
     * @throws IOException 
     */
    public void delRf() throws IOException {
    	ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		JSONObject json = new JSONObject();
		String rfId = ServletActionContext.getRequest().getParameter("rfId");

		rfService.delRf(Integer.parseInt(rfId));

		code = 200;
		Message = "success";

		json.put("code", code);
		json.put("Message", "删除成功！");

		out.print(json);
		out.flush();
		out.close();
    	
    }
    /**
     *@author huangqin
     *  更新射频开关
     * 2019年11月30日
     * @throws IOException 
     */
    public void updRf() throws IOException {
    	ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		JSONObject json = new JSONObject();

		String rfId = ServletActionContext.getRequest().getParameter("rfId");
		String nickname = ServletActionContext.getRequest().getParameter("nickName");
		String btnName=ServletActionContext.getRequest().getParameter("btnName");
		System.out.println("nickname:"+nickname);
		rfService.updRf(Integer.parseInt(rfId), nickname, btnName);

		code = 200;
		Message = "success";

		json.put("code", code);
		json.put("Message", "更新成功！");

		out.print(json);
		out.flush();
		out.close();
    	
    }
   
}
