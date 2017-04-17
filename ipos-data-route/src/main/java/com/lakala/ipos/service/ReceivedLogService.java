package com.lakala.ipos.service;

import com.lakala.ipos.model.ReceivedLog;
/**
 * 数据接收服务
 * 
 * @author Loki_yb
 * 
 * @since 2016年3月11日 上午10:12:20
 * 
 **/

public interface ReceivedLogService {
	
	/**
	 * 功能: 
	 * 1.解析消息json字符串
	 * 2.将dataPak解压缩成json字符串
	 * 3.提取json串中各业务类型的数据，并组装业务数据Json
	 * 4.将数据发送到kafka中
	 * @param message
	 *            接收的压缩包
	 */
	public void dealData(String message);
	
	/**
	 * 功能：
	 * 1.将json字符串转换成java对象
	 * @param receivedLogJson	json字符串
	 * @return java对象
	 * @throws Exception 
	 */
	ReceivedLog transformReceivedLog(String receivedLogJson) throws Exception;
	
}
