package com.guava.example.cache;

import com.google.common.cache.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author majiabao on 2017/11/21.
 */
public class LoadingCacheExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //方法一
        LoadingCache<String, Integer> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .build(
                        new CacheLoader<String, Integer>() {
                            public Integer load(String key) {
                                return key.length();
                            }

                            public Integer reload(String key) {
                                return key.length() + 1;
                            }
                        });
        System.out.println(cache.get("aaa"));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(cache.get("aaa"));
        System.out.println(cache.get("aaa"));
        cache.refresh("aaa");

        //方法二
        Cache<String, Integer> cache2 = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .build();
        // look Ma, no CacheLoader
        // If the key wasn't in the "easy to compute" group, we need to
        // do things the hard way.
        cache2.get("aaa", new Callable<Integer>() {
            @Override
            public Integer call() {
                return "aaa".length();
            }
        });

        //回收
        //方式一
        //expireAfterWrite,expireAfterAccess
        //方式二
        LoadingCache<String, Integer> graphs = CacheBuilder.newBuilder()
                .maximumWeight(1)
                .weigher(new Weigher<String, Integer>() {
                    public int weigh(String k, Integer g) {
                        return g;
                    }
                })
                .build(
                        new CacheLoader<String, Integer>() {
                            public Integer load(String key) { // no checked exception
                                return key.length();
                            }
                        });
        graphs.get("aaa");
        graphs.get("aaa");
        //两次都需要加载，因为第一次的值的weight超过了maxweight，所以不会缓存
        //方式三
        //CacheBuilder.newBuilder().weakKeys()
        //CacheBuilder.newBuilder().weakValues()
        //CacheBuilder.softValues()
        //方式四
        //Cache.invalidate(key)
    }
}
