package com.lakala.ipos.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.lakala.ipos.document.DevinfoDocument;

/**
 * MongoDB设备信息自定义操作接口实现
 * 
 * @author liguangsheng
 * @see DevinfoRepository
 * @see DevinfoRepositoryCustom
 */
public class DevinfoRepositoryImpl implements DevinfoRepositoryCustom {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void insertOne(DevinfoDocument doc) {
		mongoTemplate.insert(doc);
	}

}
