package br.com.davyson.userregistryapi.config.cache;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Configuration
@EnableCaching
@Profile("dev")
public class RedisConfig {

    @Bean
    public GenericJackson2JsonRedisSerializer serializer(){
        ObjectMapper objMapper = new ObjectMapper();
        objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

        objMapper.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
        );
        return new GenericJackson2JsonRedisSerializer(objMapper);
    }

    @Bean
    public RedisCacheManager connectionFactoryFromCache(RedisConnectionFactory connectionFactory){
        GenericJackson2JsonRedisSerializer jacsonSerializer = serializer();

        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext
                        .SerializationPair.fromSerializer(serializer()));

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(cacheConfiguration.entryTtl(Duration.ofMinutes(5)))
                .build();
    }
    @Bean
    public CommandLineRunner clearCacheOnStartups(RedisConnectionFactory connectionFactory){
        return args -> {
            System.out.println("Limpando o cache...");
            try{
                connectionFactory.getConnection().serverCommands().flushAll();
                System.out.println("Cache limpo com sucesso!");
            } catch(Exception ex){
                System.err.println("Falha ao limpar o cache: " + ex.getMessage());
            }
        };
    }
}
