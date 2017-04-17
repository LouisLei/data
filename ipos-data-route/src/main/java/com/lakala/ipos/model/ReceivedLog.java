package com.lakala.ipos.model;

public class ReceivedLog {

    private String appId;

    private String appVersion;

    private String bizCode;

    private String serialNo;

    private String token;

    private String dataPak;
    
    private String sentTime;
    
    private String recvTime;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

	public String getDataPak() {
		return dataPak;
	}

	public void setDataPak(String dataPak) {
		this.dataPak = dataPak;
	}

	public String getSentTime() {
		return sentTime;
	}

	public void setSentTime(String sentTime) {
		this.sentTime = sentTime;
	}
	
	public String getRecvTime() {
        return recvTime;
    }

    public void setRecvTime(String recvTime) {
        this.recvTime = recvTime;
    }
	
	public ReceivedLog(String appId, String appVersion,
			String bizCode, String serialNo, String token, String dataPak,String sentTime,String recvTime) {
		this.appId = appId;
		this.appVersion = appVersion;
		this.bizCode = bizCode;
		this.serialNo = serialNo;
		this.token = token;
		this.dataPak = dataPak;
		this.sentTime=sentTime;
		this.recvTime=recvTime;
	}

	public ReceivedLog() {
		// TODO Auto-generated constructor stub
	}

}