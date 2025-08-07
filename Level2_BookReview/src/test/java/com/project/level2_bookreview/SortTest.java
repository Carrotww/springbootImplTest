package com.project.level2_bookreview;

import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;
import java.io.*;

public class SortTest {

    static class Student {

        int age;
        String name;
        char level;

        Student(int i, String s, char c) {
            this.age = i;
            this.name = s;
            this.level = c;
        }
    }

    static int[] intAry = {5, 1, 4, 3, 2};

    @Test
    void test() {
        Stream<Integer> stream = Arrays.stream(intAry).boxed();
        System.out.println(stream);
        Integer[] ary = stream.toArray(Integer[]::new);
        System.out.println(Arrays.toString(ary));

        Integer[] ary2 = Arrays.stream(intAry).boxed().toArray(Integer[]::new);
        Arrays.sort(ary2, Collections.reverseOrder());

        Integer[] ary3 = Arrays.stream(intAry).boxed().toArray(size -> new Integer[size]);
    }

    @Test
    void comparableTest() {
        Function<Integer, String> impleFunction = x -> "value" + x;

        System.out.println(impleFunction.apply(10));
    }

    @Test
    void comparatorTest() {

    }
}
