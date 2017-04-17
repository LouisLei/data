package com.lakala.ipos.appmalldata.service;

import java.util.List;

import com.lakala.ipos.appmalldata.model.AppmallData;

public interface AppmallDataService {

	public void batchInsertByRecvId(long recvId, List<AppmallData> list, String sn) throws Exception;
	
	public void deleteByRecvId(long recvId) throws Exception;

	public void transferAppmallLog(String appmallData,long id, String sn);
	
}
