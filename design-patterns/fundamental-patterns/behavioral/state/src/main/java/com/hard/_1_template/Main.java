package com.hard._1_template;

public class Main {
    public static void main(String[] args) {
        Context context = new Context();

        context.setState(new State1());
        context.operation();

        for (int i = 0; i < 3; i++) {
            context.nextState();
            context.operation();
        }
    }
}

/**
 * State
 */

interface State {
    void operation();
}

/**
 * Concrete State
 */

class State1 implements State {
    @Override
    public void operation() {
        System.out.println("State1");
    }
}

class State2 implements State {
    @Override
    public void operation() {
        System.out.println("State2");
    }
}

class State3 implements State {
    @Override
    public void operation() {
        System.out.println("State3");
    }
}

/**
 * Context
 */

class Context {
    private State state;

    public void setState(State state) {
        this.state = state;
    }

    public void nextState() {
        if (state instanceof State1)
            setState(new State2());
        else if (state instanceof State2)
            setState(new State3());
        else if (state instanceof State3)
            setState(new State1());
    }

    public void operation() {
        state.operation();
    }
}
