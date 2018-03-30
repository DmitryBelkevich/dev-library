package com.hard;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Element element = new Element3();

        element.accept(new Visitor1());
        element.accept(new Visitor2());
    }
}

/**
 * Element
 */

interface Element {
    void accept(Visitor visitor);
}

/**
 * Concrete Element
 */

class Element1 implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Element2 implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Element3 implements Element {
    private Collection<Element> elements = new ArrayList<>();

    public Element3() {
        elements.add(new Element1());
        elements.add(new Element2());
    }

    @Override
    public void accept(Visitor visitor) {
        for (Element service : elements)
            service.accept(visitor);

        visitor.visit(this);
    }
}

/**
 * Visitor
 */

interface Visitor {
    void visit(Element1 element1);

    void visit(Element2 element2);

    void visit(Element3 element3);
}

/**
 * Concrete Visitor
 */

class Visitor1 implements Visitor {
    @Override
    public void visit(Element1 element1) {
        System.out.println(this + " handles " + element1);
    }

    @Override
    public void visit(Element2 element2) {
        System.out.println(this + " handles " + element2);
    }

    @Override
    public void visit(Element3 element3) {
        System.out.println(this + " handles " + element3);
    }
}

class Visitor2 implements Visitor {
    @Override
    public void visit(Element1 element1) {
        System.out.println(this + " handles " + element1);
    }

    @Override
    public void visit(Element2 element2) {
        System.out.println(this + " handles " + element2);
    }

    @Override
    public void visit(Element3 element3) {
        System.out.println(this + " handles " + element3);
    }
}
