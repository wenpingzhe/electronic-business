package com.agri.wen.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.dmsdbj.cloud.tool.redis.TedisCacheManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author
 * 实例:
 * 1.注解使用:
 *
 * @Cacheable(value = "configDetail", key = "#p0", sync = true)
 * @CacheExpire(expire = 120)
 * ConfigDetailEntity findByAction(String action);
 * <p>
 * 2.直接使用
 * redisTemplate.opsForValue();//操作字符串
 * redisTemplate.opsForHash();//操作hash
 * redisTemplate.opsForList();//操作list
 * redisTemplate.opsForSet();//操作set
 * redisTemplate.opsForZSet();//操作有序set
 */
@Configuration
@Slf4j
public class RedisConfig extends CachingConfigurerSupport {

    private final RedisConnectionFactory redisConnectionFactory;
    private RedisSerializer keySerializer = new StringRedisSerializer();
    private final RedisSerializationContext.SerializationPair<String> keyPair = RedisSerializationContext
            .SerializationPair.fromSerializer(keySerializer);

    private RedisSerializer valueSerializer = new GenericFastJsonRedisSerializer();
    private final RedisSerializationContext.SerializationPair<Object> valuePair = RedisSerializationContext
            .SerializationPair.fromSerializer(valueSerializer);

    RedisConfig(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();

        // 设置key序列化类，否则key前面会多了一些乱码
        template.setKeySerializer(keySerializer);
        template.setHashKeySerializer(keySerializer);

        // fastjson serializer
        template.setValueSerializer(valueSerializer);
        template.setHashValueSerializer(valueSerializer);
        // 如果 KeySerializer 或者 ValueSerializer 没有配置，则对应的 KeySerializer、ValueSerializer 才使用这个 Serializer
        template.setDefaultSerializer(valueSerializer);

        log.info("redis: {}", redisConnectionFactory);
        LettuceConnectionFactory factory = (LettuceConnectionFactory) redisConnectionFactory;
        log.info("spring.redis.host: {}", factory.getHostName());
        log.info("spring.redis.port: {}", factory.getPort());
        log.info("spring.redis.timeout: {}", factory.getTimeout());
        log.info("spring.redis.password: {}", factory.getPassword());

        // factory
        template.setConnectionFactory(redisConnectionFactory);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (o, method, objects) -> {
            StringBuilder sb = new StringBuilder(32);
            sb.append(o.getClass().getSimpleName());
            sb.append(".");
            sb.append(method.getName());
            if (objects.length > 0) {
                sb.append("#");
            }
            String sp = "";
            for (Object object : objects) {
                sb.append(sp);
                if (object == null) {
                    sb.append("NULL");
                } else {
                    sb.append(object.toString());
                }
                sp = ".";
            }
            return sb.toString();
        };
    }

    /**
     * 配置 RedisCacheManager，使用 cache 注解管理 redis 缓存
     */
    @Bean
    @Override
    public CacheManager cacheManager() {
        TedisCacheManager tedisCacheManager = TedisCacheManager.create(redisConnectionFactory);
        tedisCacheManager.setKeySerializer(keySerializer);
        tedisCacheManager.setValueSerializer(valueSerializer);
        return tedisCacheManager;
    }
}
