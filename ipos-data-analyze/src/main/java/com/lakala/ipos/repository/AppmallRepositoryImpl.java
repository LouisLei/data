package com.lakala.ipos.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.lakala.ipos.document.AppmallDocument;

@Repository
public class AppmallRepositoryImpl implements AppmallRepository {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public AppmallRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void updateFirst(Query query, Update update, Class<AppmallDocument> class1) {
		// TODO Auto-generated method stub
		mongoTemplate.updateFirst(query, update, AppmallDocument.class);
	}

	@Override
	public boolean exists(Query query, Class<AppmallDocument> clazz) {
		// TODO Auto-generated method stub
		return mongoTemplate.exists(query, clazz);
	}

	@Override
	public void insert(AppmallDocument appmallDocument) {
		// TODO Auto-generated method stub
		mongoTemplate.insert(appmallDocument);
	}
	
}
