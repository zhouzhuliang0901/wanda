package reindeer.interceptor;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import tw.ecosystem.reindeer.web.HttpReqRes;
import tw.ecosystem.reindeer.web.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthInterceptor implements HandlerInterceptor {
   
	/**
	 * 访问项目接口需要先进行拦截，查看session中是否具有token
	 */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
       //设置拦截
    	 String token;
		try {
			TokenUtil tokenUtil = new TokenUtil();
			token = tokenUtil.getRequestToken(request);
			 if (StringUtils.isBlank(token)) {
				 Result result = Result.getResult();
				 HttpReqRes httpReqRes = new HttpReqRes(request, response);
				 result.setCode("400");
				 result.setSuccess(false);
				 result.setMsg("授权失败");
				 httpReqRes.writeJsonP(result);
		         return false;
		        }
		} catch (Exception e) {
			e.printStackTrace();
		}
   //--------------------------------------------------
        //1. 根据token，查询用户信息
       /* UserEntity userEntity = authService.findByToken(token);
        //2. 若用户不存在,
        if (userEntity == null) {
            setReturn(response, 400, "用户不存在");
            return false;
        }*/
       /* //3. token失效
        if (userEntity.getExpireTime().isBefore(LocalDateTime.now())) {
            setReturn(response, 400, "用户登录凭证已失效，请重新登录");
            return false;
        }*/

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

   /* private static void setReturn(HttpServletResponse response, int status, String msg) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setStatus(400);
        response.setContentType("application/json;charset=utf-8");
        Result build = Result.build(status, msg);
        String json = JSON.toJSONString(build);
        httpResponse.getWriter().print(json);
    }*/

}
