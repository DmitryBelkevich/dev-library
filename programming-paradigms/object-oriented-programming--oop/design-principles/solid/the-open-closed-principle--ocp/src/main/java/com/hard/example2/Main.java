package com.hard.example2;

public class Main {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();

        orderService.getTotalPrice();
    }
}

/**
 * Bad example
 */

//class OrderService {
//    public void getTotalPrice() {
//        System.out.println("get all positions of cart");
//        System.out.println("get discount for current user");
//        System.out.println("count of total price");
//    }
//}

/**
 * Good example
 */

class OrderService {
    public void getTotalPrice() {
        getProducts();
        getDiscount();
        calculateTotalPrice();
    }

    public void getProducts() {
        System.out.println("get all positions of cart");
    }

    public void getDiscount() {
        System.out.println("get discount for current user");
    }

    public void calculateTotalPrice() {
        System.out.println("count of total price");
    }
}
