package com.lakala.ipos.task;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lakala.ipos.callback.IposmgrCallBack;
import com.lakala.ipos.service.IposmgrService;
import com.lakala.msc.client.consumer.LakalaConsumerGroup;

/**
 * IPOS管家消息接收
 * 
 * @author liguangsheng
 * @date 2016-06-28
 */
@Component("iposmgrTask")
@SuppressWarnings("unused")
public class IposmgrTask {

	private static final Logger logger = LogManager.getLogger(IposmgrTask.class);
	private LakalaConsumerGroup group = null;

	@Autowired
	@Qualifier("iposmgrCallBack")
	private IposmgrCallBack mscCallback;

	@Autowired
	private ApplicationContext ctx;

	@Autowired
	@Qualifier("iposmgrService")
	private IposmgrService iposmgrService;

	private void showBeans() {
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		logger.info("Spring Boot Beans({}):", beanNames.length);
		for (String beanName : beanNames) {
			logger.info(beanName);
		}
	}

	private void test() {
		String message = "{\n" + "	\"dataPak\": [{\n" + "		\"appId\": \"com.lakala.cloudpos.poskeeper\",\n"
				+ "		\"actionId\": \"MDM4MjM2OTljMWI1NGQwM2E1NWJhZmJmZGExNTZjYWI\",\n"
				+ "		\"eventId\": \"E018\",\n" + "		\"actionName\": \"充值余额\",\n"
				+ "		\"status\": \"FAILURE\",\n" + "		\"timeStamp\": \"2016-06-17 16:23:35.837000\",\n"
				+ "		\"reason\": \"未知错误\"\n" + "	}, {\n" + "		\"appId\": \"com.lakala.cloudpos.poskeeper\",\n"
				+ "		\"actionId\": \"Njc4MzI2NzRjM2RhNDQyZmViZjdlYzI1NGM1NTRiNjM\",\n"
				+ "		\"eventId\": \"E017\",\n" + "		\"actionName\": \"充值余额\",\n"
				+ "		\"timeStamp\": \"2016-06-17 16:23:40.718000\",\n" + "	}, {\n"
				+ "		\"appId\": \"com.lakala.cloudpos.poskeeper\",\n"
				+ "		\"actionId\": \"Njc4MzI2NzRjM2RhNDQyZmViZjdlYzI1NGM1NTRiNjM\",\n"
				+ "		\"eventId\": \"E018\",\n" + "		\"actionName\": \"充值余额\",\n"
				+ "		\"status\": \"SUCCESS\",\n" + "		\"timeStamp\": \"2016-06-17 16:24:03.233000\",\n"
				+ "		\"orderNum\": \"CZ20160617003013\",\n" + "	}],\n" + "	\"serialNo\": \"YP760000000231\",\n"
				+ "	\"sentTime\": \"2016-06-18 11:36:28.216\",\n" + "	\"recvTime\": \"2016-06-18 11:36:28.216\",\n"
				+ "}\n";
		iposmgrService.transfer(message);
	}

	@PostConstruct
	public void invoke() {
		// showBeans();
		// test();

		try {
			if (true) {
				String filePath = Thread.currentThread().getContextClassLoader().getResource("").getPath()
						+ "/properties/iposmgr"; // 加载classpath路径
				LakalaConsumerGroup devinfoGroup = new LakalaConsumerGroup(filePath, mscCallback);
				devinfoGroup.startReceive();
			}

		} catch (Exception e) {
			e.printStackTrace();
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
