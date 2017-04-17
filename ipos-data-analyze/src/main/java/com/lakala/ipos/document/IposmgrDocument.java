package com.lakala.ipos.document;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * ipos管家存储文档模型
 * 
 * @author liguangsheng
 * @date 2016-06-28
 */
@Document(collection = "iposmgr")
public class IposmgrDocument {
	private String appId;
	private String actionId;
	private String actionName;
	private Date sentTime;
	private Date recvTime;
	private Date createTime;
	private Date updateTime;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
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
