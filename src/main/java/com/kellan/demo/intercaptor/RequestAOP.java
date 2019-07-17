package com.kellan.demo.intercaptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 请求记录拦截器
 * @author Kellan_Song
 * @createTime 2019年4月4日
 */
@Aspect
@Component
public class RequestAOP {
	
	private final Logger logger = LoggerFactory.getLogger(RequestAOP.class);
	
//	@Autowired
//	RequestRecordServiceI requestRecordService;
	
	@Pointcut("execution(public * com.kellan.demo.controller..*Controller.*(..))")
	public void pointCut(){}
	
	@Around("pointCut()")
	public Object arounds(ProceedingJoinPoint point) throws Throwable {
		logger.info("----进入请求记录拦截器----");
		Date startTime = new Date();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String ip = request.getRemoteAddr();
		String apiAdr = request.getRequestURI();
		Object o = point.proceed();
//		RequestRecordTask task = new RequestRecordTask(apiAdr, ip, System.currentTimeMillis() - startTime.getTime(), point, o, requestRecordService);
//		ThreadPool.getThreadPool().execute(task);
		return o;
	}
	
}
