package com.lakala.ipos.paymentdata.service.impl;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lakala.ipos.paymentdata.document.PaymentDataDocument;
import com.lakala.ipos.paymentdata.repository.PaymentDataRepository;
import com.lakala.ipos.paymentdata.service.PaymentDataService;
import com.lakala.ipos.paymentdata.util.GzipUtils;

/**
 * 收单数据处理接口实现
 * 
 * @author Wangyu
 * @date 2016/6/28
 *
 */
@Service("paymentDataService")
public class PaymentDataServiceImpl implements PaymentDataService {
	private static final Logger logger = LoggerFactory.getLogger(PaymentDataServiceImpl.class);

	@Autowired
	private PaymentDataRepository paymentDataRepository;

	@Override
	public void transfer(String message) {
		try {
			logger.debug("payment analyze begin......");

			//将数据转成JSON对象
			JSONObject json = JSON.parseObject(message);
			String dataPakGzip = json.getString("dataPak");
			String dataPak = GzipUtils.uncompressMessage(dataPakGzip);
			JSONArray dataArray = JSON.parseArray(dataPak);
			for (int i = 0; i < dataArray.size(); i++) {

				JSONObject data = dataArray.getJSONObject(i);
				logger.debug(data.toJSONString());
				logger.debug("appId: " + json.getString("appId"));
				logger.debug("appVersion: " + json.getString("appVersion"));
				logger.debug("serialNo: " + json.getString("serialNo"));
				String actionId = data.getString("actionId");
				//拼接事件时间戳字段
				String eventId = data.getString("eventId").toLowerCase() + "TimeStamp";
				Timestamp timeStamp = Timestamp.valueOf(data.getString("timeStamp"));
				data.put(eventId, timeStamp);

				PaymentDataDocument doc = null;
				doc = paymentDataRepository.findByActionId(actionId);
				if (doc == null) {
					//actionId不存在  第一次插入
					
					//将首次入库的事件时间戳保存到firstEventTimeStamp字段
					data.put("firstEventTimeStamp", timeStamp);
					//按指定Bean过滤数据
					doc = JSON.toJavaObject(data, PaymentDataDocument.class);
					doc.setSentTime(Timestamp.valueOf(json.getString("sentTime")));
					doc.setRecvTime(Timestamp.valueOf(json.getString("recvTime")));
					doc.setAppId(json.getString("appId"));
					doc.setAppVersion(json.getString("appVersion"));
					doc.setSn(json.getString("serialNo"));
					
					Timestamp current = new Timestamp(System.currentTimeMillis());
					doc.setCreateTime(current);
					doc.setUpdateTime(current);
					
					paymentDataRepository.insert(doc);
				} else {
					//actionId已存在  更新
					
					//按指定Bean过滤数据
					doc = JSON.toJavaObject(data, PaymentDataDocument.class);
					JSONObject jsonTemp = (JSONObject)JSON.toJSON(doc);
					
					paymentDataRepository.updateField(actionId, jsonTemp);
				}
			}
			logger.debug("payment analyze end......");
		} catch (Exception e) {
			logger.error("收单数据处理异常", e);
		}

	}

}
