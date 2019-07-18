package com.kellan.demo.model;

import com.kellan.demo.utils.entitys.Entity;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户实体
 * @author: Kellan_Song
 * @date: 2019-07-17 15:27
 **/
public class User extends Entity{

    @ApiModelProperty(value = "人员ID")
    private Long id;
    @ApiModelProperty(value = "姓名")
    private String name; //姓名
    @ApiModelProperty(value = "性别：0女 1男")
    private Boolean sex; //0女 1男

    public User(String name, Boolean sex) {
        this.name = name;
        this.sex = sex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }
}