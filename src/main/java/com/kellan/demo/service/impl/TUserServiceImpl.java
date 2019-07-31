package com.kellan.demo.service.impl;

import com.kellan.demo.dao.TUserMapper;
import com.kellan.demo.model.TUser;
import com.kellan.demo.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Kellan_Song
 * @date: 2019-07-30 17:16
 **/
@Service
public class TUserServiceImpl implements TUserService {

    @Autowired
    TUserMapper tUserMapper;

    @Override
    public int insert(TUser record) {
        return tUserMapper.insert(record);
    }

    @Override
    public List<TUser> getAll() {
        return tUserMapper.findAll();
    }
}