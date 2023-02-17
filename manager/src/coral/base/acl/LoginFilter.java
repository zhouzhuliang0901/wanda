package coral.base.acl;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) req).getSession();  
	      
	    //首先将原session中的数据转移至一临时map中  
	    Map<String,Object> tempMap = new HashMap();  
	    Enumeration<String> sessionNames = session.getAttributeNames();  
	    while(sessionNames.hasMoreElements()){  
	        String sessionName = sessionNames.nextElement();  
	        tempMap.put(sessionName, session.getAttribute(sessionName));  
	    }  
	      
	    //注销原session，为的是重置sessionId  
	    session.invalidate();  
	      
	    //将临时map中的数据转移至新session  
	    session = ((HttpServletRequest) req).getSession();  
	    for(Map.Entry<String, Object> entry : tempMap.entrySet()){  
	        session.setAttribute(entry.getKey(), entry.getValue());  
	    }  
	    
	    chain.doFilter(req, res);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	

}
