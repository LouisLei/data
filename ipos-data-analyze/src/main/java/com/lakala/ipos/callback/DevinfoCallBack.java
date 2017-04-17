package com.lakala.ipos.callback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.lakala.ipos.service.DevinfoService;
import com.lakala.msc.client.MSCCallback;
import com.lakala.msc.client.consumer.message.MessageTuple;
import com.lakala.msc.exception.InvalidDataFormatException;

/**
 * 设备信息回调类
 * 
 * @author liguangsheng
 * @date 2016-06-28
 * @see com.lakala.ipos.task
 */
@Component("devinfoCallBack")
public class DevinfoCallBack implements MSCCallback {

	@Autowired
	@Qualifier("devinfoService")
	private DevinfoService devinfoService;

	@Override
	public boolean doAction(MessageTuple messageTuple) throws InvalidDataFormatException, Exception {
		String message = new String(messageTuple.getMessage());
		devinfoService.transfer(message); // 处理接收到的数据
		return true;
	}

}