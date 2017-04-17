package com.lakala.ipos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.lakala.ipos.document.IposmgrDocument;

/**
 * MongoDB IPOS管家操作操作接口
 * 
 * @author liguangsheng
 * @date 2016-06-28
 */
public interface IposmgrRepository extends IposmgrRepositoryCustom, MongoRepository<IposmgrDocument, String> {
	@Query("{'actionId': ?0}")
	IposmgrDocument findByActionId(String actionId);
}
