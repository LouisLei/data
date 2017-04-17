package com.lakala.ipos.repository;

import com.lakala.ipos.document.IposmgrEventDocument;

/**
 * MongoDB IPOS管家操作操作自定义接口
 * 
 * @author liguangsheng
 * @date 2016-06-28
 */
public interface IposmgrRepositoryCustom {
	public void addEvent(String actionId, String eventId, IposmgrEventDocument edoc);
	
	public void updateUpdateTime(String actionId);
}
