package com.kellan.demo.utils.enums;

/**
 * 统一错误枚举
 * @author: Kellan_Song
 * @date: 2019-07-18 09:09
 **/
public enum ExceptionEnum {

    DATA_ERROR(111, "数据有误")
    ,PARAMS_ERROR(999, "缺少参数")




    ,SYSTEM_ERROR(201, "系统异常，操作失败");

    private Integer errorCode;//错误码
    private String message;//错误信息

    private ExceptionEnum(Integer errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

}
