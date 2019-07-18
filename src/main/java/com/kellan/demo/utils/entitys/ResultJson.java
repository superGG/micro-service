package com.kellan.demo.utils.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;


@ApiModel(description= "返回响应数据")
public class ResultJson<T> {
	@ApiModelProperty(value = "请求是否成功,true:是,false:否")
    private Boolean state = true;
	@ApiModelProperty(value = "前端可忽略此值")
    private String flag;
	@ApiModelProperty(value = "消息提示信息")
    private String message;
	@ApiModelProperty(value = "返回结果集")
    private T result;
	@ApiModelProperty(value = "响应状态码,常用状态码:200-成功;201-系统异常,操作失败;400-账号已退出登录;401-token已过期")
    private Integer errorCode = 200;//状态码
    @JsonIgnore
    private transient Map<String, Object> resultMap;

	public ResultJson() {
		super();
	}

	public ResultJson(String msg) {
    	this.message = msg;
	}
	public ResultJson(Boolean state, String message) {
		super();
		this.state = state;
		this.message = message;
	}
    
	public Map<String, Object> getResultMap() {
		if (resultMap == null) resultMap = new HashMap<>();
    	return resultMap;
    }
    
	public boolean isState() {
		return state;
	}
	public ResultJson setState(boolean state) {
		this.state = state;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public ResultJson setMessage(String message) {
		this.message = message;
		return this;
	}
	public T getResult() {
		if(resultMap != null && resultMap.keySet().size() != 0) {
			result = (T) resultMap;
		}
		return result;
	}
	public ResultJson setResult(T result) {
		this.result = result;
		return this;
	}
	public String getFlag() {
		return flag;
	}
	public ResultJson setFlag(String flag) {
		this.flag = flag;
		return this;
	}
    public int getErrorCode() {
		return errorCode;
	}
	public ResultJson setErrorCode(int errorCode) {
		this.errorCode = errorCode;
		return this;
	}

	public ResultJson setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
		return this;
	}

	public Boolean getState() {
		return state;
	}

	public ResultJson setState(Boolean state) {
		this.state = state;
		return this;
	}
}
