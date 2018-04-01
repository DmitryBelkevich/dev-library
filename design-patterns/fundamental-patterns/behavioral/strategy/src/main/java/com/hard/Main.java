package com.hard;

public class Main {
    public static void main(String[] args) {
        StrategyClient strategyClient = new StrategyClient();

        strategyClient.setStrategy(new Strategy1());
        strategyClient.executeStrategy();

        strategyClient.setStrategy(new Strategy2());
        strategyClient.executeStrategy();
    }
}

/**
 * Strategy
 */

interface Strategy {
    void execute();
}

/**
 * Concrete Strategy
 */

class Strategy1 implements Strategy {
    public void execute() {
        System.out.println("Strategy1");
    }
}

class Strategy2 implements Strategy {
    public void execute() {
        System.out.println("Strategy2");
    }
}

/**
 * Context, Client
 */

class StrategyClient {
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy() {
        strategy.execute();
    }
}
