package com.hard;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        // create Composites
        Composite composite1 = new Composite();
        Composite composite2 = new Composite();

        // add Components to Composites
        composite1.addComponent(new Component1());
        composite1.addComponent(new Component2());
        composite1.addComponent(new Component3());

        composite2.addComponent(new Component1());
        composite2.addComponent(new Component2());
        composite2.addComponent(new Component3());

        // add Composites to Composites
        composite1.addComponent(composite2);

        // operation
        composite1.operation();
    }
}

/**
 * Component
 */

interface Component {
    void operation();
}

/**
 * Concrete Components
 */

class Component1 implements Component {
    @Override
    public void operation() {
        System.out.println("Component1");
    }
}

class Component2 implements Component {
    @Override
    public void operation() {
        System.out.println("Component2");
    }
}

class Component3 implements Component {
    @Override
    public void operation() {
        System.out.println("Component3");
    }
}

/**
 * Composite
 */

class Composite implements Component {
    private Collection<Component> components = new ArrayList<>();

    public void addComponent(Component component) {
        components.add(component);
    }

    public void removeComponent(Component component) {
        components.remove(component);
    }

    @Override
    public void operation() {
        for (Component component : components)
            component.operation();
    }
}
