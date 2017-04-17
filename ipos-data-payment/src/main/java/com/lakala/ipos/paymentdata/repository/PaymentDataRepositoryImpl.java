package com.lakala.ipos.paymentdata.repository;

import java.sql.Timestamp;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;

/**
 * MongoDB收单数据自定义操作接口实现
 * 
 * @author Wangyu
 * @date 2016/6/28
 *
 */
public class PaymentDataRepositoryImpl implements PaymentDataRepositoryCustom {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public void updateField(String actionId, JSONObject jsonTemp){
		BasicDBObject dbDoc = new BasicDBObject();
		BasicDBObject dbTemp = new BasicDBObject();
		Iterator<String> it = jsonTemp.keySet().iterator();
		while(it.hasNext()){
			String key = (String)it.next();
			if(jsonTemp.get(key) != null){
				dbTemp.put(key, jsonTemp.get(key));
			}
		}
		dbTemp.put("updateTime", new Timestamp(System.currentTimeMillis()));
		dbDoc.put("$set", dbTemp);
		mongoTemplate.upsert(Query.query(Criteria.where("actionId").is(actionId)), Update.fromDBObject(dbDoc),
				"payment");
	}

}
