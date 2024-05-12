package com.rateservice.configuration;

import com.rateservice.utilities.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** JavaDoc COMMENT. */
@Configuration
@EnableCaching
public class CacheConfig {
  @Bean
  public Cache inMemoryCache() {
    return new Cache();
  }
}
