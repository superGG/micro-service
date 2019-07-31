package com.kellan.demo.utils.commom;

import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * 封装请求参数
 * @author Kellan_Song
 * @createTime 2019年4月3日
 */
public class RequestUtils {

	public static Map<String, Object> getNameAndArgs(Class<?> cls, String clazzName, String methodName, Object[] args)
			throws NotFoundException {

		Map<String, Object> nameAndArgs = new HashMap<String, Object>();
		//实例化类型池对象
		ClassPool pool = ClassPool.getDefault();
		ClassClassPath classPath = new ClassClassPath(cls);
		pool.insertClassPath(classPath);
		//获取方法参数
		CtClass cc = pool.get(clazzName);
		CtMethod cm = cc.getDeclaredMethod(methodName);
		MethodInfo methodInfo = cm.getMethodInfo();
		CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
		LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
		if (attr == null) {
			return nameAndArgs; //没有参数，返回空对象
		}
		//判断是否未静态方法
		int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
		for (int i = 0; i < cm.getParameterTypes().length; i++) {
			if (args[i] != null) {
				//过滤文件类型、请求类型    多文件上传时，用数组接收参数，需要额外声明
				if (args[i] instanceof MultipartFile || args[i] instanceof MultipartFile[] || args[i] instanceof HttpSession
						|| args[i] instanceof ServletRequest
						|| args[i] instanceof ServletResponse
						|| args[i] instanceof Model) {
					continue;
				}
				nameAndArgs.put(attr.variableName(i + pos), args[i]);// paramNames即参数名
			}
		}

		return nameAndArgs;
	}

}
