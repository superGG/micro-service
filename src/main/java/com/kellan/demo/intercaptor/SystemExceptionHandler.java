package com.kellan.demo.intercaptor;

import com.kellan.demo.dao.ServerErrorLogMapper;
import com.kellan.demo.model.ServerErrorLog;
import com.kellan.demo.utils.commom.StringUtils;
import com.kellan.demo.utils.entitys.ExceptionEntity;
import com.kellan.demo.utils.entitys.ResultJson;
import com.kellan.demo.utils.enums.ExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@ControllerAdvice
public class SystemExceptionHandler {
	
	@Autowired
	ServerErrorLogMapper appErrorLogDao;

	@SuppressWarnings("unchecked")
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResultJson resolveException(HttpServletRequest request, Exception ex) {
		ResultJson resultJson = new ResultJson();
		ServerErrorLog error = new ServerErrorLog();
		if (ex instanceof ApplicationException) {
			resultJson.setState(false);
			resultJson.setMessage(ex.getMessage());
			resultJson.setFlag("");
			resultJson.setResult("");
		}  else if (ex instanceof SecurityException) {
			int errCode = ex.getCause() == null ? 201 : Integer.valueOf(ex.getCause().getMessage());
			resultJson.setErrorCode(errCode);
			resultJson.setState(false);
			resultJson.setMessage(ex.getMessage());
			resultJson.setFlag("");
			resultJson.setResult("");
			log.error("--------发生错误--------");
			log.error("错误信息:"+ex.getMessage());
			log.error("错误编码:"+errCode);
		}  else if (ex instanceof MissingServletRequestParameterException){
			resultJson.setErrorCode(ExceptionEnum.PARAMS_ERROR.getErrorCode());
			resultJson.setState(false);
			resultJson.setMessage("缺少参数："+((MissingServletRequestParameterException) ex).getParameterName());
			resultJson.setFlag("");
			resultJson.setResult("");
		} else if(ex instanceof HttpSessionRequiredException){
			return resultJson;
		} else {
			resultJson.setErrorCode(ExceptionEnum.SYSTEM_ERROR.getErrorCode());
			resultJson.setState(false);
			resultJson.setMessage(ExceptionEnum.SYSTEM_ERROR.getMessage());
			resultJson.setFlag("");
			resultJson.setResult("");
		}
		ex.printStackTrace();
		log.error("--------发生错误--------");
		Map<String, Object> param = new HashMap<>();
		Set<String> keySet = request.getParameterMap().keySet();
		if(!keySet.isEmpty()) {
			for(String key : keySet) {
				param.put(key, (request.getParameter(key).length() > 2000 ? "参数过长，先不显示" : request.getParameter(key)));
			}
		}
		error.setAppType(request.getHeader("clientType") == null ? 4 : Integer.valueOf(request.getHeader("clientType")));
		error.setServerVersion(request.getHeader("serverVersion"));
		error.setErrorCode(resultJson.getErrorCode());
		error.setMessage(resultJson.getMessage());
		error.setCreateTime(new Date());
		error.setInterfaceAdr(request.getRequestURI());
		error.setIp(request.getRemoteAddr());
		error.setParams(StringUtils.toJSONString(param));
		if (ex instanceof ExceptionEntity) {
			error.setParams(((ExceptionEntity) ex).getParams());
		}
		appErrorLogDao.insert(error);
		return resultJson;
	}
}