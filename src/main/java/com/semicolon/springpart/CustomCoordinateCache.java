package com.semicolon.springpart;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.semicolon.springpart.dto.ChargerMarkerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
public class CustomCoordinateCache {

    private static final Logger logger = LoggerFactory.getLogger(CustomCoordinateCache.class);
    private final Cache<String, List<ChargerMarkerDTO>> cache; // 제네릭 타입을 더 구체적으로 지정해줍니다.
    private final Map<String, String> parentKeyMap = new ConcurrentHashMap<>();

    public CustomCoordinateCache() {
        this.cache = Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .maximumSize(100)
                .removalListener((Object key, Object value, RemovalCause cause) -> {
                    logger.info("Cache entry for key {} removed due to {}", key, cause);
                })
                .build();
    }

    public List<ChargerMarkerDTO> get(String key) { // 반환 타입도 List<ChargerMarkerDTO>로 수정합니다.
        // Check for parent keys
        for (Map.Entry<String, String> entry : parentKeyMap.entrySet()) {
            if (isSubsetKey(entry.getKey(), key)) {
                return cache.getIfPresent(entry.getKey());
            }
        }
        return cache.getIfPresent(key);
    }

    public void put(String key, List<ChargerMarkerDTO> value) { // value의 타입도 List<ChargerMarkerDTO>로 수정합니다.
        cache.put(key, value);
        // Update parent key map
        for (Map.Entry<String, String> entry : parentKeyMap.entrySet()) {
            if (isSubsetKey(entry.getKey(), key)) {
                return;
            }
        }
        parentKeyMap.put(key, key);
    }

    private boolean isSubsetKey(String parentKey, String childKey) {
        // Implement your logic to check if parentKey is a superset of childKey
        String[] parentCoords = parentKey.split(",");
        String[] childCoords = childKey.split(",");
        float parentSwLat = Float.parseFloat(parentCoords[0]);
        float parentSwLng = Float.parseFloat(parentCoords[1]);
        float parentNeLat = Float.parseFloat(parentCoords[2]);
        float parentNeLng = Float.parseFloat(parentCoords[3]);
        float childSwLat = Float.parseFloat(childCoords[0]);
        float childSwLng = Float.parseFloat(childCoords[1]);
        float childNeLat = Float.parseFloat(childCoords[2]);
        float childNeLng = Float.parseFloat(childCoords[3]);

        return parentSwLat <= childSwLat && parentSwLng <= childSwLng &&
                parentNeLat >= childNeLat && parentNeLng >= childNeLng;
    }
}
