package com.kellan.demo.model;

import com.kellan.demo.utils.entitys.Entity;
import io.swagger.annotations.ApiModelProperty;
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
public class User extends Entity{

    @ApiModelProperty(value = "人员ID")
    private Long id;
    @ApiModelProperty(value = "姓名")
    private String name; //姓名
    @ApiModelProperty(value = "性别：0女 1男")
    private Boolean sex; //0女 1男
    private Date createTime;//创建时间
    private Date lastTime;//最近操作时间

    public User() {
    }

    public User(String name, Boolean sex) {
        this.name = name;
        this.sex = sex;
    }

}