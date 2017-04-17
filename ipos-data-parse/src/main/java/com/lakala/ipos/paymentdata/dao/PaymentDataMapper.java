package com.lakala.ipos.paymentdata.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lakala.ipos.paymentdata.model.PaymentData;

public interface PaymentDataMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PaymentData record);

    int insertSelective(PaymentData record);

    PaymentData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PaymentData record);

	int updateByPrimaryKey(PaymentData record);

	void insertBatchByRecvId(@Param("recvId") Long recvId, @Param("list") List<PaymentData> list, @Param("sn") String sn);

	void deleteByRecvId(long recvId);
}