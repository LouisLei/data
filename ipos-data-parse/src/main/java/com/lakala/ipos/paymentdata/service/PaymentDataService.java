package com.lakala.ipos.paymentdata.service;

import java.util.List;

import com.lakala.ipos.paymentdata.model.PaymentData;

public interface PaymentDataService {
	
	public void batchInsertByRecvId(long recvId, List<PaymentData> list, String sn) throws Exception;

	public void deleteByRecvId(long recvId) throws Exception;
	
	public void transferPaymentLog(String appmallData, long id, String sn);
	
}
