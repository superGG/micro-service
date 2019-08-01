package com.kellan.demo.intercaptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
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
@Slf4j
public class ParamInterceptor implements HandlerInterceptor {

	@Value("${intercaptor.param}")
	private Boolean enable; //是否开启

	public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse res,
                           Object arg2, ModelAndView arg3) throws Exception {
		if (enable) {
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
			log.info("-----------------退出ip---拦截器---" + request.getRequestURI() + "  退出时间：" + sim.format(new Date()));
		}
	}
	public boolean preHandle(HttpServletRequest request, HttpServletResponse res,
                             Object handler) throws Exception {
		if (!enable) return true; //若拦截器未开启，直接返回
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		log.info("-----------------进入ip---拦截器");
		// 获取参数
		log.info("-----------------访问地址  | 访问者ip | 访问后台版本 | 访问时间");
		log.info("-----------------" + request.getRequestURI() + " | " + request.getRemoteAddr() + " | " +
				" | " + request.getHeader("serverVersion") + " | " + sim.format(new Date()));
		Set<String> keySet = request.getParameterMap().keySet();
		if(!keySet.isEmpty()) {
			for(String key : keySet) {
				log.info("-----------------参数:"+key+":"+ (request.getParameter(key).length() > 255 ? "参数过长，先不显示" : request.getParameter(key)));
			}
		}
		return true;
	}
}
