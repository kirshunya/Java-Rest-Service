package com.rateservice.utilities;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class Cache {

    public static final int MAX_CACHE_SIZE = 10;
    private final ConcurrentHashMap<LocalDate, Object> hashMap = new ConcurrentHashMap<>();


    public Object getUsersByDate(LocalDate key) {
        return hashMap.get(key);
    }

    public void cacheUsersByDate(LocalDate key, Object value) {
        if(hashMap.size() >= MAX_CACHE_SIZE) {
            removeFirstAddedElement();
        }
        hashMap.put(key, value);
    }

    public void removeFirstAddedElement() {
        for (LocalDate key : hashMap.keySet()) {
            hashMap.remove(key);
        }
    }

    public void removeFromCache(LocalDate key) {
        hashMap.remove(key);
    }
    public void updateCache(LocalDate key, Object value) {
        hashMap.put(key, value);
    }

    public boolean containsKey(LocalDate key) {
        return hashMap.containsKey(key);
    }
}

