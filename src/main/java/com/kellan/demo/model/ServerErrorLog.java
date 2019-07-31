package com.kellan.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 系统异常记录实体
 * @author: Kellan_Song
 * @date: 2019-07-17 15:27
 **/
@ToString
@Setter
@Getter
@Accessors(chain = true)
public class ServerErrorLog {
    private Integer id;

    private Integer appType;

    private Date createTime;

    private Integer errorCode;

    private String ip;

    private String interfaceAdr;

    private String serverVersion;

    private String message;

    private String params;

}