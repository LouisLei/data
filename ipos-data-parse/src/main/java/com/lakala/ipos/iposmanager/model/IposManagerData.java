package com.lakala.ipos.iposmanager.model;

public class IposManagerData {
	private Long id;

	private Long recvId;

	private String appId;

	private String actionId;

	private String actionName;

	private String eventId;

	private String status;

	private String timeStamp;

	private String reason;

	private String usefulMemory;

	private String percent;

	private String iccid;

	private String available;

	private String total;

	private String balance;

	private String packList;

	private String orderNum;

	private String packId;

	private String amount;

	private String crash;

	private String sn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRecvId() {
		return recvId;
	}

	public void setRecvId(Long recvId) {
		this.recvId = recvId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId == null ? null : appId.trim();
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId == null ? null : actionId.trim();
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName == null ? null : actionName.trim();
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId == null ? null : eventId.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason == null ? null : reason.trim();
	}

	public String getUsefulMemory() {
		return usefulMemory;
	}

	public void setUsefulMemory(String usefulMemory) {
		this.usefulMemory = usefulMemory == null ? null : usefulMemory.trim();
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent == null ? null : percent.trim();
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid == null ? null : iccid.trim();
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available == null ? null : available.trim();
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total == null ? null : total.trim();
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance == null ? null : balance.trim();
	}

	public String getPackList() {
		return packList;
	}

	public void setPackList(String packList) {
		this.packList = packList == null ? null : packList.trim();
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum == null ? null : orderNum.trim();
	}

	public String getPackId() {
		return packId;
	}

	public void setPackId(String packId) {
		this.packId = packId == null ? null : packId.trim();
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount == null ? null : amount.trim();
	}

	public String getCrash() {
		return crash;
	}

	public void setCrash(String crash) {
		this.crash = crash == null ? null : crash.trim();
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn == null ? null : sn.trim();
	}
}