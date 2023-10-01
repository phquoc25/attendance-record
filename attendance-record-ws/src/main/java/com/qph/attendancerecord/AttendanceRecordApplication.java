package com.qph.attendancerecord;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@SpringBootApplication
@EnableCaching
public class AttendanceRecordApplication {

	@Bean
	public RedisCacheConfiguration cacheConfiguration() {
		return RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofSeconds(20))
				.disableCachingNullValues()
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
	}

	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/attendees").allowedOrigins("*");
				registry.addMapping("/admin/**").allowedOrigins("*")
						.allowedMethods("PUT", "DELETE");
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(AttendanceRecordApplication.class, args);
	}

}
