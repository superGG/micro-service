package com.kellan.demo.utils.entitys;


import com.kellan.demo.utils.enums.ExceptionEnum;

/**
 * 自定义错误类型
 * @author Kellan_Song
 * @createTime 2019年3月1日
 */
public class ExceptionEntity extends SecurityException {
	
	private static final long serialVersionUID = 1L;
	
	private Integer errorCode;//错误码
	private String errorMsg;//错误信息
	private String params;//参数
	
	public ExceptionEntity() {
		super();
	}
	public ExceptionEntity(String message, Throwable cause) {
		super(message, cause);
		this.errorMsg = message;
	}

	/**
	 * 自定义错误信息
	 * @param msg 错误信息
	 * @param errorCode 错误编码
	 * @param params 相关参数
	 */
	public ExceptionEntity(String msg, Integer errorCode, String params) {
		super(msg, new Throwable(errorCode.toString()));
		this.params = params;
	}

	/**
	 *	通过统一的错误枚举类型
	 * {@link ExceptionEnum}
	 */
	public ExceptionEntity(ExceptionEnum errorEnum) {
		super(errorEnum.getMessage(), new Throwable(errorEnum.getErrorCode().toString()));
	}
	/**
	 *	通过统一的错误枚举类型
	 * @param msg 自定义拼接错误信息
	 * {@link ExceptionEnum}
	 */
	public ExceptionEntity(ExceptionEnum errorEnum, String msg) {
		super(errorEnum.getMessage() + "，" + msg, new Throwable(errorEnum.getErrorCode().toString()));
	}


	public ExceptionEntity(String message, Integer errorCode) {
		super(message, new Throwable(errorCode.toString()));
	}
	public ExceptionEntity(String s) {
		super(s);
	}
	public ExceptionEntity(Throwable cause) {
		super(cause);
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	
}
