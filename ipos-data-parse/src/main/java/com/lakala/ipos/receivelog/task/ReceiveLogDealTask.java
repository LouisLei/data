package com.lakala.ipos.receivelog.task;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.lakala.msc.client.MSCCallback;
import com.lakala.msc.client.consumer.LakalaConsumerGroup;

@Component("receiveLogDealTask")
public class ReceiveLogDealTask {

	private static final Logger logger = LogManager.getLogger(ReceiveLogDealTask.class);

	@Autowired
	@Qualifier("receiveLogCallBack")
	private MSCCallback mscCallback;
	private LakalaConsumerGroup group = null;

	@PostConstruct
	public void invoke() {
		try {
			String filePath = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "/properties"; // 加载classpath路径
			group = new LakalaConsumerGroup(filePath, mscCallback);
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
