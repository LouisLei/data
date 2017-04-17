package com.lakala.ipos.receivelog.model;

public class ReceivedLog {
    private Long id;

    private String appId;

    private String appVersion;

    private String bizCode;

    private String serialNo;

    private String createTime;

    private String readFlag;

    private String retrieveReadFlag;

    private String token;

    private String dataPak;
    
    private String sentTime;
    
    private String recvTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(String readFlag) {
        this.readFlag = readFlag;
    }

    public String getRetrieveReadFlag() {
        return retrieveReadFlag;
    }

    public void setRetrieveReadFlag(String retrieveReadFlag) {
        this.retrieveReadFlag = retrieveReadFlag;
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
	
	public ReceivedLog(Long id, String appId, String appVersion,
			String bizCode, String serialNo, String createTime,
			String readFlag, String token, String dataPak,String sentTime,String recvTime) {
		this.id = id;
		this.appId = appId;
		this.appVersion = appVersion;
		this.bizCode = bizCode;
		this.serialNo = serialNo;
		this.createTime = createTime;
		this.readFlag = readFlag;
		this.token = token;
		this.dataPak = dataPak;
		this.sentTime=sentTime;
		this.recvTime=recvTime;
	}

	public ReceivedLog() {
		// TODO Auto-generated constructor stub
	}

}