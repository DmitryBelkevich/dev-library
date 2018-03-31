package com.hard._1_template;

public class Main {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();

        Invoker invoker = new Invoker(
                new Action1Command(receiver),
                new Action2Command(receiver)
        );

        invoker.action1();
        invoker.action2();
    }
}

/**
 * Receiver
 */

class Receiver {
    public void action1() {
        System.out.println("action1");
    }

    public void action2() {
        System.out.println("action2");
    }
}

/**
 * Command
 */

interface Command {
    void execute();
}

/**
 * Concrete Command
 */

class Action1Command implements Command {
    private Receiver receiver;

    public Action1Command(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.action1();
    }
}

class Action2Command implements Command {
    private Receiver receiver;

    public Action2Command(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.action2();
    }
}

/**
 * Invoker
 */

class Invoker {
    private Command action1Command;
    private Command action2Command;

    public Invoker(Command action1Command, Command action2Command) {
        this.action1Command = action1Command;
        this.action2Command = action2Command;
    }

    public void action1() {
        action1Command.execute();
    }

    public void action2() {
        action2Command.execute();
    }
}
