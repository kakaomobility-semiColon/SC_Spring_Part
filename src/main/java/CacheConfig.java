import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cache.caffeine.CaffeineCache;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager() {
            @Override
            protected org.springframework.cache.Cache createConcurrentMapCache(String name) {
                return new CaffeineCache(name,
                        Caffeine.newBuilder()
                                .expireAfterWrite(5, TimeUnit.MINUTES) // 5분 후 만료
                                .maximumSize(100) // 최대 100개 아이템 저장
                                .build());
            }
        };
        return cacheManager;
    }
}