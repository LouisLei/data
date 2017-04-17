package com.lakala.ipos.repository;

import com.lakala.ipos.document.DevinfoDocument;

/**
 * MongoDB设备信息自定义操作接口
 * 
 * @author liguangsheng
 * @see DevinfoRepository
 */
public interface DevinfoRepositoryCustom {
	public void insertOne(DevinfoDocument doc);
}
