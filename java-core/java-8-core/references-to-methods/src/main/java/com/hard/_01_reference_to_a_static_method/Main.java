package com.hard._01_reference_to_a_static_method;

public class Main {
    public static void main(String[] args) {
        Listener listener = Printer::print;
//        Listener listener = (str) -> System.out.println(str);

        listener.execute("Hello World");
    }
}

interface Listener {
    void execute(String str);
}

class Printer {
    public static void print(String str) {
        System.out.println(str);
    }
}
