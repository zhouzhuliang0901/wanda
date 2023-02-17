package com.wondersgroup.assistant.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class RedisConfig{


        public static Integer getRedisIsOk(String url, int port) {
            int result = 0; try {
      //连接本地Redis服务
           Jedis jedis = new Jedis(url, port);

                String ping = jedis.ping();
                if (ping.equalsIgnoreCase("PONG")) {
                    System.out.println("redis缓存有效！" + ping);
                    result = 0;
                }
            } catch (Exception e) {
                System.out.println("redis缓存失败！");
                result = 1;
            }
            return result;
        }


        public static void main(String[] args) {

            int res = getRedisIsOk("127.0.0.1", 6379);
            if (res == 0) {
                System.out.println("redis缓存有效！" + res);
            } else {
                System.out.println("redis缓存失败！" + res);
            }
        }


}
