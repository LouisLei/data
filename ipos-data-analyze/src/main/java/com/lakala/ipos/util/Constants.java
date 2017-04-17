package com.lakala.ipos.util;

import java.text.SimpleDateFormat;

/**
 * 公共常量信息
 */
public class Constants {

	public static final SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	/**
	 * 业务类型
	 */
	public static final String DEVINFO_CODE = "A000"; // 基础设备信息业务code
	public static final String PAYMENT_CODE = "A001"; // 收单业务code
	public static final String APPMALL_CODE = "A002"; // 应用商城业务code
	public static final String MANAGER_CODE = "A004"; // ipos管家业务code

	/**
	 * kafka topic
	 */
	public static final String RECEIVE_TOPIC = "appdata"; // 接手端接收到数据的topic
	public static final String DEVINFO_TOPIC = "DevinfoData"; // 基础信息数据的topic
	public static final String PAYMENT_TOPIC = "PaymentData"; // 收单数据的topic
	public static final String APPMALL_TOPIC = "AppmallData"; // 应用商城数据的topic
	public static final String MANAGER_TOPIC = "IposManagerData"; // ipos管家数据的topic

	/**
	 * kafka timeout
	 */
	public static final long RECEIVE_TIMEOUT = 1000; // 获取接收数据包消息超时
	public static final long DEVINFO_TIMEOUT = 1000; // 获取基础信息消息超时
	public static final long PAYMENT_TIMEOUT = 1000; // 获取收单消息超时
	public static final long APPMALL_TIMEOUT = 1000; // 获取应用商城消息超时
	public static final long MANAGER_TIMEOUT = 1000; // 获取ipos管家消息超时

	/**
	 * 数据处理与通信通道类型
	 */
	public static final String DEAl_CHANNEL_TYPE = "DEAl_CHANNEL_TYPE"; // 处理接收数据包通道类型
	public static final String DEAl_BIZ_CHANNEL_TYPE = "DEAl_BIZ_CHANNEL_TYPE"; // 处理业务类型数据通道类型
	public static final String MYSQL_CHANNEL = "0"; // MYSQL处理通道
	public static final String MQ_CHANNEL = "1"; // MQ处理通道

	/**
	 * 从kafka读取信息时的charset
	 */
	public static final String READ_KAFKA_CHARSET = "UTF-8";
}
