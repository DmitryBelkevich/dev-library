package com.hard._03_aggregation;

public class Main {
    public static void main(String[] args) {
        Repository repository = new Repository();

        Service service = new Service(repository);

        service.getAll();
    }
}

class Repository {
    public void getAll() {
        System.out.println("getAll");
    }
}

class Service {
    private Repository repository;

    public Service() {

    }

    public Service(Repository repository) {
        this.repository = repository;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public void getAll() {
        repository.getAll();
    }
}
