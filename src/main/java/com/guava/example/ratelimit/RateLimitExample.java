package com.guava.example.ratelimit;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author majiabao on 2018/4/23.
 */
public class RateLimitExample {
    private final static RateLimiter RATE_LIMITER = RateLimiter.create(5);

    public static void main(String[] args) {

        RATE_LIMITER.tryAcquire();

        new Thread(new Runnable() {
            @Override
            public void run() {
                RATE_LIMITER.acquire(10);
                System.out.println("a");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                RATE_LIMITER.acquire(10);
                System.out.println("a");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                RATE_LIMITER.acquire(10);
                System.out.println("a");
            }
        }).start();

    }
}
