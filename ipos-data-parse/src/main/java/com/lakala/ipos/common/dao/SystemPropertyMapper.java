package com.lakala.ipos.common.dao;

import com.lakala.ipos.common.model.SystemProperty;


public interface SystemPropertyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemProperty record);

    int insertSelective(SystemProperty record);

    SystemProperty selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemProperty record);

	int updateByPrimaryKey(SystemProperty record);

	SystemProperty selectBybizType(String bizType);
}