package com.lakala.ipos.receivelog.service;

import com.lakala.ipos.receivelog.model.ReceivedLog;
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
     * 通过用户id 查询taken码
     * 
     * @param id
     * @return
     */
	ReceivedLog selectById(Long id);

	public void insertAndGetId(ReceivedLog receivedLog);

	ReceivedLog transferReceivedLog(String receivedLogJson);
	
}
