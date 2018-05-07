package com.hard._01_factory_pattern;

public class Main {
    public static void main(String[] args) {
        ProductController productController = new ProductController();

        productController.getAll();
    }
}

/**
 * Entity
 */

interface ProductService {
    void getAll();
}

class ProductServiceImpl implements ProductService {
    @Override
    public void getAll() {
        System.out.println("getAll");
    }
}

/**
 * Factory
 */

interface Factory {
    ProductService getProductService();
}

class Factory1 implements Factory {
    public ProductService getProductService() {
        return new ProductServiceImpl();
    }
}

/**
 * Inversion of Control (IoC)
 */

class ProductController {
    private ProductService productService;

    public ProductController() {
        Factory factory = new Factory1();
        productService = factory.getProductService();
    }

    public void getAll() {
        productService.getAll();
    }
}
