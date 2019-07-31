package com.kellan.demo.dao;

import com.kellan.demo.model.TUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    List<TUser> findAll();
}