package com.hard;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Service service = ServiceLocator.getService("Service1");
        service.execute();

        service = ServiceLocator.getService("Service2");
        service.execute();

        service = ServiceLocator.getService("Service1");
        service.execute();

        service = ServiceLocator.getService("Service2");
        service.execute();
    }
}

/**
 * Service
 */

interface Service {
    String getName();

    void execute();
}

/**
 * Concrete Service
 */

class Service1 implements Service {
    @Override
    public String getName() {
        return "Service1";
    }

    @Override
    public void execute() {
        System.out.println("execute Service1");
    }
}

class Service2 implements Service {
    @Override
    public String getName() {
        return "Service2";
    }

    @Override
    public void execute() {
        System.out.println("execute Service2");
    }
}

/**
 * Initial Context
 */

class InitialContext {
    public Object getService(String name) {
        if (name.equalsIgnoreCase("Service1")) {
            System.out.println("Looking up and creating a new Service1 object");
            return new Service1();
        } else if (name.equalsIgnoreCase("Service2")) {
            System.out.println("Looking up and creating a new Service2 object");
            return new Service2();
        }

        return null;
    }
}

/**
 * Cache
 */

class Cache {
    private List<Service> services = new ArrayList<>();

    public Cache() {

    }

    public void addService(Service newService) {
        boolean exists = false;

        for (Service service : services)
            if (service.getName().equalsIgnoreCase(newService.getName()))
                exists = true;

        if (!exists)
            services.add(newService);
    }

    public Service getService(String name) {
        for (Service service : services)
            if (service.getName().equalsIgnoreCase(name)) {
                System.out.println("Returning cached " + name + " object");
                return service;
            }

        return null;
    }
}

/**
 * Service Locator
 */

class ServiceLocator {
    private static Cache cache;

    static {
        cache = new Cache();
    }

    public static Service getService(String name) {
        Service service = cache.getService(name);

        if (service != null)
            return service;
        else {
            InitialContext initialContext = new InitialContext();
            Service newService = (Service) initialContext.getService(name);
            cache.addService(newService);
            return newService;
        }
    }
}
