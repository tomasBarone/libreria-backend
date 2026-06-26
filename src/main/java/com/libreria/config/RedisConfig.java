package com.libreria.config;
import java.time.Duration;

import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

@Configuration
// 1. Hacemos que tu clase implemente CachingConfigurer
public class RedisConfig implements CachingConfigurer {

	@Bean
	public RedisCacheConfiguration cacheConfiguration() {
		return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10)) // Tiempo de vida de los datos en caché
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new GenericJackson2JsonRedisSerializer()));
	}
	
	// 2. Agregamos el método para atajar las explosiones de Redis si Docker está cerrado
	@Override
	public CacheErrorHandler errorHandler() {
		return new SimpleCacheErrorHandler() {
			@Override
			public void handleCacheGetError(RuntimeException exception, org.springframework.cache.Cache cache, Object key) {
				System.out.println(" Redis fuera de línea (GET). Buscando en Base de Datos principal... Error: " + exception.getMessage());
			}

			@Override
			public void handleCachePutError(RuntimeException exception, org.springframework.cache.Cache cache, Object key, Object value) {
				System.out.println(" Redis fuera de línea (PUT). No se pudo almacenar caché. Error: " + exception.getMessage());
			}

			@Override
			public void handleCacheEvictError(RuntimeException exception, org.springframework.cache.Cache cache, Object key) {
				System.out.println(" Redis fuera de línea (EVICT). No se pudo desalojar caché. Error: " + exception.getMessage());
			}

			@Override
			public void handleCacheClearError(RuntimeException exception, org.springframework.cache.Cache cache) {
				System.out.println(" Redis fuera de línea (CLEAR). No se pudo limpiar caché. Error: " + exception.getMessage());
			}
		};
	}
}