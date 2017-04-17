package com.lakala.ipos.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "appmall")
public class AppmallDocument {
	
	@Id
    private String actionId;	//交易ID

    private String eventId;		//事件ID, 不存入mongodb

    private String startTimeStamp;		//开始时间戳, 不存入mongodb

    private String endTimeStamp;		//结束时间戳, 不存入mongodb
    
    private Date downloadStartTimeStamp;		//下载开始时间戳, eventId = E001时, 存入mongodb
    
    private Date downloadEndTimeStamp;		//下载结束时间戳, eventId = E001时, 存入mongodb
    
    private Date installStartTimeStamp;		//安装开始时间戳, eventId = E002时, 存入mongodb
    
    private Date installEndTimeStamp;			//安装结束时间戳, eventId = E002时, 存入mongodb

    private String appId;		//APPID

    private String versionId;	//版本号

    private String downStatus;	//下载状态

    private String downFailedReason;	//下载失败原因

    private String downProException;	//下载过程异常栈

    private String appSize;		//App大小

    private String oldVersionId;	//旧版本号

    private String newVersionId;	//新版本号

    private String installStatus;	//安装状态

    private String installFailedReason;		//安装失败原因

    private String exception;		//安装过程异常信息

    private String exceptionMsg;	//Crash日志信息
    
    private String sn;		//sn终端号
    
    private Date sentTime;		//发送时间
    
    private Date recvTime;		//接收时间
    
    private Date createTime;		//创建时间

    private Date updateTime;		//最后修改时间
    
    public AppmallDocument() {
		// TODO Auto-generated constructor stub
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getStartTimeStamp() {
		return startTimeStamp;
	}

	public void setStartTimeStamp(String startTimeStamp) {
		this.startTimeStamp = startTimeStamp;
	}

	public String getEndTimeStamp() {
		return endTimeStamp;
	}

	public void setEndTimeStamp(String endTimeStamp) {
		this.endTimeStamp = endTimeStamp;
	}

	public Date getDownloadStartTimeStamp() {
		return downloadStartTimeStamp;
	}

	public void setDownloadStartTimeStamp(Date downloadStartTimeStamp) {
		this.downloadStartTimeStamp = downloadStartTimeStamp;
	}

	public Date getDownloadEndTimeStamp() {
		return downloadEndTimeStamp;
	}

	public void setDownloadEndTimeStamp(Date downloadEndTimeStamp) {
		this.downloadEndTimeStamp = downloadEndTimeStamp;
	}

	public Date getInstallStartTimeStamp() {
		return installStartTimeStamp;
	}

	public void setInstallStartTimeStamp(Date installStartTimeStamp) {
		this.installStartTimeStamp = installStartTimeStamp;
	}

	public Date getInstallEndTimeStamp() {
		return installEndTimeStamp;
	}

	public void setInstallEndTimeStamp(Date installEndTimeStamp) {
		this.installEndTimeStamp = installEndTimeStamp;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public String getDownStatus() {
		return downStatus;
	}

	public void setDownStatus(String downStatus) {
		this.downStatus = downStatus;
	}

	public String getDownFailedReason() {
		return downFailedReason;
	}

	public void setDownFailedReason(String downFailedReason) {
		this.downFailedReason = downFailedReason;
	}

	public String getDownProException() {
		return downProException;
	}

	public void setDownProException(String downProException) {
		this.downProException = downProException;
	}

	public String getAppSize() {
		return appSize;
	}

	public void setAppSize(String appSize) {
		this.appSize = appSize;
	}

	public String getOldVersionId() {
		return oldVersionId;
	}

	public void setOldVersionId(String oldVersionId) {
		this.oldVersionId = oldVersionId;
	}

	public String getNewVersionId() {
		return newVersionId;
	}

	public void setNewVersionId(String newVersionId) {
		this.newVersionId = newVersionId;
	}

	public String getInstallStatus() {
		return installStatus;
	}

	public void setInstallStatus(String installStatus) {
		this.installStatus = installStatus;
	}

	public String getInstallFailedReason() {
		return installFailedReason;
	}

	public void setInstallFailedReason(String installFailedReason) {
		this.installFailedReason = installFailedReason;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Date getSentTime() {
		return sentTime;
	}

	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
	}

	public Date getRecvTime() {
		return recvTime;
	}

	public void setRecvTime(Date recvTime) {
		this.recvTime = recvTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}