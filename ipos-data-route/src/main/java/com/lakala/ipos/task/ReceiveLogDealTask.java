package com.lakala.ipos.task;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lakala.ipos.service.ReceivedLogService;
import com.lakala.msc.client.MSCCallback;
import com.lakala.msc.client.consumer.LakalaConsumerGroup;
import com.lakala.msc.client.consumer.message.MessageTuple;
import com.lakala.msc.exception.InvalidDataFormatException;

@Component("receiveLogDealTask")
public class ReceiveLogDealTask {

	private static final Logger logger = LogManager.getLogger(ReceiveLogDealTask.class);
	
	@Autowired
	private ReceivedLogService receivedLogService;
	
	private LakalaConsumerGroup group = null;

	@PostConstruct
	public void invoke() {
		try {
			String filePath = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "/properties"; // 加载classpath路径
			group = new LakalaConsumerGroup(filePath, new MSCCallback() {
				
				@Override
				public boolean doAction(MessageTuple messageTuple) throws InvalidDataFormatException, Exception {
					// TODO Auto-generated method stub
					String message = new String(messageTuple.getMessage());
					receivedLogService.dealData(message); //处理接收到的数据
					return true;
				}
			});
			group.startReceive();
		} catch (Exception e) {
			logger.error("kafka异常信息", e);
		}
	}

	@PreDestroy
	public void destory() {
		try {
			if (group != null) {
				group.destoryConsumerGroup();
			}
		} catch (Exception e) {
			logger.error("kafka异常信息", e);
		}
	}
}
