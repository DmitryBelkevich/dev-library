package com.hard.example2;

import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();

        Product product = productRepository.getById(1);
    }
}

/**
 * Bad example
 */

//class Product {
//    private String title;
//
//    public Collection<Product> getAll() {
//        System.out.println("getAll");
//        return null;
//    }
//
//    public Product getById(int id) {
//        System.out.println("getById");
//        return null;
//    }
//
//    public void save() {
//        System.out.println("save");
//    }
//
//    public void delete() {
//        System.out.println("delete");
//    }
//
//    public void show() {
//        System.out.println("show");
//    }
//
//    public void print() {
//        System.out.println("print");
//    }
//}

/**
 * Good example
 */

class Product {
    private String title;
}

class ProductRepository {
    public Collection<Product> getAll() {
        System.out.println("getAll");
        return null;
    }

    public Product getById(int id) {
        System.out.println("getById");
        return null;
    }

    public void save() {
        System.out.println("save");
    }

    public void delete() {
        System.out.println("delete");
    }
}

class ProductView {
    public void show() {
        System.out.println("show");
    }

    public void print() {
        System.out.println("print");
    }
}
