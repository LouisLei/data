package com.lakala.ipos.appmalldata.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lakala.ipos.appmalldata.dao.AppmallDataMapper;
import com.lakala.ipos.appmalldata.model.AppmallData;
import com.lakala.ipos.appmalldata.service.AppmallDataService;
import com.lakala.ipos.common.util.FastjsonUtil;

@Service("mysqlAppmallLogService")
public class AppmallDataServiceImpl implements AppmallDataService {

	private final Logger logger = LogManager.getLogger(AppmallDataServiceImpl.class);
	
	@Autowired
	private AppmallDataMapper appmallDataMapper;
	
	public AppmallDataServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Transactional(readOnly = false)
	public void batchInsertByRecvId(long recvId, List<AppmallData> list, String sn) throws Exception {
		// TODO Auto-generated method stub
		appmallDataMapper.insertBatchByRecvId(recvId, list, sn);
	}

	@Override
	public void transferAppmallLog(String appmallData, long id, String sn) {
		try {
			FastjsonUtil<AppmallData> fastjsonUtil = new FastjsonUtil<AppmallData>(AppmallData.class);
			List<AppmallData> list = fastjsonUtil.parseToList(appmallData);// 解析Json数组字符串
			batchInsertByRecvId(id,list,sn);
		} catch (Exception e) {
			logger.error("处理应用商城数据异常——{recvId:"+id+"}");
			logger.error("处理应用商城数据异常", e);
		}
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteByRecvId(long recvId) throws Exception {
		// TODO Auto-generated method stub
		appmallDataMapper.deleteByRecvId(recvId);
	}

}
