package com.lakala.ipos.receivelog.dao;

import com.lakala.ipos.receivelog.model.ReceivedLog;


public interface ReceivedLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReceivedLog record);

    int insertSelective(ReceivedLog record);

    ReceivedLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReceivedLog record);

    int updateByPrimaryKeyWithBLOBs(ReceivedLog record);

	int updateByPrimaryKey(ReceivedLog record);

	Long insertAndGetId(ReceivedLog receivedLog);
}