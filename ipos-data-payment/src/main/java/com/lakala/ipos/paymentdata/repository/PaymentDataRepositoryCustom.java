package com.lakala.ipos.paymentdata.repository;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;

/**
 * MongoDB收单信息自定义操作接口
 * 
 * @author Wangyu
 * @date 2016/6/28
 *
 */
@Repository
public interface PaymentDataRepositoryCustom {
	
	public void updateField(String actionId, JSONObject data);

}
