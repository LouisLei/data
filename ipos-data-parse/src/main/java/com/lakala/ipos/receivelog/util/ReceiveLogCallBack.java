package com.lakala.ipos.receivelog.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.lakala.ipos.receivelog.service.CommonService;
import com.lakala.msc.client.MSCCallback;
import com.lakala.msc.client.consumer.message.MessageTuple;
import com.lakala.msc.exception.InvalidDataFormatException;

@Component("receiveLogCallBack")
public class ReceiveLogCallBack implements MSCCallback {

	@Autowired
	@Qualifier("mqCommonService")
	private CommonService commonService;
	
	@Override
	public boolean doAction(MessageTuple messageTuple) throws InvalidDataFormatException, Exception {
		String message = new String(messageTuple.getMessage());
		commonService.transfer(message); //处理接收到的数据
		return true;
	}

}
