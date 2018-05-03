package com.hard._01_inheritance;

public class Main {
    public static void main(String[] args) {
        AbstractRepository repository = new Repository();

        repository.getAll();
    }
}

abstract class AbstractRepository {
    public void getAll() {
        System.out.println("getAll");
    }
}

class Repository extends AbstractRepository {

}
