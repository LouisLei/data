package com.lakala.ipos.common.service;

import com.lakala.ipos.common.model.SystemProperty;

public interface SystemPropertyService {
	
	SystemProperty selectBybizType(String bizType);

	public void kafkaProducer(String dd);
	
	
}
