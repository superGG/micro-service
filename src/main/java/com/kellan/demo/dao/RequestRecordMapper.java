package com.kellan.demo.dao;

import com.kellan.demo.model.RequestRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RequestRecord record);

    int insertSelective(RequestRecord record);

    RequestRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RequestRecord record);

    int updateByPrimaryKeyWithBLOBs(RequestRecord record);

    int updateByPrimaryKey(RequestRecord record);
}