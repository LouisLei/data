package com.lakala.ipos.devinfodata.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lakala.ipos.common.util.FastjsonUtil;
import com.lakala.ipos.devinfodata.dao.DevinfoDataMapper;
import com.lakala.ipos.devinfodata.model.DevinfoData;
import com.lakala.ipos.devinfodata.service.DevinfoDataService;
     
@Service("mysqlDeviceLogService")
public class DevinfoDataServiceImpl implements DevinfoDataService {
	private final Logger logger = LogManager.getLogger(DevinfoDataServiceImpl.class);

	@Autowired
	private DevinfoDataMapper devinfoDataMapper;
	
	public DevinfoDataServiceImpl() {
	}

	@Transactional(readOnly = false)
	public void batchInsertByRecvId(long recvId, List<DevinfoData> list, String sn) throws Exception {
		devinfoDataMapper.insertBatchByRecvId(recvId, list, sn);
	}
	
	@Transactional(readOnly = false)
	public void insertByRecvId(long recvId, DevinfoData devinfoData) throws Exception {
		devinfoData.setRecvId(recvId);
		devinfoDataMapper.insert(devinfoData);
	}
	
	@Transactional(readOnly = false)
	public void insert(DevinfoData devinfoData) throws Exception {
		devinfoDataMapper.insert(devinfoData);
	}

	@Override
	public void transferDeviceLog(String deviceData, long id, String sn) {
		try {
			FastjsonUtil<DevinfoData> fastjsonUtil = new FastjsonUtil<DevinfoData>(DevinfoData.class);
			List<DevinfoData> list = fastjsonUtil.parseToList(deviceData);// 解析Json数组字符串
			batchInsertByRecvId(id,list,sn);
		} catch (Exception e) {
			logger.error("处理基础信息数据异常——{recvId:"+id+"}");
			logger.error("处理基础信息数据异常", e);
		}
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteByRecvId(long recvId) throws Exception {
		devinfoDataMapper.deleteByRecvId(recvId);
	}

}