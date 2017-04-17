package com.lakala.ipos.service.impl;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.lakala.ipos.document.AppmallDocument;
import com.lakala.ipos.document.ReceiverDocument;
import com.lakala.ipos.repository.AppmallRepository;
import com.lakala.ipos.service.AppmallService;
import com.lakala.ipos.util.FastjsonUtil;
import com.lakala.ipos.util.GzipUtils;

@Service
public class AppmallServiceImpl implements AppmallService {

	private final Logger logger = LogManager.getLogger(AppmallServiceImpl.class);
	
	private static final String DOWNLOAD_FLAG = "E001";		//下载事件
	
	private static final String INSTALL_FLAG = "E002";		//安装事件
	
	@Autowired
	private AppmallRepository appmallRepository;
	
	public AppmallServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 功能：
	 * 1.将接收数据Json转换成java接收数据对象
	 * 2.将对象中的应用商城业务数据转换成应用商城对象进行处理
	 * @param receivedDataJson	接收数据Json
	 */
	@Override
	public void transformReceivedData(String receivedDataJson) {
		try {
			logger.info("应用商城数据处理中...");
			FastjsonUtil<ReceiverDocument> fastjsonUtil = new FastjsonUtil<ReceiverDocument>(ReceiverDocument.class);
			ReceiverDocument receiverDocument = fastjsonUtil.parseToBean(receivedDataJson);// 解析Json字符串
			String appmallJson = GzipUtils.uncompressMessage(receiverDocument.getDataPak());
			convertToAppmall(appmallJson, receiverDocument);	//转换对象
		} catch (Exception e) {
			logger.error("异常信息",e.getMessage());
		} finally {
			logger.info("应用商城数据处理完成！");
		}
	}
	
	/**
	 * 功能：
	 * 1.将json转换成java对象
	 * 2.并批量固化对象
	 * @param appmallJson	应用商城数据Json
	 * @param receiverDocument	接收数据对象
	 */
	@Override
	public void convertToAppmall(String appmallJson, ReceiverDocument receiverDocument) throws Exception {
		if(appmallJson == null || "".equalsIgnoreCase(appmallJson) )
			return;
		FastjsonUtil<AppmallDocument> fastjsonUtil = new FastjsonUtil<AppmallDocument>(AppmallDocument.class);
		List<AppmallDocument> list = fastjsonUtil.parseToList(appmallJson);// 解析Json数组字符串
		batchSaveOrUpdate(list, receiverDocument);
	}
	
	/**
	 * 功能：
	 * 1.批量固化对象
	 * @param appmallJson	接收数据Json
	 * @param receiverDocument	接收数据对象
	 * @throws Exception
	 */
	@Override
	public void batchSaveOrUpdate(List<AppmallDocument> list, ReceiverDocument receiverDocument) {
		// TODO Auto-generated method stub
		for (AppmallDocument appmallDocument : list) {
			try {
				saveOrUpdate(appmallDocument, receiverDocument);
			} catch (Exception e) {
				logger.error("异常信息",e.getMessage());
			}
		}
	}
	
	/**
	 * 
	 * 功能：单条数据处理
	 * 1.数据不存在，插入数据
	 * 2.数据存在，修改数据
	 * @param appmallDocument	数据对象
	 * @param receiverDocument	接收数据对象
	 * @throws Exception
	 */
	public void saveOrUpdate(AppmallDocument appmallDocument, ReceiverDocument receiverDocument) throws Exception {
		Date currentTime = new Date();
		convertDataStructure(appmallDocument, receiverDocument, currentTime);	//转换数据结构
		Query query = new Query(new Criteria("_id").is(appmallDocument.getActionId()));	//_id查询
		boolean existFlag = appmallRepository.exists(query, AppmallDocument.class);
		if(!existFlag){	//数据不存在,设置创建时间，并使用insert方法插入数据
			appmallDocument.setCreateTime(currentTime);
			appmallRepository.insert(appmallDocument);
		} else {	//数据存在,设置当前对象中属性值不为空的属性，并使用update修改数据
			doUpdateWithOptions(appmallDocument, query);
		}
	}
	
	/**
	 * 功能：
	 * 1.获取需要转换的属性
	 * 2.转换数据结构
	 * @param appmallDocument	数据对象
	 * @param receiverDocument	接收数据对象
	 * @param currentTime	当前时间戳
	 * @throws Exception 
	 */
	private void convertDataStructure(AppmallDocument appmallDocument, ReceiverDocument receiverDocument, Date currentTime) throws Exception {
		String eventId = appmallDocument.getEventId();
		String startTimeStamp = appmallDocument.getStartTimeStamp();
		String endTimeStamp = appmallDocument.getEndTimeStamp();
		if(eventId.equalsIgnoreCase(DOWNLOAD_FLAG)){
			appmallDocument.setDownloadStartTimeStamp(Timestamp.valueOf(startTimeStamp));
			appmallDocument.setDownloadEndTimeStamp(Timestamp.valueOf(endTimeStamp));
		}else if (eventId.equalsIgnoreCase(INSTALL_FLAG)) {
			appmallDocument.setInstallStartTimeStamp(Timestamp.valueOf(startTimeStamp));
			appmallDocument.setInstallEndTimeStamp(Timestamp.valueOf(endTimeStamp));
		}
		appmallDocument.setEventId(null);
		appmallDocument.setStartTimeStamp(null);
		appmallDocument.setEndTimeStamp(null);
		appmallDocument.setSn(receiverDocument.getSerialNo());		//sn终端号
		appmallDocument.setSentTime(Timestamp.valueOf(receiverDocument.getSentTime()));	//发送时间
		appmallDocument.setRecvTime(Timestamp.valueOf(receiverDocument.getRecvTime()));	//接收时间
		appmallDocument.setUpdateTime(currentTime);	//最后修改时间
	}
	
	/**
	 * 功能：
	 * 1.使用反射获取所有属性与属性值
	 * 2.设置非空数据
	 * 3.更具查询修改第一条数据
	 * @param appmallDocument 	需要更新数据的对象
	 * @param query		查询条件
	 * @throws IllegalAccessException
	 */
	private void doUpdateWithOptions(AppmallDocument appmallDocument, Query query)
			throws IllegalAccessException {
		Update update = new Update();
		Field[] fieldArrays = appmallDocument.getClass().getDeclaredFields();
		for (Field field : fieldArrays) {
			field.setAccessible(true);	//修改访问权限
			String propertyName = field.getName();
			Object propertyValue = field.get(appmallDocument);
			if(propertyValue != null && !"".equals(propertyValue)) {
				update.set(propertyName, propertyValue);
			}
		}
		appmallRepository.updateFirst(query, update, AppmallDocument.class);
	}
}
