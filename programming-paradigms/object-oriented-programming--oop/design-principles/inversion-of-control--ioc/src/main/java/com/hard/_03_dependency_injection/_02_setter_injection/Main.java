package com.hard._03_dependency_injection._02_setter_injection;

public class Main {
    public static void main(String[] args) {
        ProductController productController = new ProductController();

        productController.setProductService(new ProductServiceImpl());

        productController.getAll();
    }
}

/**
 * Service
 */

interface ProductService {
    void getAll();
}

/**
 * Concrete Service
 */

class ProductServiceImpl implements ProductService {
    @Override
    public void getAll() {
        System.out.println("getAll");
    }
}

/**
 * Inversion of Control (IoC)
 */

class ProductController {
    private ProductService productService;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void getAll() {
        productService.getAll();
    }
}
