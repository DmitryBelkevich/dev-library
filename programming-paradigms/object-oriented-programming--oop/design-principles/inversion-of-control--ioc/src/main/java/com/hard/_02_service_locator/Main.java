package com.hard._02_service_locator;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ProductController productController = new ProductController();

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
 * Initial Context
 */

class InitialContext {
    public ProductService getProductService(String title) {
        switch (title) {
            case "ProductService":
                return new ProductServiceImpl();
        }

        return null;
    }
}

/**
 * Service Locator
 */

class ServiceLocator {
    private InitialContext initialContext = new InitialContext();
    private Map<String, ProductService> services = new HashMap<>();

    public ProductService getProductService(String title) {
        ProductService productService = services.get(title);

        if (productService == null) {
            productService = initialContext.getProductService(title);
            services.put(title, productService);
        }

        return productService;
    }
}

/**
 * Inversion of Control (IoC)
 */

class ProductController {
    private ProductService productService;

    public ProductController() {
        ServiceLocator serviceLocator = new ServiceLocator();
        productService = serviceLocator.getProductService("ProductService");
    }

    public void getAll() {
        productService.getAll();
    }
}
