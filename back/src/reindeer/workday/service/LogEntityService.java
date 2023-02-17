package reindeer.workday.service;

import javax.servlet.http.HttpServletRequest;

import reindeer.workday.dao.WstLog;


/**
 * 日志模块实现的统一接口
 * 
 * @author Administrator
 * 
 */
public interface LogEntityService {

	/**
	 * webroot下的一级目录名称
	 * 
	 * @return String
	 */
	String getModuleName();

	/**
	 * 对应日志模块的日志实现
	 * 
	 * @param log
	 *            记录的日志实体对象
	 * @param servletPath
	 *            对应servlet的url，和request.getServletPath()一致。
	 * @param request
	 *            httpservlet的请求
	 */
	void logging(WstLog log, String servletPath, HttpServletRequest request);

}
