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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lakala.ipos.callback.DevinfoCallBack;
import com.lakala.ipos.service.DevinfoService;
import com.lakala.ipos.util.GzipUtils;
import com.lakala.msc.client.consumer.LakalaConsumerGroup;

/**
 * 设备信息消息接收
 * 
 * @author liguangsheng
 * @date 2016-06-28
 */
@Component("devinfoTask")
@SuppressWarnings("unused")
public class DevinfoTask {

	private static final Logger logger = LogManager.getLogger(DevinfoTask.class);
	private LakalaConsumerGroup group = null;

	@Autowired
	@Qualifier("devinfoCallBack")
	private DevinfoCallBack mscCallback;

	@Autowired
	private ApplicationContext ctx;

	@Autowired
	@Qualifier("devinfoService")
	private DevinfoService devinfoService;

	private void showBeans() {
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		logger.info("Spring Boot Beans({}):", beanNames.length);
		for (String beanName : beanNames) {
			logger.info(beanName);
		}
	}

	private void test() {
		String dataPak = "[{\n" + "		\"timeStamp\": \"2016-06-17 11:26:18.216\",\n"
				+ "		\"CID\": \"124950815\",\n" + "		\"MNC\": \"01\",\n" + "		\"MCC\": \"460\",\n"
				+ "		\"LAC\": \"33239\",\n" + "		\"level\": \"51%\",\n" + "		\"maxFreq\": \"1094400\",\n"
				+ "		\"cpuName\": \"0\",\n" + "		\"minFreq\": \"200000\",\n" + "		\"curFreq\": \"1094400\",\n"
				+ "		\"availMem\": \"400 MB\",\n" + "		\"totalMem\": \"0.88 GB\",\n"
				+ "		\"totalSdcard\": \"4.71 GB\",\n" + "		\"availSdcard\": \"4.34 GB\"\n" + "	}, {\n"
				+ "		\"timeStamp\": \"2016-06-18 11:36:28.216\",\n" + "		\"CID\": \"124953745\",\n"
				+ "		\"MNC\": \"01\",\n" + "		\"MCC\": \"460\",\n" + "		\"LAC\": \"33239\",\n"
				+ "		\"level\": \"89%\",\n" + "		\"maxFreq\": \"1094400\",\n" + "		\"cpuName\": \"0\",\n"
				+ "		\"minFreq\": \"200000\",\n" + "		\"curFreq\": \"1094400\",\n"
				+ "		\"availMem\": \"400 MB\",\n" + "		\"totalMem\": \"0.88 GB\",\n"
				+ "		\"totalSdcard\": \"4.71 GB\",\n" + "		\"availSdcard\": \"4.34 GB\"\n" + "	}]\n";

		String dataPakGzip = GzipUtils.compressMessage(dataPak);
		String message = "{\n" + "	\"serialNo\": \"YP000000000001\",\n"
				+ "	\"sentTime\": \"2016-06-18 11:36:28.216\",\n" + "	\"recvTime\": \"2016-06-18 11:36:28.216\",\n"
				+ "}\n";
		JSONObject json = JSON.parseObject(message);
		json.put("dataPak", dataPakGzip);

		devinfoService.transfer(json.toJSONString());
	}

	@PostConstruct
	public void invoke() {
		showBeans();
		// test();

		try {
			if (true) {
				String filePath = Thread.currentThread().getContextClassLoader().getResource("").getPath()
						+ "/properties/devinfo"; // 加载classpath路径
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
