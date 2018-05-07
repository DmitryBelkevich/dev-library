package com.hard.example2;

public class Main {
    public static void main(String[] args) {
        AbstractOrderService orderService = new OrderService();

        orderService.getTotalPrice();
    }
}

/**
 * Bad example
 */

//class AbstractOrderService {
//    public void getTotalPrice() {
//        System.out.println("get all positions of cart");
//        System.out.println("get discount for current user");
//        System.out.println("count of total price");
//    }
//}

/**
 * Good example
 */

abstract class AbstractOrderService {
    public void getTotalPrice() {
        getProducts();
        getDiscount();
        calculateTotalPrice();
    }

    public abstract void getProducts();

    public abstract void getDiscount();

    public void calculateTotalPrice() {
        System.out.println("count of total price");
    }
}

class OrderService extends AbstractOrderService {
    @Override
    public void getProducts() {
        System.out.println("get all positions of cart");
    }

    @Override
    public void getDiscount() {
        System.out.println("get discount for current user");
    }
}
