package com.project.level2_bookreview;

import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;
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
    static Integer[] IntegerAry = {6, 1, 4, 56, 2, 1};

    @Test
    void test() {
        Stream<Integer> stream = Arrays.stream(intAry).boxed();
        System.out.println(stream);
        Integer[] ary = stream.toArray(Integer[]::new);
        System.out.println(Arrays.toString(ary));

        Integer[] ary2 = Arrays.stream(intAry).boxed().toArray(Integer[]::new);
        Arrays.sort(ary2, Collections.reverseOrder());

        Integer[] ary3 = Arrays.stream(intAry).boxed().toArray(size -> new Integer[size]);

        System.out.println("-----------------");
        Arrays.sort(intAry);
        Arrays.sort(IntegerAry);
        System.out.println(IntegerAry);
        Arrays.sort(IntegerAry, Collections.reverseOrder());
        System.out.println(IntegerAry);
    }

    @Test
    void comparableTest() {
        Function<Integer, String> impleFunction = x -> "value" + x;

        System.out.println(impleFunction.apply(10));
    }

    @Test
    void comparatorTest() {

    }

    @Test
    void boxedTest() {
        int N = 10_000_000;

        long start1 = System.currentTimeMillis();
        long sum1 = Stream.iterate(0, i -> i + 1)
                          .limit(N)
                          .mapToInt(Integer::intValue)
                          .sum();
        long end1 = System.currentTimeMillis();
        System.out.println("Stream<Integer> 합계: " + sum1 + ", 실행 시간: " + (end1 - start1) + "ms");

        long start2 = System.currentTimeMillis();
        long sum2 = IntStream.range(0, N)
                             .sum();
        long end2 = System.currentTimeMillis();
        System.out.println("IntStream 합계: " + sum2 + ", 실행 시간: " + (end2 - start2) + "ms");

        long start3 = System.currentTimeMillis();
        long sum3 = IntStream.range(0, N)
                             .boxed()
                             .mapToInt(Integer::intValue)
                             .sum();
        long end3 = System.currentTimeMillis();
        System.out.println("IntStream.boxed() 합계: " + sum3 + ", 실행 시간: " + (end3 - start3) + "ms");
    }
}
