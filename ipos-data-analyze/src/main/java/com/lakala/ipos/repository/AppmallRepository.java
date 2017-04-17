package com.lakala.ipos.repository;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.lakala.ipos.document.AppmallDocument;

public interface AppmallRepository {

	public void updateFirst(Query query, Update update, Class<AppmallDocument> class1);

	public boolean exists(Query query, Class<AppmallDocument> clazz);

	public void insert(AppmallDocument appmallDocument);
	
}
