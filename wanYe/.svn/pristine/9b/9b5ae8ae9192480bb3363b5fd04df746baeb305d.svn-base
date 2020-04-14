package com.mindor.api;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.mindor.entity.OpenUser;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class WanYeApiUpdateHeads extends ActionSupport {

	private OpenUser user;
	private int code;
	private String Message;

	private File headImg;
	private String headImgContentType;
	private String headImgFileName;
	private static SessionFactory factory;

	// 保存新增
	@SuppressWarnings("unchecked")
	public void updateHead() throws IOException {
		JSONObject json = new JSONObject();
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
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
			HashMap map = new HashMap();

			try {
				String userId = ServletActionContext.getRequest().getParameter(
						"userId"); // "minApp100001"
				System.out.println("userId=============" + userId);
				if (userId == null || userId == "" || userId == "undefind") {
					Message = "修改头像失败！";// 成功后返回状态
					code = 500; // 返回成功状态码
					json.put("Message", Message);
					json.put("code", code);
				} else {

					user = (OpenUser) session.get(OpenUser.class, userId);
					if (user != null) {
						// 处理头像
						if (headImg != null) {

							// 1、保存头像，保存头像文件到服务器中
							String filePath = ServletActionContext
									.getServletContext().getRealPath(
											"/upload/user");
							String fileName = UUID.randomUUID().toString()
									.replaceAll("-", "")
									+ headImgFileName.substring(headImgFileName
											.lastIndexOf("."));
							FileUtils.copyFile(headImg, new File(filePath,
									fileName));

							// 2、设置到用户头像
							user
									.setHeadPortrait("http://112.74.48.180/wanYe/upload/user/"
											+ fileName);
							// 保存用户及其角色
							session.update(user);
							String file = selectFile(userId);
							System.out.println("file===" + file);
							if (file != null) {
								delFile(file);// 删除历史头像
							}
							map.put("head",
									"http://112.74.48.180/wanYe/upload/user/"
											+ fileName);
							Message = "修改头像成功！";// 成功后返回状态
							code = 200; // 返回成功状态码
							json.put("Message", Message);
							json.put("code", code);
							json.put("data", map);
						} else {
							Message = "修改头像失败！";// 成功后返回状态
							code = 500; // 返回成功状态码
							json.put("Message", Message);
							json.put("code", code);
						}

					} else {
						Message = "修改头像失败！";// 成功后返回状态
						code = 500; // 返回成功状态码
						json.put("Message", Message);
						json.put("code", code);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			out.print(json);
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
	 *@author huangqin 删除文件 May 11, 2019
	 */
	public void delFile(String filename) {
		File file = new File(
				"C:/Users/Administrator/Desktop/tomcat_appInterface/webapps/wanYe/upload/user/"
						+ filename);
		if (file.exists() && file.isFile())
			file.delete();
	}

	public String selectFile(String userId) {
		String file = null;
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
			user = (OpenUser) session.get(OpenUser.class, userId);
			if (user != null) {
				file = user.getHeadPortrait();
				file = file.substring(file.indexOf("r/") + 2, file.length());
				tx.commit();
			}

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.getSessionFactory().close();
			session.close();
			System.gc();// 执行垃圾回收
		}
		return file;
	}

	public OpenUser getUser() {
		return user;
	}

	public void setUser(OpenUser user) {
		this.user = user;
	}

	public File getHeadImg() {
		return headImg;
	}

	public void setHeadImg(File headImg) {
		this.headImg = headImg;
	}

	public String getHeadImgContentType() {
		return headImgContentType;
	}

	public void setHeadImgContentType(String headImgContentType) {
		this.headImgContentType = headImgContentType;
	}

	public String getHeadImgFileName() {
		return headImgFileName;
	}

	public void setHeadImgFileName(String headImgFileName) {
		this.headImgFileName = headImgFileName;
	}
}
