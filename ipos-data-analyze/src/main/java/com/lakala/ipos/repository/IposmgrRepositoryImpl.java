package com.lakala.ipos.repository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.lakala.ipos.document.IposmgrEventDocument;
import com.mongodb.BasicDBObject;

/**
 * MongoDB IPOS管家操作操作自定义接口实现
 * 
 * @author liguangsheng
 * @date 2016-06-28
 */
public class IposmgrRepositoryImpl implements IposmgrRepositoryCustom {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void addEvent(String actionId, String eventId, IposmgrEventDocument edoc) {
		BasicDBObject dbDoc = new BasicDBObject();
		dbDoc.append("$set", new BasicDBObject().append(eventId, edoc));
		mongoTemplate.upsert(Query.query(Criteria.where("actionId").is(actionId)), Update.fromDBObject(dbDoc),
				"iposmgr");
	}

	@Override
	public void updateUpdateTime(String actionId) {
		BasicDBObject dbDoc = new BasicDBObject();
		dbDoc.append("$set", new BasicDBObject().append("updateTime", new Date()));
		mongoTemplate.upsert(Query.query(Criteria.where("actionId").is(actionId)), Update.fromDBObject(dbDoc),
				"iposmgr");
	}

}
