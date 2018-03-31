package com.hard._3_template;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();

        Invoker invoker = new Invoker();

        invoker.addCommand(new Action1Command(receiver));
        invoker.addCommand(new Action2Command(receiver));

        invoker.run();
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
    private Collection<Command> commands = new ArrayList<>();

    public Invoker() {

    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void run() {
        for (Command command : commands)
            command.execute();
    }
}
