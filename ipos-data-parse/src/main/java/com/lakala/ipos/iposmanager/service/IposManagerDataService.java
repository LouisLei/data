package com.lakala.ipos.iposmanager.service;

import java.util.List;

import com.lakala.ipos.iposmanager.model.IposManagerData;

public interface IposManagerDataService {
	public void batchInsertByRecvId(long recvId, List<IposManagerData> list, String sn) throws Exception;

	public void deleteByRecvId(long recvId) throws Exception;

	public void transferIposManagerLog(String iposManagerData, long id, String sn);
}
