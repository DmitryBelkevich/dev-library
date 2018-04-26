package com.hard._02_reference_to_an_instance_method_of_a_particular_object;

public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer();

        Listener listener = printer::print;
//        Listener listener = (str) -> System.out.println(str);

        listener.execute("Hello World");
    }
}

interface Listener {
    void execute(String str);
}

class Printer {
    public void print(String str) {
        System.out.println(str);
    }
}
