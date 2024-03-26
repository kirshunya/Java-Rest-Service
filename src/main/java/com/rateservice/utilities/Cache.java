package com.rateservice.utilities;

import com.rateservice.dao.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class Cache {
    private final ConcurrentHashMap<LocalDate, List<User>> cache = new ConcurrentHashMap<>();

    public List<User> getUsersByDate(LocalDate date) {
        return cache.get(date);
    }

    public void cacheUsersByDate(LocalDate date, List<User> users) {
        cache.put(date, users);
    }

    public boolean isUsersByDateCached(LocalDate date) {
        return cache.containsKey(date);
    }

    public void remove(String key) {
        cache.remove(key);
    }
}
