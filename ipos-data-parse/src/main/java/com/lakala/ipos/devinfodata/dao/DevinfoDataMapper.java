package com.lakala.ipos.devinfodata.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lakala.ipos.devinfodata.model.DevinfoData;


public interface DevinfoDataMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DevinfoData record);

    int insertSelective(DevinfoData record);

    DevinfoData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DevinfoData record);

	int updateByPrimaryKey(DevinfoData record);

	void insertBatchByRecvId(@Param("recvId") Long recvId, @Param("list") List<DevinfoData> list, @Param("sn") String sn);

	void deleteByRecvId(Long recvId);
}