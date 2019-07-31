package com.kellan.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 请求记录实体
 * @author: Kellan_Song
 * @date: 2019-07-17 15:27
 **/
@ToString
@Setter
@Getter
@Accessors(chain = true)
public class RequestRecord {
    private Long id;

    private String apiAdr;

    private Date createTime;

    private String ip;

    private Long duration;

    private String request;

    private String response;

    public RequestRecord() {
    }

    public RequestRecord(String request, String response, Date createTime, String ip, String apiAdr, Long duration) {
        super();
        this.request = request;
        this.response = response;
        this.createTime = createTime;
        this.ip = ip;
        this.apiAdr = apiAdr;
        this.duration = duration;
    }
}