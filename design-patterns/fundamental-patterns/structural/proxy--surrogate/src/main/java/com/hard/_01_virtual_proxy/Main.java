package com.hard._01_virtual_proxy;

public class Main {
    public static void main(String[] args) {
        IService service = new Service();        // eager initialization
        service.operation();

        System.out.println("----------");

        IService serviceProxy = new ServiceProxy();
        serviceProxy.operation();                    // lazy initialization
    }
}

/**
 * Subject, Service
 */

interface IService {
    void operation();
}

/**
 * Real Subject, Service Impl
 */

class Service implements IService {
    public Service() {
        System.out.println("create Service object");
    }

    @Override
    public void operation() {
        System.out.println("operation");
    }
}

/**
 * Proxy Object, Service Proxy
 */

class ServiceProxy implements IService {
    private Service service;

    @Override
    public void operation() {
        if (service == null)
            service = new Service();

        service.operation();
    }
}
