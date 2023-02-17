/**
 * Project: Coral
 * Source file: LinkedJSONObject.java
 * Create At 2013-12-30 下午03:06:30
 * Create By 龚云
 */
package coral.base.util;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

/**
 * @author 龚云
 * 
 */
public class LinkedJSONObject extends JSONObject {

	@SuppressWarnings("rawtypes")
	public LinkedJSONObject() {
		super();
		try {
			Field fld = JSONObject.class.getDeclaredField("myHashMap");
			fld.setAccessible(true);
			fld.set(this, new LinkedHashMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public LinkedJSONObject(Map map) {
		super(map);
		try {
			Field fld = JSONObject.class.getDeclaredField("myHashMap");
			fld.setAccessible(true);
			fld.set(this, new LinkedHashMap(map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
