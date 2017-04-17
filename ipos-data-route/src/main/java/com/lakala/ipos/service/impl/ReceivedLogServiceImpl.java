package com.lakala.ipos.service.impl;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.lakala.ipos.model.ReceivedLog;
import com.lakala.ipos.service.ReceivedLogService;
import com.lakala.ipos.util.Constants;
import com.lakala.ipos.util.FastjsonUtil;
import com.lakala.ipos.util.GzipUtils;
import com.lakala.ipos.util.JsonUtil;
import com.lakala.msc.client.producer.LakalaProducer;

@Service
public class ReceivedLogServiceImpl implements ReceivedLogService , InitializingBean, DisposableBean {

	private static final Logger logger = LogManager.getLogger(ReceivedLogServiceImpl.class);
	
	@Autowired
	@Qualifier("paymentProducer")
	private LakalaProducer paymentProducer; //收单生产者
	
	
	@Autowired
	@Qualifier("appmallProducer")
	private LakalaProducer appmallProducer;	//应用商城生产者
	
	@Autowired
	@Qualifier("devinfoProducer")
	private LakalaProducer devinfoProducer; //基础设备信息生产者
	
	@Autowired
	@Qualifier("iposmgrProducer")
	private LakalaProducer iposmgrProducer; //pos+管家生产者
	
	/**
	 * 功能: 
	 * 1.解析消息json字符串
	 * 2.将dataPak解压缩成json字符串
	 * 3.提取json串中各业务类型的数据，并组装业务数据Json
	 * 4.将数据发送到kafka中
	 * @param message
	 *            接收到的压缩包
	 */
	@Override
	public void dealData(String message) {
		try {
			logger.info("数据处理中...");
			FastjsonUtil<String> fastjsonUtil = new FastjsonUtil<String>(String.class);
			ReceivedLog receivedLog = transformReceivedLog(message);
			if (receivedLog == null)
				return;
			String dataPkg = GzipUtils.uncompressMessage(receivedLog.getDataPak());	//解压缩数据包
			if (dataPkg == null || "".equalsIgnoreCase(dataPkg)) //解压缩后的数据为空则不处理
				return;
			Map<String, String> resutlMap = fastjsonUtil.parseChildJsonToMap(dataPkg);// 解析Json数组字符串，分离业务数据
			assembleBizData(resutlMap, receivedLog);	//组装业务数据，并发送到kafka中
		} catch (Exception e) {
			logger.error("异常信息", e.getMessage());
		} finally {
			logger.info("数据处理完成！");
		}
	}
	
	/**
	 * 功能：
	 * 1.将json字符串转换成java对象
	 * @param receivedLogJson	json字符串
	 * @return java对象
	 * @throws Exception 
	 */
	@Override
	public ReceivedLog transformReceivedLog(String receivedLogJson) throws Exception {
		try {
			JsonUtil<ReceivedLog> jsonUtil = new JsonUtil<ReceivedLog>(ReceivedLog.class);
			ReceivedLog receivedLog = jsonUtil.parseToBean(receivedLogJson);// 解析Json字符串
			return receivedLog;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	/**
	 * 功能：
	 * 组装业务类型数据，并发到消息中间件中
	 * @param resutlMap		业务类型-业务类型数据
	 * @param receivedLog		接收（发送）消息对象
	 * @param resultStatus 
	 */
	private void assembleBizData(Map<String, String> resutlMap, ReceivedLog receivedLog) {
		if (resutlMap == null || resutlMap.size() <= 0)
			return;
		for (Entry<String, String> entry : resutlMap.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			String gzipDataPak = GzipUtils.compressMessage(value);
			receivedLog.setDataPak(gzipDataPak);	//替换为业务数据
			receivedLog.setToken(null);
			receivedLog.setBizCode(null);
			String message = JSON.toJSON(receivedLog).toString();
			SendBizMsg(key, message);	//发送消息
		}
	}
	
	/**
	 * 功能：
	 * 1.根据业务类型数据分别将业务数据发送到不同topic种的数据
	 * @param key	业务类型
	 * @param message	包含业务类型数据的字符串
	 */
	private void SendBizMsg(String key, String message) {
		switch (key) {
		case Constants.DEVINFO_CODE:// 基础设备信息数据转换
			if(devinfoProducer.send(key, message))
				logger.info("基础设备消息发送成功！");
			else
				logger.info("基础设备消息发送失败！");
			break;
		case Constants.PAYMENT_CODE:// 收单数据转换
			if(paymentProducer.send(key, message))
				logger.info("收单消息发送成功！");
			else 
				logger.info("收单消息发送失败！");
			break;
		case Constants.APPMALL_CODE:// 应用商城数据转换
			if(appmallProducer.send(key, message))
				logger.info("应用商城消息发送成功！");
			else 
				logger.info("应用商城消息发送失败！");
			break;
		case Constants.MANAGER_CODE:// IPOS管家数据转换
			if(iposmgrProducer.send(key, message))
				logger.info("ipos管家消息发送成功！");
			else
				logger.info("ipos管家消息发送失败！");
			break;
		default:
			break;
		}
	}
	
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		if (paymentProducer !=null) {
			try {
				paymentProducer.close();
			} catch (Exception e) {
				logger.error("销毁收单生产者异常", e.getMessage());
			}
		}
		if (appmallProducer !=null) {
			try {
				appmallProducer.close();
			} catch (Exception e) {
				logger.error("销毁应用商城生产者异常", e.getMessage());
			}
		}
		if (devinfoProducer !=null) {
			try {
				devinfoProducer.close();
			} catch (Exception e) {
				logger.error("销毁 基础设备信息生产者异常", e.getMessage());
			}
		}
		if (iposmgrProducer !=null) {
			try {
				iposmgrProducer.close();
			} catch (Exception e) {
				logger.error("销毁pos+管家生产者异常", e.getMessage());
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		if (paymentProducer !=null) {
			this.paymentProducer.init();
		}
		if (appmallProducer !=null) {
			this.appmallProducer.init();
		}
		if (devinfoProducer !=null) {
			this.devinfoProducer.init();
		}
		if (iposmgrProducer !=null) {
			this.iposmgrProducer.init();
		}
	}
}


