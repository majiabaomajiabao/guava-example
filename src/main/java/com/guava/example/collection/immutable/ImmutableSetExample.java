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
        // guava核心实现方法construct，实现也是类似于jdk的实现，通过构建一个table[]，根据element的hashcode()，equals（）

    }
}
