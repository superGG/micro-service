package com.kellan.demo.intercaptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * ip地址拦截.
 * @author Kellan.
 * @date:2018-2-19 下午11:33:17
 * @version :
 */
public class IpInterceptor implements HandlerInterceptor {
	
	private final Logger logger = LoggerFactory.getLogger(IpInterceptor.class);
	
	public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse res,
                           Object arg2, ModelAndView arg3) throws Exception {
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		logger.info("-----------------退出ip---拦截器---"+request.getRequestURI()+ "  退出时间："+sim.format(new Date()));
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse res,
                             Object handler) throws Exception {
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		logger.info("-----------------进入ip---拦截器");
		// 获取参数
		logger.info("-----------------访问地址  | 访问者ip | 访问后台版本 | 访问时间");
		logger.info("-----------------" + request.getRequestURI() + " | " + request.getRemoteAddr() + " | " +
				" | " + request.getHeader("serverVersion") + " | " + sim.format(new Date()));
		Set<String> keySet = request.getParameterMap().keySet();
		if(!keySet.isEmpty()) {
			for(String key : keySet) {
				logger.info("-----------------参数:"+key+":"+ (request.getParameter(key).length() > 255 ? "参数过长，先不显示" : request.getParameter(key)));
			}
		}
		return true;
	}
}
