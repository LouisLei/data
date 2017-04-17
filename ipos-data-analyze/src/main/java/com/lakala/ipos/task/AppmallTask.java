package com.lakala.ipos.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.lakala.msc.client.MSCCallback;
import com.lakala.msc.client.consumer.LakalaConsumerGroup;

@Component("appmallTask")
public class AppmallTask implements InitializingBean,DisposableBean {

	private static final Logger logger = LogManager.getLogger(AppmallTask.class);

	@Autowired
	@Qualifier("appmallCallBack")
	private MSCCallback mscCallback;
	private LakalaConsumerGroup group = null;

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		try {
			if (group != null) {
				group.destoryConsumerGroup();
			}
		} catch (Exception e) {
			logger.error("kafka异常信息", e);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		try {
			String filePath = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "/properties/appmall"; // 加载classpath路径
			group = new LakalaConsumerGroup(filePath, mscCallback);
			group.startReceive();
		} catch (Exception e) {
			logger.error("kafka异常信息", e);
		}
	}
}
