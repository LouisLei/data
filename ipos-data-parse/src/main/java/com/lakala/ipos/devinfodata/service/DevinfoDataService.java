package com.lakala.ipos.devinfodata.service;

import java.util.List;

import com.lakala.ipos.devinfodata.model.DevinfoData;

/**
 * @author liguangsheng
 *
 */      
public interface DevinfoDataService {    

	public void batchInsertByRecvId(long recvId, List<DevinfoData> list, String sn) throws Exception;
	
	public void insertByRecvId(long recvId, DevinfoData devinfoData) throws Exception;
	
	public void insert(DevinfoData devinfoData) throws Exception;

	public void deleteByRecvId(long recvId) throws Exception;
	
	public void transferDeviceLog(String deviceData, long id, String sN);

}
