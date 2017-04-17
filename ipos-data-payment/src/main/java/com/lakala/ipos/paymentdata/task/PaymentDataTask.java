package com.lakala.ipos.paymentdata.task;

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
import com.alibaba.fastjson.JSONObject;
import com.lakala.ipos.paymentdata.callback.PaymentDataCallBack;
import com.lakala.ipos.paymentdata.service.PaymentDataService;
import com.lakala.ipos.paymentdata.util.GzipUtils;
import com.lakala.msc.client.consumer.LakalaConsumerGroup;

/**
 * 收单数据接收
 * 
 * @author Wangyu
 * @date 2016年6月28日
 *
 */
@Component("paymentDataTask")
@SuppressWarnings("unused")
public class PaymentDataTask {

    private static final Logger logger = LogManager.getLogger(PaymentDataTask.class);
    private LakalaConsumerGroup group  = null;

    @Autowired
    @Qualifier("paymentDataCallBack")
    private PaymentDataCallBack mscCallback;

    @Autowired
    private ApplicationContext  ctx;

    @Autowired
    @Qualifier("paymentDataService")
    private PaymentDataService  paymentDataService;

    @PostConstruct
    public void invoke() {

        try {
            String filePath = Thread.currentThread().getContextClassLoader().getResource("").getPath() 
                + "/properties/payment"; // 加载classpath路径
            LakalaConsumerGroup paymentDataGroup = new LakalaConsumerGroup(filePath, mscCallback);
            paymentDataGroup.startReceive();

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
