package com.hard;

public class Main {
    public static void main(String[] args) {
        ProductCreator productCreator = getCreatorByName("Creator1");

        Product product = productCreator.createProduct();

        product.getData();
    }

    private static ProductCreator getCreatorByName(String creatorName) {
        switch (creatorName) {
            case "Creator1":
                return new Product1Creator();
            case "Creator2":
                return new Product2Creator();
            default:
                throw new RuntimeException(creatorName + " is not exist");
        }
    }
}

/**
 * Product
 */

interface Product {
    void getData();
}

/**
 * Concrete Product
 */

class Product1 implements Product {
    @Override
    public void getData() {
        System.out.println("Product1");
    }
}

class Product2 implements Product {
    @Override
    public void getData() {
        System.out.println("Product2");
    }
}

/**
 * Creator / Factory
 */

interface ProductCreator {
    Product createProduct();
}

/**
 * Concrete Creator / Concrete Factory
 */

class Product1Creator implements ProductCreator {
    @Override
    public Product createProduct() {
        return new Product1();
    }
}

class Product2Creator implements ProductCreator {
    @Override
    public Product createProduct() {
        return new Product2();
    }
}
