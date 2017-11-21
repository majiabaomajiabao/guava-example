package com.guava.example.collection.types;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author majiabao on 2017/11/20.
 */
public class MultiSetExample {
    public static void main(String[] args) {
        List<String> wordList = Lists.newArrayList("a", "b", "c", "a", "b", "d");
        HashMultiset<String> multiSet = HashMultiset.create();
        multiSet.addAll(wordList);
        Integer count = multiSet.count("a");
        System.out.println(count);
        //可以发现jdk中的set是依赖map来实现的，但只使用了map的key属性，而HashMultiset同样是依赖map，但使用了value属性，来记出现次数
        //HashMultiset: 元素存放于 HashMap
        //LinkedHashMultiset: 元素存放于 LinkedHashMap，即元素的排列顺序由第一次放入的顺序决定
        //TreeMultiset:元素被排序存放于TreeMap
        //EnumMultiset: 元素必须是 enum 类型
        //ImmutableMultiset: 不可修改的 Mutiset

        multiSet.setCount("a",0);
        System.out.println(multiSet.elementSet());
        //在HashMultiset,count=0代表删除该元素(该元素不存在)
    }
}
