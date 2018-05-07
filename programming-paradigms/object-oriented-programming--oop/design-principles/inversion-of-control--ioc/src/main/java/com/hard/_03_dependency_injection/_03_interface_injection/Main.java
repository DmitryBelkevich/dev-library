package com.hard._03_dependency_injection._03_interface_injection;

public class Main {
    public static void main(String[] args) {
        ProductController productController = new ProductController();

        productController.inject(new ProductServiceImpl());

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

interface Injectable {
    void inject(ProductService productService);
}

class ProductController implements Injectable {
    private ProductService productService;

    @Override
    public void inject(ProductService productService) {
        this.productService = productService;
    }

    public void getAll() {
        productService.getAll();
    }
}
