package com.hard._01_factory_pattern;

public class Main {
    public static void main(String[] args) {
        ProductService productService = new ProductService();

        productService.getAll();
    }
}

/**
 * Entity
 */

interface ProductDao {
    void getAll();
}

class ProductDaoImpl implements ProductDao {
    @Override
    public void getAll() {
        System.out.println("getAll");
    }
}

/**
 * Factory
 */

interface Factory {
    ProductDao getProductDao();
}

class Factory1 implements Factory {
    public ProductDao getProductDao() {
        return new ProductDaoImpl();
    }
}

/**
 * Inversion of Control (IoC)
 */

class ProductService {
    private ProductDao productDao;

    public ProductService() {
        Factory factory = new Factory1();
        productDao = factory.getProductDao();
    }

    public void getAll() {
        productDao.getAll();
    }
}


