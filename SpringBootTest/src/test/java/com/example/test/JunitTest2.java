package com.example.test;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JunitTest2 {

    static class Person {
        String name;
        int age;

        Person(String n, int a) {
            this.name = n;
            this.age = a;
        }

        String getName() {
            return this.name;
        }

        int getAge() {
            return this.age;
        }
    }

    static final Person person = new Person("mike", 10);

    @Test
    void assertionTest01() {
        assertEquals(1, 1, "test is collect");
        assertTrue('c' > 'b', () -> ErrorGenerater('a', 'b'));
        assertAll("asssertAll Test",
                    () -> assertEquals(1, 1),
                    () -> assertTrue(1 < 2));
    }

    @Test
    void dependentAssertions() {
        assertAll(
            "properties",
            () -> {
                String personName = person.getName();
                System.out.println("personName : " + personName);
                assertNotNull(personName);

                assertAll("check person name",
                    () -> personName.startsWith("a"),
                    () -> personName.endsWith("e")
                );
            },

            () -> {
                int personAge = person.getAge();
                assertNotNull(personAge);

                assertAll("check person age",
                    () -> assertEquals(personAge, 10),
                    () -> assertTrue(personAge < 20)
                );
            }
        );
    }

    @Test
    void ExceptionTest() {
        IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class, () -> {
                throw new IllegalArgumentException("custom IllegalArgumentException");
            });
        
        assertEquals("custom IllegalArgumentException", exception.getMessage());

        // RuntimeException 가 IllegalArgumentException에 상위 클래스이기 때문에 통과
        assertThrows(RuntimeException.class, () -> {
            throw new IllegalArgumentException("runtime exception");
        });
    }


    @Test
    void ExceptionExactlyTest() {
        IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class, () -> {
                throw new IllegalArgumentException("custom IllegalArgumentException");
            });
        
        assertEquals("custom IllegalArgumentException", exception.getMessage());

        assertThrowsExactly(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("runtime exception");
        });
    }

    @Test
    void AssertThatTest() {
        assertThat(person.getName(), containsString("mi"));
    }

    private static String ErrorGenerater(char a, char b) {
        return "Assertion message --" + "is unnecessary." + (a < b);
    }
}
