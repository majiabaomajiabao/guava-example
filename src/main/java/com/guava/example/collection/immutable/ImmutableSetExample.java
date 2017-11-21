package com.guava.example.collection.immutable;

import com.google.common.collect.ImmutableSet;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author majiabao on 2017/11/19.
 */
public class ImmutableSetExample {
    public static void main(String[] args) {
        //1.
        Set<String> colorName = ImmutableSet.of(
                "red",
                "orange",
                "yellow",
                "green",
                "blue",
                "purple",
                "red");
        System.out.println(colorName);
        //2.
        Set<String> set = new HashSet<String>(Arrays.asList(new String[]{"red",
                "orange",
                "yellow",
                "green",
                "blue",
                "purple",
                "red"}));
        Set<String> unmodifiableSet = Collections.unmodifiableSet(set);
        System.out.println(unmodifiableSet);
        //3.
        Set<String> set3 = ImmutableSet.copyOf(set);
        System.out.println(set3);
        //小结：三种方法都不支持add方法，但第二种可以通过修改原set来添加值，因为第二种方法只是简单的引用。
        //guava核心实现方法construct，通过构建一个table[]，根据element的hashcode(),equals()决定存放的位置
        //construct方法一个小性能优化的地方：在初始化table[]数组，填充元素，如果发现重复元素过多时，会再一次construct，缩减数组的大小
        //获取一个int值的最近的2的指数值的方法：Integer.highestOneBit(setSize - 1) << 1

        //4.
        Set<String> set4 = ImmutableSet.<String>builder().addAll(set).add("d").build();
        System.out.println(set4);

        //5.类似immutable的集合还有
        //ImmutableList,ImmutableMap,ImmutableSortedSet,etc
    }
}
