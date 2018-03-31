package com.hard._2_template;

public class Main {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();

        Command command = new Action1Command(receiver);

        Invoker invoker = new Invoker(command);
        invoker.execute();

        invoker.setCommand(new Action2Command(receiver));
        invoker.execute();
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
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void execute() {
        command.execute();
    }
}
