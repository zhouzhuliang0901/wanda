package reindeer.base.app;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import reindeer.base.exception.TipInfoException;
import tw.ecosystem.reindeer.web.Result;

@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 所有异常报错
	 * 
	 * @param request
	 * @param exception
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public String allExceptionHandler(HttpServletRequest request,
			Exception exception) throws Exception {
		exception.printStackTrace();
		Result result = Result.getResult();
		result.setMsg(exception.getMessage());
		return result.toString();
	}

	/**
	 * 提示性异常，不打印错误日志
	 * 
	 * @param request
	 * @param exception
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = TipInfoException.class)
	@ResponseBody
	public String tipInfoExceptionHandler(HttpServletRequest request,
			TipInfoException exception) throws Exception {
		Result result = Result.getSuccessResult();
		result.setCode(exception.getCode());
		result.setMsg(exception.getMsg());
		result.setData(exception.getData());
		return result.toString();
	}

}
