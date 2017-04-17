package com.lakala.ipos.callback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lakala.ipos.service.AppmallService;
import com.lakala.msc.client.MSCCallback;
import com.lakala.msc.client.consumer.message.MessageTuple;
import com.lakala.msc.exception.InvalidDataFormatException;

@Component("appmallCallBack")
public class AppmallCallBack implements MSCCallback {
	
	@Autowired
	private AppmallService appmallService;
	
	@Override
	public boolean doAction(MessageTuple messageTuple) throws InvalidDataFormatException, Exception {
		String message = new String(messageTuple.getMessage());
		appmallService.transformReceivedData(message); //处理接收到的数据
		return true;
	}

}
