package com.example.demo.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.IOException;

/**
 * @author zhan yan
 * @date 2022/12/28
 */
@Configuration
public class RedisConfig {

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson(@Value("classpath:/redisson.yaml") Resource configFile) throws IOException {
        Config config = Config.fromYAML(configFile.getInputStream());
        return Redisson.create(config);
    }

    @Bean
    public RedissonConnectionFactory redissonConnectionFactory(RedissonClient redisson) {
        return new RedissonConnectionFactory(redisson);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        RedisTemplate<String, Object> temp = new RedisTemplate<>();
        RedisSerializer<?> strSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<Object> jackson = new Jackson2JsonRedisSerializer<>(Object.class);
        jackson.setObjectMapper(om);

        temp.setKeySerializer(strSerializer);
        temp.setHashKeySerializer(strSerializer);
        temp.setValueSerializer(jackson);
        temp.setHashValueSerializer(jackson);
        temp.setConnectionFactory(redisConnectionFactory);
        temp.setEnableTransactionSupport(true);
        temp.afterPropertiesSet();

        return temp;
    }

}
