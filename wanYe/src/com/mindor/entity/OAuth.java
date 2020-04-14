package com.mindor.entity;

public class OAuth {
	private int oauthId;
	private String userId;
	private String authCode;
	private String access_token;

	public int getOauthId() {
		return oauthId;
	}

	public void setOauthId(int oauthId) {
		this.oauthId = oauthId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getUserId() {
		return userId;
	}

	public String getAuthCode() {
		return authCode;
	}

	public String getAccess_token() {
		return access_token;
	}

	public OAuth() {
		super();
	}

	 public OAuth(String userId,String authCode,String access_token,int oauthId) {
		     super();
		     this.userId=userId;
		     this.authCode=authCode;
		     this.access_token=access_token;
		     this.oauthId=oauthId;
	 }
	 
	 }