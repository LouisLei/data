package com.lakala.ipos.appmalldata.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.lakala.ipos.appmalldata.model.AppmallData;


public interface AppmallDataMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppmallData record);

    int insertSelective(AppmallData record);

    AppmallData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppmallData record);

	int updateByPrimaryKey(AppmallData record);

	void insertBatchByRecvId(@Param("recvId") Long recvId, @Param("list") List<AppmallData> list, @Param("sn") String sn);

	void deleteByRecvId(long recvId);
}