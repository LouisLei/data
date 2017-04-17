package com.lakala.ipos.iposmanager.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lakala.ipos.common.util.FastjsonUtil;
import com.lakala.ipos.iposmanager.dao.IposManagerDataMapper;
import com.lakala.ipos.iposmanager.model.IposManagerData;
import com.lakala.ipos.iposmanager.service.IposManagerDataService;

@Service("mysqlIposManagerDataService")
public class IposManagerDataServiceImpl implements IposManagerDataService {

	private static Logger logger = LoggerFactory.getLogger(IposManagerDataServiceImpl.class);

	@Autowired
	private IposManagerDataMapper managerDataMapper;

	@Override
	public void batchInsertByRecvId(long recvId, List<IposManagerData> list, String sn) throws Exception {
		managerDataMapper.insertBatchByRecvId(recvId, list, sn);
	}

	@Override
	public void deleteByRecvId(long recvId) throws Exception {
		managerDataMapper.deleteByRecvId(recvId);
	}

	@Override
	public void transferIposManagerLog(String iposManagerData, long id, String sn) {
		try {
			FastjsonUtil<IposManagerData> fastjsonUtil = new FastjsonUtil<IposManagerData>(IposManagerData.class);
			List<IposManagerData> list = fastjsonUtil.parseToList(iposManagerData);
			batchInsertByRecvId(id, list, sn);
		} catch (Exception e) {
			logger.error("[ERROR]: recvid - " + id);
			logger.error("[ERROR_DETAIL]: " + e);
		}
	}

}
