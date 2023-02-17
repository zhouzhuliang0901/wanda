package reindeer.base.utils;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import wfc.service.log.Log;

import com.wondersgroup.common.utils.AESUtil;


public class AciJsonHelper extends EasyUIHelper {

	/**
	 * 如果客户端提交的参数有jsonpCallback，则返回的结果为jsonpCallback('data')
	 * 
	 * @Description: TODO
	 * @param res
	 * @param result
	 * @throws IOException
	 * @return void
	 * @author miklechun
	 * @date 2016-1-11
	 */
	public static void writeJsonPResponse(HttpServletRequest req,
			HttpServletResponse res, String result) throws IOException {
		String jsonpCallback = req.getParameter("jsonpCallback");
		if (!StringUtils.isEmpty(jsonpCallback)) {
			result = jsonpCallback + "(" + result + ")";
		}
		req.setAttribute("resultLog", result);
		String key = req.getParameter("key");
		JSONObject json = new JSONObject();
		if(StringUtils.isNotEmpty(key) && "AES".equals(key.toUpperCase())){
			try {
				byte[] encodedData = AESUtil.encrypt(result, "8NONwyJtHesysWpD");
				result = AESUtil.parseByte2HexStr(encodedData);
				json.put("success", true);
				json.put("data", result);
			} catch (Exception e) {
				json.put("success", false);
				json.put("data", "");
				Log.debug("响应结果加密失败！");
			}
			result = json.toString();
		}
		writeResponse(res, result, SystemConstant.DEFAULT_CHARSET);
	}

	/**
	 * 向response输出结果
	 * 
	 * @param res
	 * @param result
	 *            结果
	 * @param charset
	 *            编码
	 * @throws IOException
	 */
	public static void writeJsonResponse(HttpServletResponse res,
			String result, String charset) throws IOException {
		res.setContentType("application/json;charset="
				+ SystemConstant.DEFAULT_CHARSET);
		res.setCharacterEncoding(charset);
		res.getWriter().write(result);
	}

	/**
	 * 向response输出结果
	 * 
	 * @param res
	 * @param result
	 *            结果
	 * @throws IOException
	 */
	public static void writeJsonResponse(HttpServletResponse res, String result)
			throws IOException {
		writeResponse(res, result, SystemConstant.DEFAULT_CHARSET);
	}

	@SuppressWarnings("unchecked")
	public static JSONObject combine(JSONObject source, JSONObject copy) {
		Iterator<String> sIterator = copy.keys();
		while (sIterator.hasNext()) {
			String key = sIterator.next();
			Object value = copy.get(key);
			if (value instanceof JSONObject) {
				JSONObject jsonValue = source.optJSONObject(key);
				if (jsonValue != null) {
					value = combine(jsonValue, (JSONObject) value);
				}
			}
			source.put(key, value);
		}
		return source;
	}
}
