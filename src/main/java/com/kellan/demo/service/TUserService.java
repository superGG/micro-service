package com.kellan.demo.service;

import com.kellan.demo.model.TUser;

import java.util.List;

/**
 * @author: Kellan_Song
 * @date: 2019-07-30 17:15
 **/
public interface TUserService {

    int insert(TUser record);

    List<TUser> getAll();
}
