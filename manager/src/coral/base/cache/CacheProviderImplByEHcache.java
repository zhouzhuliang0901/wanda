package coral.base.cache;

import java.io.IOException;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import wfc.service.exception.InnerException;
import wfc.service.util.spring.CacheProvider;

@Component
public class CacheProviderImplByEHcache implements CacheProvider {

	private String defaultCacheName = "defaultCacheDefine";
	
	public static String FOREVERCACHENAME = "foreverCache";

	private CacheManager manager;
	
	public CacheProviderImplByEHcache() {
		try {
			manager = CacheManager.create(new ClassPathResource("ehcache.xml")
					.getInputStream());
		} catch (CacheException e) {
			throw new InnerException(e);
		} catch (IOException e) {
			throw new InnerException(e);
		}
	}

	public Cache getCache(String cacheName) {
		return manager.getCache(cacheName);
	}

	public void put(String key, Object object) {
		Element element = new Element(key, object);
		manager.getCache(defaultCacheName).put(element);
	}
	
	public void put(String cacheName, String key, Object object) {
		Element element = new Element(key, object);
		manager.getCache(cacheName).put(element);
	}
	
	public void put(Cache cache, String key, Object object) {
		Element element = new Element(key, object);
		cache.put(element);
	}

	public Object get(String key) {
		Element element = manager.getCache(defaultCacheName).get(key);
		if (element == null) {
			return null;
		}
		return element.getObjectValue();
	}
	
	public Object get(String cacheName, String key) {
		Element element = manager.getCache(cacheName).get(key);
		if (element == null) {
			return null;
		}
		return element.getObjectValue();
	}
	
	public Object get(Cache cache, String key) {
		Element element = cache.get(key);
		if (element == null) {
			return null;
		}
		return element.getObjectValue();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String[] getAllKeys() {
		List result = manager.getCache(defaultCacheName).getKeys();
		return (String[]) result.toArray(new String[result.size()]);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String[] getAllKeys(String cacheName) {
		List result = manager.getCache(cacheName).getKeys();
		return (String[]) result.toArray(new String[result.size()]);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String[] getAllKeys(Cache cache) {
		List result = cache.getKeys();
		return (String[]) result.toArray(new String[result.size()]);
	}

	public void remove(String key) {
		manager.getCache(defaultCacheName).remove(key);
	}
	
	public void remove(String cacheName, String key) {
		manager.getCache(cacheName).remove(key);
	}
	
	public void remove(Cache cache, String key) {
		cache.remove(key);
	}

}
