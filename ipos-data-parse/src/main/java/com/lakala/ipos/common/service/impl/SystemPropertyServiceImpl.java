package com.lakala.ipos.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lakala.ipos.common.dao.SystemPropertyMapper;
import com.lakala.ipos.common.model.SystemProperty;
import com.lakala.ipos.common.service.SystemPropertyService;

@Service("mysqlSystemPropertyService")
public class SystemPropertyServiceImpl implements SystemPropertyService {

	@Autowired
	private SystemPropertyMapper systemPropertyMapper;
	
	@Override
	public SystemProperty selectBybizType(String bizType) {
		// TODO Auto-generated method stub
		return systemPropertyMapper.selectBybizType(bizType);
	}

	@Override
	public void kafkaProducer(String dd) {
		
	}

}
