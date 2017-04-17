package com.lakala.ipos.receivelog.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lakala.ipos.common.util.JsonUtil;
import com.lakala.ipos.receivelog.dao.ReceivedLogMapper;
import com.lakala.ipos.receivelog.model.ReceivedLog;
import com.lakala.ipos.receivelog.service.ReceivedLogService;

@Service("mysqlReceivedLogService")
public class ReceivedLogServiceImpl implements ReceivedLogService {

	private static final Logger logger = LogManager.getLogger(ReceivedLogServiceImpl.class);
	
	@Autowired
	private ReceivedLogMapper receivedLogMapper;
	
	@Transactional(readOnly = true)
	@Override
	public ReceivedLog selectById(Long id) {
		return receivedLogMapper.selectByPrimaryKey(id);
	}

	@Transactional(readOnly = false)
	@Override
	public void insertAndGetId(ReceivedLog receivedLog) {
//		logger.info("DAO层插入数据开始....");
		receivedLogMapper.insertAndGetId(receivedLog);
//		logger.info("DAO层插入数据开始....");
	}
	 
	@Override
	public ReceivedLog transferReceivedLog(String receivedLogJson) {
		try {
			JsonUtil<ReceivedLog> jsonUtil = new JsonUtil<ReceivedLog>(ReceivedLog.class);
			ReceivedLog receivedLog = jsonUtil.parseToBean(receivedLogJson);// 解析Json字符串
			receivedLog.setRetrieveReadFlag("Y"); //设置取标记为Y,已被取过
			receivedLog.setReadFlag("Y"); //设置读标记为Y,表示已处理过
			this.insertAndGetId(receivedLog);
			return receivedLog;
		} catch (Exception e) {
			logger.error("处理接收数据异常——{receivedLogJson:"+receivedLogJson+"}");
			logger.error("处理接收数据异常", e);
		}
		return null;
	}
	
}


