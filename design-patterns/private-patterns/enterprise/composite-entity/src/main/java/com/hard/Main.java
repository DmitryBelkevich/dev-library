package com.hard;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Client client = new Client();

        client.setData("aaa", "bbb");
        client.printData();

        client.setData("ccc", "ddd");
        client.printData();
    }
}

/**
 * Dependent Object
 */

class DependentObject1 {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

class DependentObject2 {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

/**
 * Coarse Grained Object
 */

class CoarseGrainedObject {
    private DependentObject1 dependentObject1 = new DependentObject1();
    private DependentObject2 dependentObject2 = new DependentObject2();

    public void setData(String data1, String data2) {
        dependentObject1.setData(data1);
        dependentObject2.setData(data2);
    }

    public Collection<String> getData(){
        Collection<String> data = new ArrayList<>();

        data.add(dependentObject1.getData());
        data.add(dependentObject2.getData());

        return data;
    }
}

/**
 * Composite Entity
 */

class CompositeEntity {
    private CoarseGrainedObject coarseGrainedObject = new CoarseGrainedObject();

    public void setData(String data1, String data2) {
        coarseGrainedObject.setData(data1, data2);
    }

    public Collection<String> getData(){
        Collection<String> data = coarseGrainedObject.getData();

        return data;
    }
}

/**
 * Client
 */

class Client {
    private CompositeEntity compositeEntity = new CompositeEntity();

    public void setData(String data1, String data2) {
        compositeEntity.setData(data1, data2);
    }

    public void printData(){
        Collection<String> data = compositeEntity.getData();

        for (String d : data)
            System.out.println(d);
    }
}
