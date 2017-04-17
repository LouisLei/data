package com.lakala.ipos.service;

import java.util.List;

import com.lakala.ipos.document.AppmallDocument;
import com.lakala.ipos.document.ReceiverDocument;

public interface AppmallService {

	public void transformReceivedData(String receivedDataJson);
	
	public void convertToAppmall(String appmallJson, ReceiverDocument receiverDocument) throws Exception;
	
	public void batchSaveOrUpdate(List<AppmallDocument> list, ReceiverDocument receiverDocument);
	
	public void saveOrUpdate(AppmallDocument appmallDocument, ReceiverDocument receiverDocument) throws Exception;

}
