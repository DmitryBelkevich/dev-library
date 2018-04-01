package com.hard;

public class Main {
    public static void main(String[] args) {
        Factory factory = getFactoryByName("FactoryA");

        Product1 product1 = factory.getProduct1();
        Product2 product2 = factory.getProduct2();

        product1.getData();
        product2.getData();
    }

    private static Factory getFactoryByName(String factoryName) {
        switch (factoryName) {
            case "FactoryA":
                return new FactoryA();
            case "FactoryB":
                return new FactoryB();
            default:
                throw new RuntimeException(factoryName + " is not exist");
        }
    }
}

/**
 * Product
 */

interface Product1 {
    void getData();
}

interface Product2 {
    void getData();
}

/**
 * Concrete Product
 */

class Product1A implements Product1 {
    @Override
    public void getData() {
        System.out.println("Product1A");
    }
}

class Product2A implements Product2 {
    @Override
    public void getData() {
        System.out.println("Product2A");
    }
}

class Product1B implements Product1 {
    @Override
    public void getData() {
        System.out.println("Product1B");
    }
}

class Product2B implements Product2 {
    @Override
    public void getData() {
        System.out.println("Product2B");
    }
}

/**
 * Abstract Factory
 */

interface Factory {
    Product1 getProduct1();

    Product2 getProduct2();
}

/**
 * Concrete Factory
 */

class FactoryA implements Factory {
    @Override
    public Product1 getProduct1() {
        return new Product1A();
    }

    @Override
    public Product2 getProduct2() {
        return new Product2A();
    }
}

class FactoryB implements Factory {
    @Override
    public Product1 getProduct1() {
        return new Product1B();
    }

    @Override
    public Product2 getProduct2() {
        return new Product2B();
    }
}
