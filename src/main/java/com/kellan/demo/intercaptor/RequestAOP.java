package com.kellan.demo.intercaptor;

import com.kellan.demo.dao.RequestRecordMapper;
import com.kellan.demo.model.RequestRecord;
import com.kellan.demo.utils.commom.RequestUtils;
import com.kellan.demo.utils.commom.StringUtils;
import com.kellan.demo.utils.thread.ThreadPool;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * 请求记录拦截器
 * @author Kellan_Song
 * @createTime 2019年4月4日
 */
@Aspect
@Component
public class RequestAOP {
	
	private final Logger logger = LoggerFactory.getLogger(RequestAOP.class);
	
	@Autowired
	RequestRecordMapper requestRecordMapper;
	
	@Pointcut("execution(public * com.kellan.demo.controller..*Controller.*(..))")
	public void pointCut(){}
	
	@Around("pointCut()")
	public Object arounds(ProceedingJoinPoint point) throws Throwable {
		logger.info("----进入请求记录拦截器----");
		Date startTime = new Date();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String ip = request.getRemoteAddr();
		String apiAdr = request.getRequestURI();
		Object result = point.proceed();
		ThreadPool.getThreadPool().execute(new Runnable() {
			@Override
			public void run() {
				logger.info("----请求记录线程开启----");
				try {
					String classType = point.getTarget().getClass().getName();
					Class<?> clazz = Class.forName(classType);
					String clazzName = clazz.getName();
					String methodName = point.getSignature().getName(); // 获取方法名称
					Method[] methods = clazz.getMethods();
					Annotation anno;
					String request_str = null;
					Boolean isSave = true;
					for (Method method : methods) {
						if (method.getName().equals(methodName)) {
							anno = method.getAnnotation(RequestMapping.class);
							if (anno == null) isSave = false;   //排除非 request API
							if (anno != null) {
								//获取请求参数的 key-value
								Map<String, Object> nameAndArgs = RequestUtils.getNameAndArgs(this.getClass(), clazzName, methodName, point.getArgs());
								request_str = StringUtils.toJSONString(nameAndArgs);
								logger.info("----请求参数 :" + (request_str.length() > 255 ? "参数过长，后台不打印" : request_str));
								break;
							}
						}
					}
					if (isSave) {
						// 获取放回结果
						String response_str = StringUtils.toJSONString(result);
						logger.info("----请求结果 :" + (response_str == null ? null : response_str.length() > 255 ? "结果过长，后台不打印" : response_str));
						RequestRecord record = new RequestRecord(request_str, response_str, new Date(), ip, apiAdr, (System.currentTimeMillis() - startTime.getTime()));
						requestRecordMapper.insert(record);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				logger.info("----请求记录线程结束----");
			}
		});
		return result;
	}
	
}
