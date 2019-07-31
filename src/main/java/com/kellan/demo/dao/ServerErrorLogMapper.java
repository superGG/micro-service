package com.kellan.demo.dao;

import com.kellan.demo.model.ServerErrorLog;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerErrorLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ServerErrorLog record);

    int insertSelective(ServerErrorLog record);

    ServerErrorLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServerErrorLog record);

    int updateByPrimaryKeyWithBLOBs(ServerErrorLog record);

    int updateByPrimaryKey(ServerErrorLog record);
}