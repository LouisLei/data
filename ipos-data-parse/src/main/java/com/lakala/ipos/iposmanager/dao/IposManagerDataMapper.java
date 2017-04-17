package com.lakala.ipos.iposmanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lakala.ipos.iposmanager.model.IposManagerData;

public interface IposManagerDataMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IposManagerData record);

    int insertSelective(IposManagerData record);

    IposManagerData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IposManagerData record);

    int updateByPrimaryKey(IposManagerData record);
    
	void insertBatchByRecvId(@Param("recvId") Long recvId, @Param("list") List<IposManagerData> list,
			@Param("sn") String sn);

	void deleteByRecvId(long recvId);
}