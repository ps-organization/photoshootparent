package com.instrantes.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lime on 2017/11/2
 * 配置redis
 */

@Configuration
@EnableCaching
@ImportResource({"classpath:spring/photoshoot-servlet.xml", "classpath:spring/applicationContext.xml", "classpath:spring/applicationContext-MyBatis.xml"})
public class RedisConfig {
    private static final Logger log = LogManager.getLogger(RedisConfig.class);

    //连接工厂的Bean
    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName("127.0.0.1");
        jedisConnectionFactory.setPort(6379);
        //此处还应该设置密码
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }

    // 迭代缓存管理器的列表，暂未使用到多个缓存器，不用
//    @Bean
//    public CacheManager cacheManager(RedisTemplate redisTemplate) {
//        CompositeCacheManager cacheManager = new CompositeCacheManager();
////        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
//        // 设置超时时间为10分钟，单位为秒
////        cacheManager.setDefaultExpiration(600);
//        List<CacheManager> managers = new ArrayList<>();
////        managers.add();
//        managers.add(new RedisCacheManager(redisTemplate));
//        cacheManager.setCacheManagers(managers);
//        return cacheManager;
//    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        // 设置超时时间为10分钟，单位为秒
        cacheManager.setDefaultExpiration(600);
        return cacheManager;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(
            RedisConnectionFactory redisCF) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisCF);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
    /**
     * @description 自定义的缓存key的生成策略
     *              若想使用这个key
     *              只需要讲注解上keyGenerator的值设置为customKeyGenerator即可
     * @return 自定义策略生成的key
     */
    @Bean
    public KeyGenerator customKeyGenerator() {
        return (o, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(o.getClass().getName());
            sb.append(method.getName());
            for (Object obj : objects) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }



//    private void setSerializer(RedisTemplate<String, String> template) {
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//    }
}
