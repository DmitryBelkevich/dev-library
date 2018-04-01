package com.hard;

public class Main {
    public static void main(String[] args) {
        IService service = new Service();        // create Service object
        service.operation();                    // operation

        System.out.println("----------");

        IService service2 = new ServiceProxy();
        service2.operation();                    // create Service object, operation
    }
}

/**
 * virtual proxy
 */

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

// другие варианты:
// remote proxy (удалённый прокси)
// protection proxy
// smart link
