package com.project.level2_bookreview;

import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matcher.*;
import java.util.*;

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

    @Test
    void test() {
        System.out.println((0 + 1) / 2);
        System.out.println((2 + 1) / 2);
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

    // return -> 부분합 개수
    static int twoPointer(int[] ary, int target) {
        // target -> 찾아야 할 부분 합

        int n = ary.length;
        int start = 0;
        int end = 0;
        int sum = 0;
        int result = 0;

        for (; start < n; start++) {
            while (end < n && sum < target) {
                sum += ary[end];
                end++;
            }
            if (sum == target) {
                result++;
            }
            sum -= ary[start];
        }
        return result;
    }

    static int slidingWindow(int[] ary, int size, int target) {
        int end = ary.length - size;
        int sum = 0;
        int result = 0;
        for (int i = 0; i < size; i++) sum += ary[i];
        if (sum == target) result++;

        for (int start = 1; start < end; start++) {
            sum -= ary[start - 1];
            sum += ary[start + size - 1];

            if (sum == target) result++;
        }
        return result;
    }

    static void binarySearch2(int[] ary, int target) {
        int left = 0;
        int right = ary.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (ary[mid] == target) {
                System.out.println(mid);
                return;
            } else if (ary[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(-1);
        return;
    }

    static void leftBound2(int[] ary, int target) {
        int left = 0;
        int right = ary.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (ary[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        System.out.println(left);
    }

    static void rightBound2(int[] ary, int target) {
        int left = 0;
        int right = ary.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (ary[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        System.out.println(left);
    }

    // [1, 2, 3, 4, 5, 6, 6]
    static void twoPointer2(int[] ary, int target) {
        int left = 0;
        int right = 0;
        int n = ary.length - 1;
        int sum = 0;

        for (; left < n; left++) {
            while (right < n && sum < target) {
                sum += ary[right];
                right++;

                if (target == sum) {
                    System.out.println("left : " + left + " right : " + right);
                }
            }
            sum -= ary[left];
        }
    }

    static void treePointer(int[] ary, int target) {
    }

    // k = 총 길이
    static void slidingWindow2(int[] ary, int target, int k) {
        int left = 1;
        int right = ary.length - k;
        int sum = 0;
        int result = 0;

        for (int i = 0; i < k; i++) sum += ary[i];
        if (sum == target) result++;

        for (; left <= right; left++) {
            sum -= ary[left - 1];
            sum += ary[left + k - 1];

            if (sum == target) result++;
        }

        System.out.println(result);
    }

    static void slidingWindow22(int[] ary, int target, int k) {
        // for문 하나로 돌면서 0 부터 끝까까 그러면서 k 전에 있는건 지움

        int n = ary.length;
        int sum = 0, result = 0;

        for (int i = 0; i < n; i++) {
            // 더해주기
            sum += ary[i];

            // 빼주기
            if (i >= k) sum -= ary[i - k];

            // 결과 찾기
            if (i >= k - 1 && sum == target) result++;
        }

        System.out.println(result);
    }

}
