package com.rateservice.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;
@Service
@Scope("singleton")
public class RequestCounter {
  private final AtomicInteger count = new AtomicInteger(0);

  public synchronized void increment() {
    count.incrementAndGet();
  }

  public synchronized int getCount() {
    return count.get();
  }
}
