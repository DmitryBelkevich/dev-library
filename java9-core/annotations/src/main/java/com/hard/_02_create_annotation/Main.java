package com.hard._02_create_annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Main {
    public static void main(String[] args) {
        Class<?> clazz = Service1.class;

        Annotation[] annotations = clazz.getAnnotations();

        for (Annotation annotation : annotations)
            System.out.println(annotation);
    }
}

/**
 * Annotations
 */

@Retention(RetentionPolicy.RUNTIME)
@interface Service {
    String name() default "";
}

/**
 * Service
 */

@Service(name = "service1")
class Service1 {

}
