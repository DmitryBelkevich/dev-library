package com.hard._04_contextualized_lookup;

public class Main {
    public static void main(String[] args) {
        ProductController productController = new ProductController();

        productController.lookup(new Context1());

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
 * Context
 */

interface Context {
    ProductService getProductService(String title);
}

class Context1 implements Context {
    @Override
    public ProductService getProductService(String title) {
        switch (title) {
            case "ProductService":
                return new ProductServiceImpl();
        }

        return null;
    }
}

/**
 * Inversion of Control (IoC)
 */

interface Injectable {
    void lookup(Context context);
}

class ProductController implements Injectable {
    private ProductService productService;

    @Override
    public void lookup(Context context) {
        this.productService = context.getProductService("ProductService");
    }

    public void getAll() {
        productService.getAll();
    }
}
