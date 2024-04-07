package com.rateservice.utilities;

import java.time.LocalDate;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

/** JavaDoc COMMENT. */
@Component
public class Cache {

  public static final int MAX_CACHE_SIZE = 10;
  private final ConcurrentHashMap<LocalDate, Object> hashMap = new ConcurrentHashMap<>();

  public Object getUsersByDate(LocalDate key) {
    return hashMap.get(key);
  }

  /** JavaDoc COMMENT. */
  public void cacheUsersByDate(LocalDate key, Object value) {
    if (hashMap.size() >= MAX_CACHE_SIZE) {
      removeFirstAddedElement();
    }
    hashMap.put(key, value);
  }

  /** JavaDoc COMMENT. */
  public void removeFirstAddedElement() {
    for (LocalDate key : hashMap.keySet()) {
      hashMap.remove(key);
    }
  }

  public void removeFromCache(LocalDate key) {
    hashMap.remove(key);
  }

  public boolean containsKey(LocalDate key) {
    return hashMap.containsKey(key);
  }
}
