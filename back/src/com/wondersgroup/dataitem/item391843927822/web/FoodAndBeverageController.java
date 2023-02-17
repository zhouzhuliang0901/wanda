package com.wondersgroup.dataitem.item391843927822.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.dataitem.item391843927822.utils.TokenUtil;
import com.wondersgroup.selfapi.bean.SelmAuthToken;
import com.wondersgroup.selfapi.dao.SelmAuthTokenDao;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

@Controller
public class FoodAndBeverageController {
	
	@Autowired
	private SelmAuthTokenDao selmAuthTokenDao;
	
	@Autowired
	private TokenUtil tokenUtil;
	
	/**
	 * 餐饮脸谱名称查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/foodAndBeverage/foodAndBeverage.do")
	public void foodAndBeverage(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String name = req.getParameter("name");
		if(StringUtils.isNotEmpty(name)){
			name = URLDecoder.decode(name, "utf-8");
		}
		
		SelmAuthToken auth = selmAuthTokenDao.get("foodAndBeverage");
		String appName = "ee11fe0a-a63c-4244-9014-54512ba53d50";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("Authorization", "FY "+auth.getStAuthToken());
		
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))+"?name="+name+"&index=0";
		System.out.println(url);
		String str = HttpUtil.doGet(head,url,"GET");
		System.out.println(str);
		
		JSONObject json = JSONObject.fromObject(str);
		int count = json.optInt("Count");
		int pageSize = 0;
		JSONArray arr1 = new JSONArray();
		if(count != 0){
			pageSize = count/50;
			arr1 = json.optJSONArray("Data");
		}
		int size = arr1.size();
		int pageNum = 1;
		while(size<100 && pageSize>=pageNum){
			url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))+"?name="+name+"&index="+pageNum;
			str = HttpUtil.doGet(head,url,"GET");
			System.out.println(str);
			pageNum++;
			json = JSONObject.fromObject(str);
			JSONArray arr2 = json.optJSONArray("Data");
			arr1 = joinJSONArray(arr1,arr2);
			size = arr1.size();
		}
		AciJsonHelper.writeJsonPResponse(req, res, arr1.toString());
	}
	
	private static JSONArray joinJSONArray(JSONArray array1, JSONArray array2) {
        StringBuffer sbf = new StringBuffer();
        JSONArray jSONArray = new JSONArray();
        try {
            int len = array1.size();
            for (int i = 0; i < len; i++) {
                JSONObject obj1 = (JSONObject) array1.get(i);
                if (i == len - 1)
                    sbf.append(obj1.toString());
                else
                    sbf.append(obj1.toString()).append(",");
            }
            len = array2.size();
            if (len > 0)
                sbf.append(",");
            for (int i = 0; i < len; i++) {
                JSONObject obj2 = (JSONObject) array2.get(i);
                if (i == len - 1)
                    sbf.append(obj2.toString());
                else
                    sbf.append(obj2.toString()).append(",");
            }
            
            sbf.insert(0, "[").append("]");
            jSONArray = JSONArray.fromObject(sbf.toString());
            return jSONArray;
        } catch (Exception e) {
        }
        return null;
    }
	
	/**
	 * 手动更新token
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/foodAndBeverage/updateToken.do")
	public void updateToken(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		tokenUtil.getToken();
		AciJsonHelper.writeJsonPResponse(req, res, "执行完毕！");
	}
	
	/**
	 * （1）一个使用者同一时段只有一个认证Token；
	 * （2）每个Token的有效时间为8小时；
	 * （3）每个Token 2分钟内调用次数不能大于200次；
	 * （4）8小时调用总数不超过8000次；
	 * 可以附加一个reCheck=1的参数重新获取
	 */
	@PostConstruct
	public void init() {
//		tokenUtil.getToken();
	}
}
