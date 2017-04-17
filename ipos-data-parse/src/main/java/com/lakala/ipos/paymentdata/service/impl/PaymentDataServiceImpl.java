package com.lakala.ipos.paymentdata.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lakala.ipos.common.util.FastjsonUtil;
import com.lakala.ipos.paymentdata.dao.PaymentDataMapper;
import com.lakala.ipos.paymentdata.model.PaymentData;
import com.lakala.ipos.paymentdata.service.PaymentDataService;

@Service("mysqlPaymentLogService")
public class PaymentDataServiceImpl implements PaymentDataService {

	private final Logger logger = LogManager.getLogger(PaymentDataServiceImpl.class);
	
	@Autowired
	private PaymentDataMapper paymentDataMapper;

	public PaymentDataServiceImpl() {
	}
	
	@Transactional(readOnly = false)
	public void batchInsertByRecvId(long recvId, List<PaymentData> list, String sn) throws Exception {
		paymentDataMapper.insertBatchByRecvId(recvId, list, sn);
	}

	@Override
	public void transferPaymentLog(String paymentData, long id, String sn) {
		try {
			FastjsonUtil<PaymentData> fastjsonUtil = new FastjsonUtil<PaymentData>(PaymentData.class);
			List<PaymentData> list = fastjsonUtil.parseToList(paymentData);// 解析Json数组字符串
			batchInsertByRecvId(id,list,sn);
		} catch (Exception e) {
			logger.error("处理收单数据异常——{recvId:"+id+"}");
			logger.error("处理收单数据异常", e);
		}
	}
	
	@Transactional(readOnly = false)
	@Override
	public void deleteByRecvId(long recvId) throws Exception {
		paymentDataMapper.deleteByRecvId(recvId);
	}
}
