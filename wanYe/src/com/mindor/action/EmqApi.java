package com.mindor.action;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class EmqApi {
	/*
	 * 使用JAVA请求需要Basic身份验证的网页 
	 */
	static final String kuser = "admin"; // 用户名
	static final String kpass = "public"; // 密码
	
	    public static class MyAuthenticator extends Authenticator {
	    	public PasswordAuthentication getPasswordAuthentication() {
	    		return (new PasswordAuthentication(kuser, kpass.toCharArray()));
	    	}
	    }
	    
}
