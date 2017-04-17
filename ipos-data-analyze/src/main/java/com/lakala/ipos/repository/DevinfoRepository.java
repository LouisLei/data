package com.lakala.ipos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lakala.ipos.document.DevinfoDocument;

/**
 * MongoDB设备信息操作接口
 * 
 * @author liguangsheng
 * @date 2-16-06-28
 */
@Repository
public interface DevinfoRepository extends DevinfoRepositoryCustom, MongoRepository<DevinfoDocument, String> {
}
