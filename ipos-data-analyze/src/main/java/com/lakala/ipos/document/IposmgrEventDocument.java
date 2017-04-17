package com.lakala.ipos.document;

import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * IPOS管家中事件的存储文档模型
 * 
 * @author liguangsheng
 * @date 2016-06-28
 */
public class IposmgrEventDocument {
	@JSONField(name = "timeStamp")
	private Timestamp timestamp;
	private String availMem;
	private String percent;
	private String iccid;
	private String status;
	private String total;
	private String reason;
	private String balance;
	private String orderNum;
	private String packId;
	private String amount;
	private String crash;
	private String packList;

	public String getPackList() {
		return packList;
	}

	public void setPackList(String packList) {
		this.packList = packList;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getAvailMem() {
		return availMem;
	}

	public void setAvailMem(String availMem) {
		this.availMem = availMem;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getPackId() {
		return packId;
	}

	public void setPackId(String packId) {
		this.packId = packId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCrash() {
		return crash;
	}

	public void setCrash(String crash) {
		this.crash = crash;
	}

}
