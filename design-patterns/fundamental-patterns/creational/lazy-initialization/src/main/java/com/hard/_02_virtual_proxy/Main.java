package com.hard._02_virtual_proxy;

public class Main {
    public static void main(String[] args) {
        IService service = new ServiceProxy();
        service.operation();
    }
}

/**
 * Virtual proxy
 */

interface IService {
    void operation();
}

class Service implements IService {
    public Service() {
        System.out.println("create Service object");
    }

    @Override
    public void operation() {
        System.out.println("operation");
    }
}

class ServiceProxy implements IService {
    private Service service;

    @Override
    public void operation() {
        if (service == null)
            service = new Service();

        service.operation();
    }
}
