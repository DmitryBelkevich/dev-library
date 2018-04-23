package com.hard._03_annotations_fields;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Main {
    public static void main(String[] args) {

    }
}

enum Enum {
    A,
    B,
    C,
}

/**
 * Annotations
 */

@Retention(RetentionPolicy.RUNTIME)
@interface Service {
    String name() default "";

    int number() default 0;

    Class clazz() default Object.class;

    Enum enumeration() default Enum.A;

    Override annotation() default @Override;

    String[] names() default {
            "a",
            "b",
    };

    int[] numbers() default {
            1,
            2,
    };

    Class[] classes() default {
            Object.class,
    };

    Enum[] enumerations() default {
            Enum.A,
            Enum.B,
    };

    Override[] annotations() default {
            @Override,
    };
}
