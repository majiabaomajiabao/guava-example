package com.guava.example.collection.types;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * @author majiabao on 2017/11/20.
 */
public class BiMapExample {
    public static void main(String[] args) {
        BiMap<String, String> map = HashBiMap.create();
        map.put("a", "aa");
        System.out.println(map.get("a"));
        System.out.println(map.get("aa"));
        System.out.println(map.inverse().get("aa"));
        //要求key-value都是唯一
    }
}
