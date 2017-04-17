package com.lakala.ipos.paymentdata.document;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 收单数据存储文档模型
 * 
 * @author Wangyu
 * @date 2016/6/28
 *
 */
@Document(collection = "payment")
public class PaymentDataDocument {
    @Field(value = "actionId")
	private String actionId;
	private Date e001TimeStamp;
	private Date e002TimeStamp;
	private Date e003TimeStamp;
	private Date e004TimeStamp;
	private Date e005TimeStamp;
	private Date e006TimeStamp;
	private Date e007TimeStamp;
	private Date e008TimeStamp;
	private Date e009TimeStamp;
	private Date e010TimeStamp;
	private Date e011TimeStamp;
	private Date e012TimeStamp;
	private Date e013TimeStamp;
	private Date e014TimeStamp;
	private String tradeType;
	private String appId;
	private String appVersion;
	private String bizCode;
	private String merTerminalNum;
	private String cardInfoStyle;
	private String checkCardStatus;
	private String checkCardErrCode;
	private String starPBOC;
	private String cardFormat;
	private String merchantNum;
	private String terminalNum;
	private String batchNum;
	private String serialNum;
	private Double amount;
	private String networkType;
	private String networkInfo;
	private String hostReturnStatus;
	private String writeCardProStatus;
	private String callPrinterTime;
	@Field("emvErrCode")
	@JSONField(name = "EMVErrCode")
	private String emvErrCode;
	private Date sentTime;
	private Date recvTime;
	private Date createTime;
	private Date updateTime;
	private String sn;
	private String result;
	private String printContent;
	private Date firstEventTimeStamp;
	
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	public Date getE001TimeStamp() {
		return e001TimeStamp;
	}
	public void setE001TimeStamp(Date e001timeStamp) {
		this.e001TimeStamp = e001timeStamp;
	}
	public Date getE002TimeStamp() {
		return e002TimeStamp;
	}
	public void setE002TimeStamp(Date e002TimeStamp) {
		this.e002TimeStamp = e002TimeStamp;
	}
	public Date getE003TimeStamp() {
		return e003TimeStamp;
	}
	public void setE003TimeStamp(Date e003TimeStamp) {
		this.e003TimeStamp = e003TimeStamp;
	}
	public Date getE004TimeStamp() {
		return e004TimeStamp;
	}
	public void setE004TimeStamp(Date e004TimeStamp) {
		this.e004TimeStamp = e004TimeStamp;
	}
	public Date getE005TimeStamp() {
		return e005TimeStamp;
	}
	public void setE005TimeStamp(Date e005TimeStamp) {
		this.e005TimeStamp = e005TimeStamp;
	}
	public Date getE006TimeStamp() {
		return e006TimeStamp;
	}
	public void setE006TimeStamp(Date e006TimeStamp) {
		this.e006TimeStamp = e006TimeStamp;
	}
	public Date getE007TimeStamp() {
		return e007TimeStamp;
	}
	public void setE007TimeStamp(Date e007TimeStamp) {
		this.e007TimeStamp = e007TimeStamp;
	}
	public Date getE008TimeStamp() {
		return e008TimeStamp;
	}
	public void setE008TimeStamp(Date e008TimeStamp) {
		this.e008TimeStamp = e008TimeStamp;
	}
	public Date getE009TimeStamp() {
		return e009TimeStamp;
	}
	public void setE009TimeStamp(Date e009TimeStamp) {
		this.e009TimeStamp = e009TimeStamp;
	}
	public Date getE010TimeStamp() {
		return e010TimeStamp;
	}
	public void setE010TimeStamp(Date e010TimeStamp) {
		this.e010TimeStamp = e010TimeStamp;
	}
	public Date getE011TimeStamp() {
		return e011TimeStamp;
	}
	public void setE011TimeStamp(Date e011TimeStamp) {
		this.e011TimeStamp = e011TimeStamp;
	}
	public Date getE012TimeStamp() {
		return e012TimeStamp;
	}
	public void setE012TimeStamp(Date e012TimeStamp) {
		this.e012TimeStamp = e012TimeStamp;
	}
	public Date getE013TimeStamp() {
		return e013TimeStamp;
	}
	public void setE013TimeStamp(Date e013TimeStamp) {
		this.e013TimeStamp = e013TimeStamp;
	}
	public Date getE014TimeStamp() {
		return e014TimeStamp;
	}
	public void setE014TimeStamp(Date e014TimeStamp) {
		this.e014TimeStamp = e014TimeStamp;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
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
	public String getMerTerminalNum() {
		return merTerminalNum;
	}
	public void setMerTerminalNum(String merTerminalNum) {
		this.merTerminalNum = merTerminalNum;
	}
	public String getCardInfoStyle() {
		return cardInfoStyle;
	}
	public void setCardInfoStyle(String cardInfoStyle) {
		this.cardInfoStyle = cardInfoStyle;
	}
	public String getCheckCardStatus() {
		return checkCardStatus;
	}
	public void setCheckCardStatus(String checkCardStatus) {
		this.checkCardStatus = checkCardStatus;
	}
	public String getCheckCardErrCode() {
		return checkCardErrCode;
	}
	public void setCheckCardErrCode(String checkCardErrCode) {
		this.checkCardErrCode = checkCardErrCode;
	}
	public String getStarPBOC() {
		return starPBOC;
	}
	public void setStarPBOC(String starPBOC) {
		this.starPBOC = starPBOC;
	}
	public String getCardFormat() {
		return cardFormat;
	}
	public void setCardFormat(String cardFormat) {
		this.cardFormat = cardFormat;
	}
	public String getMerchantNum() {
		return merchantNum;
	}
	public void setMerchantNum(String merchantNum) {
		this.merchantNum = merchantNum;
	}
	public String getTerminalNum() {
		return terminalNum;
	}
	public void setTerminalNum(String terminalNum) {
		this.terminalNum = terminalNum;
	}
	public String getBatchNum() {
		return batchNum;
	}
	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getNetworkType() {
		return networkType;
	}
	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}
	public String getNetworkInfo() {
		return networkInfo;
	}
	public void setNetworkInfo(String networkInfo) {
		this.networkInfo = networkInfo;
	}
	public String getHostReturnStatus() {
		return hostReturnStatus;
	}
	public void setHostReturnStatus(String hostReturnStatus) {
		this.hostReturnStatus = hostReturnStatus;
	}
	public String getWriteCardProStatus() {
		return writeCardProStatus;
	}
	public void setWriteCardProStatus(String writeCardProStatus) {
		this.writeCardProStatus = writeCardProStatus;
	}
	public String getCallPrinterTime() {
		return callPrinterTime;
	}
	public void setCallPrinterTime(String callPrinterTime) {
		this.callPrinterTime = callPrinterTime;
	}
	public String getEmvErrCode() {
		return emvErrCode;
	}
	public void setEmvErrCode(String emvErrCode) {
		this.emvErrCode = emvErrCode;
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
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getPrintContent() {
		return printContent;
	}
	public void setPrintContent(String printContent) {
		this.printContent = printContent;
	}
	public Date getFirstEventTimeStamp() {
		return firstEventTimeStamp;
	}
	public void setFirstEventTimeStamp(Date firstEventTimeStamp) {
		this.firstEventTimeStamp = firstEventTimeStamp;
	}
	

}
