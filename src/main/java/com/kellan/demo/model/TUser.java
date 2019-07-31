package com.kellan.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 用户实体
 * @author: Kellan_Song
 * @date: 2019-07-17 15:27
 **/
@ToString
@Setter
@Getter
@Accessors(chain = true)
public class TUser {
    private Integer id;

    private String name;

    private Boolean sex;

    private Date createTime;

    private Date lastTime;

}