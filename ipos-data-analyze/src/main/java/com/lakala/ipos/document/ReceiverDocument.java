package com.lakala.ipos.document;

public class ReceiverDocument {

	private String appId;

    private String appVersion;

    private String serialNo;	//sn终端号

    private String dataPak;		//业务数据包
    
    private String sentTime;
    
    private String recvTime;

    public ReceiverDocument() {
		// TODO Auto-generated constructor stub
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

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
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
	
}