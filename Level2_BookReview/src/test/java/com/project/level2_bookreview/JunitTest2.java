package com.project.level2_bookreview;

import org.junit.jupiter.api.*;

import lombok.Getter;

import static org.junit.jupiter.api.Assertions.*;

public class JunitTest2 {

    @Getter
    static class Person {
        String name;
        int age;

        Person(String n, int a) {
            this.name = n;
            this.age = a;
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

    private static String ErrorGenerater(char a, char b) {
        return "Assertion message --" + "is unnecessary." + (a < b);
    }
}
