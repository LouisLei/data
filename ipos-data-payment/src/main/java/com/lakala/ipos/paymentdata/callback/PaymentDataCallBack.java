package com.lakala.ipos.paymentdata.callback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.lakala.ipos.paymentdata.service.PaymentDataService;
import com.lakala.msc.client.MSCCallback;
import com.lakala.msc.client.consumer.message.MessageTuple;
import com.lakala.msc.exception.InvalidDataFormatException;

/**
 * 收单数据回调
 * 
 * @author Wangyu
 * @date 2016/6/28
 *
 */
@Component("paymentDataCallBack")
public class PaymentDataCallBack implements MSCCallback {
	
	@Autowired
	@Qualifier("paymentDataService")
	private PaymentDataService paymentDataService;
	
	@Override
	public boolean doAction(MessageTuple messageTuple) throws InvalidDataFormatException, Exception {
		String message = new String(messageTuple.getMessage());
		paymentDataService.transfer(message); //处理接收到的数据
		return true;
	}

}
