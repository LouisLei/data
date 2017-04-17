package com.lakala.ipos.callback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.lakala.ipos.service.IposmgrService;
import com.lakala.msc.client.MSCCallback;
import com.lakala.msc.client.consumer.message.MessageTuple;
import com.lakala.msc.exception.InvalidDataFormatException;

/**
 * ipos管家回调类
 * 
 * @author liguangsheng
 * @date 2016-06-28
 */
@Component("iposmgrCallBack")
public class IposmgrCallBack implements MSCCallback {
	@Autowired
	@Qualifier("iposmgrService")
	private IposmgrService iposmgrService;

	@Override
	public boolean doAction(MessageTuple messageTuple) throws InvalidDataFormatException, Exception {
		String message = new String(messageTuple.getMessage());
		iposmgrService.transfer(message); // 处理接收到的数据
		return true;
	}

}
