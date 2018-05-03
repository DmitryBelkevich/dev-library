package com.hard._02_composition;

public class Main {
    public static void main(String[] args) {
        Repository repository = new Repository();

        Service service = new Service();

        service.getAll();
    }
}

class Repository {
    public void getAll() {
        System.out.println("getAll");
    }
}

class Service {
    private Repository repository = new Repository();

    public void getAll() {
        repository.getAll();
    }
}
