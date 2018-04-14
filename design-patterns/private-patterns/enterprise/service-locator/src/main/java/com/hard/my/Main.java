package com.hard.my;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        BeanLocator beanLocator = new BeanLocator();

        Object bean = beanLocator.getBean("Service1");

        System.out.println(bean);
    }
}

/**
 * Annotations
 */

@Retention(RetentionPolicy.RUNTIME)
@interface Bean {
    String name() default "";
}

/**
 * Service
 */

interface IService {

}

@Bean(name = "service1")
class Service1 implements IService {

}

@Bean(name = "service2")
class Service2 implements IService {

}

/**
 * Initial Context
 */

class Context {
    public Object getBean(String name) {
        if (name.equalsIgnoreCase("service1"))
            return new Service1();

        if (name.equalsIgnoreCase("service2"))
            return new Service2();

        return null;
    }
}

/**
 * Cache
 */

class Container {
    private Map<String, Object> beans = new HashMap<>();

    public void addBean(String name, Object bean) {
        beans.put(name, bean);
    }

    public Object getBean(String name) {
        return beans.get(name);
    }
}

/**
 * Service Locator
 */

class BeanLocator {
    private Context context = new Context();
    private Container container = new Container();

    public Object getBean(String name) {
        Object bean = container.getBean(name);

        if (bean != null)
            return bean;

        Object newBean = context.getBean(name);
        if (newBean != null) {
            container.addBean(name.toLowerCase(), newBean);
            return newBean;
        }

        return null;
    }
}
