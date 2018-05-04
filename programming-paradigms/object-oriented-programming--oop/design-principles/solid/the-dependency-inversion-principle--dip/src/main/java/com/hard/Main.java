package com.hard;

public class Main {
    public static void main(String[] args) {
        ProductService productService = new ProductService();

        productService.getAll();
    }
}

/**
 * Bad example
 */

//class ProductDao {
//    public void getAll() {
//        System.out.println("getAll");
//    }
//}
//
//class ProductService {
//    private ProductDao productDao = new ProductDao();
//
//    public void getAll() {
//        productDao.getAll();
//    }
//}

/**
 * Good example
 */

interface Dao {
    void getAll();
}

class ProductDao implements Dao {
    @Override
    public void getAll() {
        System.out.println("getAll");
    }
}

class ProductService {
    private Dao productDao = new ProductDao();

    public void getAll() {
        productDao.getAll();
    }
}
