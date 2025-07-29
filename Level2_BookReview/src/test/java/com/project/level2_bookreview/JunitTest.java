package com.project.level2_bookreview;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.*;

class JunitTest {

    @BeforeAll
    static void initAll() {

    }

    @BeforeEach
    void init() {

    }

    @Test
    void failingTest() {
        // fail("fail test");
    }

    @Test
    @DisplayName("assumeTrue : 조건이 참이면 아래 코드 실행, 아니면 스킵한다.")
    void abortedTest() {
        assumeTrue("abc".contains("a"));
        assertEquals(1, 1);
    }

    @Test
    @DisplayName("priorityQueue test")
    void pqTest() {
        assertEquals(2, 2);
    }

}
