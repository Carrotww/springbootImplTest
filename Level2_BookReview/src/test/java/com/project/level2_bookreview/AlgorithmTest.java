package com.project.level2_bookreview;

import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matcher.*;

public class AlgorithmTest {

    static int[] ary = new int[] { 1, 1, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 7 };
    static int target_1 = 1;
    static int target_2 = 2;
    static int target_3 = 3;
    static int target_4 = 4;
    static int target_5 = 5;
    static int target_7 = 7;

    static int target_1_leftBound_expect = 0;
    static int target_2_leftBound_expect = 2;
    static int target_3_leftBound_expect = 4;
    static int target_4_leftBound_expect = 7;
    static int target_5_leftBound_expect = 10;
    static int target_7_leftBound_expect = 13;

    static int target_1_rightBound_expect = 2;
    static int target_2_rightBound_expect = 4;
    static int target_3_rightBound_expect = 7;
    static int target_4_rightBound_expect = 10;
    static int target_5_rightBound_expect = 13;
    static int target_7_rightBound_expect = 14;

    @BeforeEach
    void init() {
    }

    @Test
    void leftBoundTest() {
        assertThat(Search.leftBound(ary, target_1), equalTo(target_1_leftBound_expect));
        assertThat(Search.leftBound(ary, target_2), equalTo(target_2_leftBound_expect));
        assertThat(Search.leftBound(ary, target_3), equalTo(target_3_leftBound_expect));
        assertThat(Search.leftBound(ary, target_4), equalTo(target_4_leftBound_expect));
        assertThat(Search.leftBound(ary, target_5), equalTo(target_5_leftBound_expect));
        assertThat(Search.leftBound(ary, target_7), equalTo(target_7_leftBound_expect));
    }

    @Test
    void rightBoundTest() {
        assertThat(Search.rightBound(ary, target_1), equalTo(target_1_rightBound_expect));
        assertThat(Search.rightBound(ary, target_2), equalTo(target_2_rightBound_expect));
        assertThat(Search.rightBound(ary, target_3), equalTo(target_3_rightBound_expect));
        assertThat(Search.rightBound(ary, target_4), equalTo(target_4_rightBound_expect));
        assertThat(Search.rightBound(ary, target_5), equalTo(target_5_rightBound_expect));
        assertThat(Search.rightBound(ary, target_7), equalTo(target_7_rightBound_expect));
    }
}

class Search {

    static int binarySearch(int[] ary, int target) {
        int left = 0;
        int right = ary.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (ary[mid] == target) {
                return mid;
            } else if (ary[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    static int leftBound(int[] ary, int target) {
        int left = 0;
        int right = ary.length;

        while (left < right) {
            int mid = (left + right) / 2;

            if (ary[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    static int rightBound(int[] ary, int target) {
        int left = 0;
        int right = ary.length;

        while (left < right) {
            int mid = (left + right) / 2;

            if (ary[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
