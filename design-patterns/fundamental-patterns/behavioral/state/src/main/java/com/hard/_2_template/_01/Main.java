package com.hard._2_template._01;

public class Main {
    public static void main(String[] args) {
        Context context = new Context();

        context.setState(new State1());

        for (int i = 0; i < 4; i++)
            context.operation();
    }
}

/**
 * State
 */

interface State {
    void operation(Context context);
}

/**
 * Concrete State
 */

class State1 implements State {
    @Override
    public void operation(Context context) {
        System.out.println("State1");
        context.setState(new State2());
    }
}

class State2 implements State {
    @Override
    public void operation(Context context) {
        System.out.println("State2");
        context.setState(new State3());
    }
}

class State3 implements State {
    @Override
    public void operation(Context context) {
        System.out.println("State3");
        context.setState(new State1());
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

    public void operation() {
        state.operation(this);
    }
}
