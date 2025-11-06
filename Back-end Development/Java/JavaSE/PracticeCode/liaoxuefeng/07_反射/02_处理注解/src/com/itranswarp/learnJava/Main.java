package com.itranswarp.learnJava;

import java.lang.reflect.Field;

/**
 *  编译命令：javac -d bin src/com/itranswarp/learnJava/*.java
 *  运行命令：java -cp bin com.itranswarp.learnJava.Main
 */
public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("Bob", "Beijing", 20);
        Person p2 = new Person("", "Shanghai", 20);
        Person p3 = new Person("Alice", "Shanghai", 199);
        for (Person p : new Person[] { p1, p2, p3 }) {
            try {
                check(p);
                System.out.println("Person " + p + " checked ok.");
            } catch (IllegalArgumentException e) {
                System.out.println("Person " + p + " checked failed: " + e);
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        }
    }

    static void check(Person person) throws IllegalArgumentException, ReflectiveOperationException {
        // TODO：根据注解校验字段
        for (Field field: person.getClass().getFields()) {
            Range range = field.getAnnotation(Range.class);
            if (range != null) {
                int min = range.min();
                int max = range.max();

                Object value = field.get(person);

                if (value instanceof Integer n) {
                    if (n > max || n < min) {
                        throw new IllegalArgumentException("Invalid field: " + field.getName());
                    }
                }

                if (value instanceof String s) {
                    int length = s.length();
                    if (length > max || length < min) {
                        throw new IllegalArgumentException("Invalid field: " + field.getName());
                    }
                }
            }
        }
    }
}