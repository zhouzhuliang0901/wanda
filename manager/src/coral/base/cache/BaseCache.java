package coral.base.cache;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;

import wfc.service.log.Log;
import wfc.service.util.spring.CacheProvider;

public abstract class BaseCache {

	private String categoryKey;

	protected BaseCache() {
		categoryKey = this.getClass().getName();
	}

	@SuppressWarnings("unchecked")
	protected Object aroundWithRead(ProceedingJoinPoint jp,
			CacheProvider cacheProvider, String methodName) throws Throwable {
		Map<String, Object> map = (Map<String, Object>) cacheProvider
				.get(categoryKey);
		if (map == null) {
			map = Collections.synchronizedMap(new HashMap<String, Object>());
			cacheProvider.put(categoryKey, map);
		}
		String key = getKey(jp, methodName);
		Object obj = map.get(key);
		if (obj == null) {
			obj = jp.proceed();
			map.put(key, obj);
		} else {
			Log.debug("缓存已使用：" + categoryKey);
		}
		return obj;
	}

	protected Object aroundWithFlush(ProceedingJoinPoint jp,
			CacheProvider cacheProvider) throws Throwable {
		cacheProvider.remove(categoryKey);
		return jp.proceed();
	}

	private String getKey(ProceedingJoinPoint jp, String methodName) {
		String key = jp.getTarget().getClass().getName() + "." + methodName;
		Object[] object = jp.getArgs();
		if (object != null) {
			for (int i = 0; i < object.length; i++) {
				if (object[i] != null) {
					if (object[i] instanceof Object[]) {
						key += ".";
						for (Object obj : (Object[]) object[i]) {
							key = key + "#" + obj.hashCode();
						}
					} else {
						key = key + "." + object[i].hashCode();
					}
				} else {
					key = key + ".*";
				}
			}
		}
		return key;
	}

}
