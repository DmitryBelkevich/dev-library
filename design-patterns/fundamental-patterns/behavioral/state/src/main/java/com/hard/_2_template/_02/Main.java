package com.hard._2_template._02;

public class Main {
    public static void main(String[] args) {
        Context context = new Context();

        context.setState(new State1(context));

        for (int i = 0; i < 4; i++)
            context.operation();
    }
}

/**
 * State
 */

abstract class State {
    protected Context context;

    public State(Context context) {
        this.context = context;
    }

    public abstract void operation();
}

/**
 * Concrete State
 */

class State1 extends State {
    public State1(Context context) {
        super(context);
    }

    @Override
    public void operation() {
        System.out.println("State1");
        context.setState(new State2(context));
    }
}

class State2 extends State {
    public State2(Context context) {
        super(context);
    }

    @Override
    public void operation() {
        System.out.println("State2");
        context.setState(new State3(context));
    }
}

class State3 extends State {
    public State3(Context context) {
        super(context);
    }

    @Override
    public void operation() {
        System.out.println("State3");
        context.setState(new State1(context));
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
        state.operation();
    }
}
