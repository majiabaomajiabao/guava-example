package com.guava.example.collection.types;

import com.google.common.collect.*;

/**
 * @author majiabao on 2017/11/20.
 */
public class MultiMapExample {

    public static void main(String[] args) {
        test2();
    }

    public static void test1() {
        HashMultimap<String, String> map = HashMultimap.create();
        map.put("a", "aa");
        map.put("a", "aaaa");
        System.out.println(map.get("a"));
        //HashMultimap内部实现:Map<K, Collection<V>>

        ListMultimap<String, String> treeListMultimap = MultimapBuilder.treeKeys().arrayListValues().build();
        treeListMultimap.put("d", "aa");
        treeListMultimap.put("a", "aaaaa");
        System.out.println(treeListMultimap);

        ListMultimap<String, String> setMultimap = MultimapBuilder.hashKeys().arrayListValues().build();
        SetMultimap<Integer, String> hashEnumMultimap = MultimapBuilder.hashKeys().hashSetValues().build();
        //MultimapBuilder可以灵活地设置key的类型，value的类型，但具体返回的是listmap，还是setmap，则由value的调用来决定
    }

    public static void test2(){
        Multimap<String,String > scoreMultimap = ArrayListMultimap.create();
        for(int i=10;i<20;i++){
            String value ="value";
            scoreMultimap.put("peida",value);
            scoreMultimap.put("ddd",value);
            scoreMultimap.put("sss",value);
        }
                scoreMultimap.removeAll("ddd");
        System.out.println("scoreMultimap:"+scoreMultimap.size());
        System.out.println("scoreMultimap:"+scoreMultimap.keySet());
    }
}
