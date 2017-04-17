package com.lakala.ipos.document;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 设备信息存储文档模型
 * 
 * @author liguangsheng
 * @date 2016-06-28
 */
@Document(collection = "devinfo")
public class DevinfoDocument {
	@Field("_id")
	private String id;

	@Field("cid")
	@JSONField(name = "CID")
	private String cid;

	@Field("mnc")
	@JSONField(name = "MNC")
	private String mnc;

	@Field("mcc")
	@JSONField(name = "MCC")
	private String mcc;

	@Field("lac")
	@JSONField(name = "lac")
	private String lac;

	private String mac;
	private String level;
	private String maxFreq;
	private String cpuName;
	private String minFreq;
	private String curFreq;
	private String availMem;
	private String totalMem;
	private String totalSdCard;
	private String availSdCard;
	private Date sentTime;
	private Date recvTime;
	private Date createTime;
	private Date updateTime;
	private String readFlag;
	private String delStatus;
	private String sn;
	private String app;
	private String manufacture;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getMnc() {
		return mnc;
	}

	public void setMnc(String mnc) {
		this.mnc = mnc;
	}

	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getLac() {
		return lac;
	}

	public void setLac(String lac) {
		this.lac = lac;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMaxFreq() {
		return maxFreq;
	}

	public void setMaxFreq(String maxFreq) {
		this.maxFreq = maxFreq;
	}

	public String getCpuName() {
		return cpuName;
	}

	public void setCpuName(String cpuName) {
		this.cpuName = cpuName;
	}

	public String getMinFreq() {
		return minFreq;
	}

	public void setMinFreq(String minFreq) {
		this.minFreq = minFreq;
	}

	public String getCurFreq() {
		return curFreq;
	}

	public void setCurFreq(String curFreq) {
		this.curFreq = curFreq;
	}

	public String getAvailMem() {
		return availMem;
	}

	public void setAvailMem(String availMem) {
		this.availMem = availMem;
	}

	public String getTotalMem() {
		return totalMem;
	}

	public void setTotalMem(String totalMem) {
		this.totalMem = totalMem;
	}

	public String getTotalSdCard() {
		return totalSdCard;
	}

	public void setTotalSdCard(String totalSdCard) {
		this.totalSdCard = totalSdCard;
	}

	public String getAvailSdCard() {
		return availSdCard;
	}

	public void setAvailSdCard(String availSdCard) {
		this.availSdCard = availSdCard;
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

	public String getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}

	public String getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(String delStatus) {
		this.delStatus = delStatus;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

}
