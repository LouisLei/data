package com.lakala.ipos.paymentdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.lakala.ipos.paymentdata.document.PaymentDataDocument;

/**
 * MongoDB收单数据操作接口
 * 
 * @author Wangyu
 * @date 2016/6/28
 *
 */
@Repository
public interface PaymentDataRepository extends PaymentDataRepositoryCustom, MongoRepository<PaymentDataDocument, String> {
	@Query("{'actionId': ?0}")
	PaymentDataDocument findByActionId(String actionId);

}
