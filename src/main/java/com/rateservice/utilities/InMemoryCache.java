package com.rateservice.utilities;

import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCache {
    private final ConcurrentHashMap<String, Object> cache = new ConcurrentHashMap<>();

    public Object get(String key) {
        return cache.get(key);
    }

    public void put(String key, Object value) {
        cache.put(key, value);
    }

    public void remove(String key) {
        cache.remove(key);
    }
}
