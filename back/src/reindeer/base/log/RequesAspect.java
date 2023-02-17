package reindeer.base.log;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import reindeer.base.log.bean.SelmExceptionInfo;
import reindeer.base.log.bean.SelmRequestLog;
import reindeer.base.log.dao.SelmexceptioninfoDao;
import reindeer.base.log.dao.SelmrequestlogDao;
import reindeer.base.log.utils.IPUtil;

import com.alibaba.fastjson.JSON;

//@Aspect
@Component
public class RequesAspect {
	
	@Autowired
	private SelmrequestlogDao selmrequestlogDao;
	
	@Autowired
	private SelmexceptioninfoDao selmexceptioninfoDao;
	
	/**
	 * 设置请求日志切入点 记录请求日志 在注解的位置切入代码
	 * 扫描所有controller下操作
	 */
//	@Pointcut("execution(public * com.wondersgroup..*.web.*.*(..))")
	public void requestLog(){
		
	}
	
	/**
	 * 设置请求异常切入点记录异常日志 
	 * 扫描所有controller下操作
	 */
//	@Pointcut("execution(public * com.wondersgroup..*.web.*.*(..))")
//			"|| execution(public * com.wondersgroup.selfapi.web.*.*(..))")
	public void operExceptionLogPoinCut(){
		
	}
	
//	@AfterReturning(returning = "ret", pointcut = "requestLog()")
	public void doAfterReturning(JoinPoint joinPoint, Object ret)
			throws IOException {
		SelmRequestLog log = new SelmRequestLog();
		log.setStRequestLogId(UUID.randomUUID().toString());
		// 请求时间
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		log.setDtRequestTime(ts);
		// 获取RequestAttributes
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		// 从获取RequestAttributes中获取HttpServletRequest的信息
		HttpServletRequest request = servletRequestAttributes.getRequest();
		// 请求地址
		String url = request.getRequestURL().toString();
		log.setStRequestUrl(url);
		// 请求类型
		String methodType = request.getMethod();
		log.setStRequestMethodType(methodType);
		Enumeration<String> enumeration = request.getParameterNames();
		JSONObject params = new JSONObject();
		while (enumeration.hasMoreElements()) {
			String key = enumeration.nextElement();
			String value = request.getParameter(key);
			params.put(key, value);
		}
		log.setStRequestParam(params.toString());
		// 获取响应结果
		String result = "";
		if (ret == null) {
			result = (String) request.getAttribute("resultLog");
		} else {
			result = JSON.toJSONString(ret);
		}
		log.setStRequestResponse(result);
		// 从切面织入点处通过反射机制获取织入点处的方法
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		// 获取切入点所在的方法
		Method method = signature.getMethod();
		// 获取请求的类名
		String className = joinPoint.getTarget().getClass().getName();
		// 获取请求的方法名
		String methodName = method.getName();
		log.setStRequestPackage(className);
		log.setStRequestMethodName(methodName);
		// 获取IP
		String realIP = IPUtil.getRealIP(request);
		log.setStRequestIp(realIP);
//		String ipAddress =  IPUtil.getAddresses("ip="+realIP, "utf-8");
//		log.setStRequsetAddress(ipAddress);
		selmrequestlogDao.insert(log);
	}
	
//	@AfterThrowing(pointcut = "operExceptionLogPoinCut()", throwing = "e")
	public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
		SelmExceptionInfo ex = new SelmExceptionInfo();
		ex.setStId(UUID.randomUUID().toString());
		// 获取RequestAttributes
		RequestAttributes requestAttributes = RequestContextHolder
				.getRequestAttributes();
		// 从获取RequestAttributes中获取HttpServletRequest的信息
		HttpServletRequest request = (HttpServletRequest) requestAttributes
				.resolveReference(RequestAttributes.REFERENCE_REQUEST);
		
		try {
			// 从切面织入点处通过反射机制获取织入点处的方法
			MethodSignature signature = (MethodSignature) joinPoint
					.getSignature();
			// 获取切入点所在的方法
			Method method = signature.getMethod();
			// 获取请求的类名
			String className = joinPoint.getTarget().getClass().getName();
			// 获取请求的方法名
			String methodName = method.getName();
			ex.setStExceptionMethod(methodName);
			ex.setStExceptionPackage(className);
			// 请求的参数
			Map<String, String[]> paramMap = request.getParameterMap();
			Map<String, String> rtnMap = new HashMap<String, String>();
			for (String key : paramMap.keySet()) {
				rtnMap.put(key, paramMap.get(key)[0]);
			}
			// 将参数所在的数组转换成json
			String params = JSON.toJSONString(rtnMap);
			ex.setStRequestParam(params);
			StackTraceElement[] stackTraceElements = e.getStackTrace();
			String fileName = "";
			int lineNumber = 0;
			String[] classNameArr = className.split("\\.");
			for(StackTraceElement stackTraceElement : stackTraceElements){
				if(stackTraceElement.getFileName().contains(classNameArr[classNameArr.length-1])){
					fileName = stackTraceElement.getFileName();
					lineNumber = stackTraceElement.getLineNumber();
				}
			}
			ex.setStExceptionLine(lineNumber);
			ex.setStExceptionFile(fileName);
			ex.setStExceptionCause(stackTraceToString(e.getClass().getName(),
					e.getMessage(), e.getStackTrace()));
			String url = request.getRequestURL().toString();
			ex.setStRequestUrl(url);
			// 请求类型
			String methodType = request.getMethod();
			ex.setStRequestMethod(methodType);
			String IP = IPUtil.getRealIP(request);
			ex.setStRequestIp(IP);
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			ex.setDtExceptionTime(ts);
			selmexceptioninfoDao.insert(ex);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	private String stackTraceToString(String exceptionName,
			String exceptionMessage, StackTraceElement[] elements) {
		StringBuffer strbuff = new StringBuffer();
		for (StackTraceElement stet : elements) {
			strbuff.append(stet + "\n");
		}
		String message = exceptionName + ":" + exceptionMessage + "\n\t"
				+ strbuff.toString();
		return message;
	}
}
