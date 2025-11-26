package com.project.level2_bookreview;

import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;

@FunctionalInterface
interface TestInterface {
    int max(int a, int b);
}

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

    static int[] intAry = { 5, 1, 4, 3, 2 };
    static Integer[] IntegerAry = { 6, 1, 4, 56, 2, 1 };

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

        // System.out.println(impleFunction.apply(10));

        class Person implements Comparable<Person> {
            int age;
            String name;

            Person(int a, String n) {
                this.age = a;
                this.name = n;
            }

            // 나이 어린 순으로 정렬 후 나이가 같으면 이름순으로 정렬 둘 다 오름차순
            @Override
            public int compareTo(Person o) {
                if (this.age != o.age) {
                    return Integer.compare(this.age, o.age);
                }
                return this.name.compareTo(o.name);
            }
        }

        class Person2 implements Comparable<Person2> {
            int age;
            String name;

            Person2(int a, String n) {
                this.age = a;
                this.name = n;
            }

            @Override
            public int compareTo(Person2 o) {
                if (this.age != o.age) {
                    return Integer.compare(o.age, this.age);
                }

                return o.name.compareTo(this.name);
            }

            @Override
            public String toString() {
                return "age : " + this.age + " name : " + this.name;
            }
        }

        List<Person2> person2 = new ArrayList<>();

        person2.add(new Person2(17, "aaaaa"));
        person2.add(new Person2(16, "aaaaaaa16"));
        person2.add(new Person2(17, "bbbbbbbbbbb"));
        person2.add(new Person2(100, "oldMan"));
        person2.add(new Person2(11, "youngMan"));

        // 정렬 결과를 테스트
        person2.sort(null); // Comparable 기준으로 정렬
        assertEquals(100, person2.get(0).age); // 첫 번째가 가장 나이 많은지 확인
        assertEquals("oldMan", person2.get(0).name);
        System.out.println(person2);

        person2.sort(Comparator.reverseOrder());
        System.out.println(person2);
    }

    @Test
    void comparatorTest() {

        class Book {
            int pages;
            String name;

            Book(int p, String n) {
                this.pages = p;
                this.name = n;
            }
        }

        class Book2 implements Comparable<Book2> {
            int pages;
            String name;

            Book2(int p, String n) {
                this.pages = p;
                this.name = n;
            }

            @Override
            public int compareTo(Book2 b) {
                if (this.pages == b.pages) {
                    return this.name.compareTo(b.name);
                }
                return Integer.compare(this.pages, b.pages);
            }
        }

        List<Book> ary = new ArrayList<>();
        ary.add(new Book(100, "ababa"));
        ary.add(new Book(500, "bba"));
        ary.add(new Book(200, "caba"));

        // Comparator 사용법

        // 1 클래스 만들기
        class ComparatorAsc implements Comparator<Book> {
            @Override
            public int compare(Book a, Book b) {
                if (a.pages == b.pages) {
                    return Integer.compare(a.pages, b.pages);
                }
                return a.name.compareTo(b.name);
            }
        }

        // 2 익명 클래스 만들기

        ary.sort(new Comparator<Book>() {
            @Override
            public int compare(Book a, Book b) {
                if (a.pages == b.pages) {
                    return a.pages - b.pages;
                }
                return a.name.compareTo(b.name);
            }
        });

        TestInterface testInterface = (a, b) -> (a > b) ? a : b;

        TestInterface testInterface2 = (a, b) -> {
            if (a > b) {
                return a;
            }
            return b;
        };

        // 3 람다 사용

        // Book name asc, age asc
        ary.sort(Comparator.comparing((Book b) -> b.name).thenComparingInt(b -> b.pages));

        // Book name desc, age asc
        ary.sort(Comparator.comparing(((Book b) -> b.name), Comparator.reverseOrder()).thenComparing(b -> b.pages));

        // Book age asc, name asc
        ary.sort(Comparator.comparing((Book b) -> b.pages).thenComparing(b -> b.name));

        // Book age desc, name desc
        ary.sort(Comparator.comparing(((Book b) -> b.pages), Comparator.reverseOrder()).thenComparing(b -> b.name));

        ary.sort(Comparator.comparingInt((Book b) -> b.pages).reversed().thenComparing(b -> b.name));

        int[][] graph = new int[3][3];
        int cnt = 1;

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                graph[i][j] = cnt++;
            }
        }

        int[][] temp = new int[graph.length][];
        int[][] temp2 = graph;

        for (int i = 0; i < graph.length; i++) {
            temp[i] = graph[i].clone();
        }

        temp[1][1] = 999;

        for (int i = 0; i < graph.length; i++) {
            if (i == 0)
                System.out.println("print graph");
            System.out.println(Arrays.toString(graph[i]));
        }

        temp2[1][1] = 777;

        for (int i = 0; i < graph.length; i++) {
            if (i == 0)
                System.out.println("print graph");
            System.out.println(Arrays.toString(graph[i]));
        }

        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparing((int[] a) -> a[0]).reversed());
        Queue<int[]> pq2 = new PriorityQueue<>(
                Comparator.comparing((int[] a) -> a[0]).reversed().thenComparing(a -> a[1]));
    }

    @Test
    void mapTest() {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < 10; i++)
            set.add(i);

        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0 && set.contains(i))
                set.remove(i);
        }

        System.out.println(set);

        Map<String, Integer> map = new HashMap<>();

        map.put("apple", 3); // key: apple, value: 3
        map.put("banana", 2);
        map.put("apple", 5); // 기존 값 덮어쓰기 (3 → 5)

        if (map.containsKey("apple")) {
            map.getOrDefault("apple", 1);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println();
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }

    @Test
    void PqTest() {
        Queue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < 10; i++) {
            int t = (int) Math.random();
            System.out.println(t);
            pq.add(t);
        }

        System.out.println(pq);
    }

    @Test
    void totalTest() {
        // 1. int -> String
        int i = 123;

        String intToString1 = String.valueOf(i);
        String intToString2 = Integer.toString(i);

        // 2. long -> String
        long l = 123;

        String longToString1 = String.valueOf(l);
        String longToString2 = Long.toString(l);

        // 3. String -> int, long
        String s = "101010";

        int stringToInt1 = Integer.parseInt(s);
        // 2진수 값을 10진수로 변경
        int stringToInt2 = Integer.parseInt(s, 2);

        // 정수 -> 문자열
        String binaryToNum = Integer.toBinaryString(stringToInt2);

        // 5. char -> int, int -> char
        char c1 = 'c';
        int intc1 = c1 - 'a'; // 2
        char c2 = (char) (intc1 + 'a'); // c

        // 6. char -> string
        char c3 = 'd';
        String sc = String.valueOf(c3);

        // 정렬
        // 1. int[] 정렬
        int[] a1 = { 3, 1, 2 };
        Arrays.sort(a1);
        Integer[] a2 = {2, 1, 3};
        Arrays.sort(a2, Comparator.comparingInt((Integer x) -> x).reversed());

        List<Integer> ary1 = new ArrayList<>(Arrays.asList(1, 2, 3, 0, -1, 10));
        ary1.sort(Comparator.naturalOrder());
        ary1.sort(Comparator.reverseOrder());

        String[] stringAry = {};

        Arrays.sort(stringAry, Comparator.naturalOrder());

        List<String> stringAry2 = new ArrayList<>();
        stringAry2.sort(Comparator.comparing((String ss) -> ss.length()).thenComparing(Comparator.naturalOrder()));

        PriorityQueue<Integer> pq = new PriorityQueue<>();
    }

    @Test
    void totalTest2() {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.push(1);
        dq.add(4);
        dq.push(2);

        System.out.println(dq);

        dq.pop();

        System.out.println(dq);
    }

    @Test
    void test_2025_11_04() {
        // PriorityQueue<int[]> pq = new PriorityQueue<>(
        //     // Comparator.comparingInt((int[] a) -> a[0]).reversed().thenComparingInt(a -> a[1]).reversed()
        //     Comparator.comparingInt((int[] a) -> -a[0]).thenComparingInt(a -> -a[1])
        // );

        PriorityQueue<int[]> pq = new PriorityQueue<> (
            (a, b) -> {
                if (a[0] != b[0]) return Integer.compare(b[0], a[0]);
                return Integer.compare(b[1], a[1]);
            }
        );

        // 개수, 숫자
        pq.add(new int[] {10, 8});
        pq.add(new int[] {10, 9});
        pq.add(new int[] {2, 4});
        pq.add(new int[] {2, 5});
        pq.add(new int[] {4, 7});
        pq.add(new int[] {11, 3});
        pq.add(new int[] {1, 1});

        while (!pq.isEmpty()) System.out.println(Arrays.toString(pq.poll()));
    }
}
