package com.hard;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        FilterManager filterManager = new FilterManager(new Target());
        filterManager.addFilter(new AuthenticationFilter());
        filterManager.addFilter(new DebugFilter());

        Client client = new Client();

        client.setFilterManager(filterManager);
        client.sendRequest("/entities");
    }
}

/**
 * Filter
 */

interface Filter {
    void execute(String request);
}

/**
 * Concrete Filter
 */

class AuthenticationFilter implements Filter {
    @Override
    public void execute(String request) {
        System.out.println("Authenticating request: " + request);
    }
}

class DebugFilter implements Filter {
    @Override
    public void execute(String request) {
        System.out.println("Request log: " + request);
    }
}

/**
 * Target
 */

class Target {
    public void execute(String request) {
        System.out.println("Executing request: " + request);
    }
}

/**
 * Filter Chain
 */

class FilterChain {
    private Collection<Filter> filters = new ArrayList<>();
    private Target target;

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public void execute(String request) {
        for (Filter filter : filters)
            filter.execute(request);

        target.execute(request);
    }
}

/**
 * Filter Manager
 */

class FilterManager {
    private FilterChain filterChain;

    public FilterManager(Target target) {
        filterChain = new FilterChain();
        filterChain.setTarget(target);
    }

    public void addFilter(Filter filter) {
        filterChain.addFilter(filter);
    }

    public void setTarget(Target target) {
        filterChain.setTarget(target);
    }

    public void filterRequest(String request) {
        filterChain.execute(request);
    }
}

/**
 * Client
 */

class Client {
    private FilterManager filterManager;

    public void setFilterManager(FilterManager filterManager) {
        this.filterManager = filterManager;
    }

    public void sendRequest(String request) {
        filterManager.filterRequest(request);
    }
}
