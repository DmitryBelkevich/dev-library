package com.hard;

public class Main {
    public static void main(String[] args) {
        Model model = getEntityFromDatabase();
        View view = new View();
        Controller controller = new Controller(model, view);    // Controller

        controller.view1();

        controller.setModelId(2);
        controller.setModelName("entity2");
        controller.view1();
    }

    private static Model getEntityFromDatabase() {
        return new Model(1, "entity1");
    }
}

/**
 * Models
 */

/**
 * 1) data storage
 * 2) код взаимодействия приложения с БД (например доступ через JDBC, Hibernate)
 * 3) данные и правила для представления информации (бизнес логика)
 * Модель даёт контроллеру представление данных
 * Модель представляет из себя логику получения/изменения данных
 */

class Model {
    private int id;
    private String name;

    public Model() {

    }

    public Model(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/**
 * Views
 */

/**
 * представление - отображение для пользователя
 * представление не содержит бизнес-логики: получения/изменения данных
 * примеры: html-разметка и формы
 */

class View {
    public void view1(int id, String name) {
        System.out.println(
                id + " " + name
        );
    }

    public void view2(int id, String name) {
        System.out.println(
                "{" +
                        "id: " + id
                        + " name: " + name
                        + "}"
        );
    }

    public void view3(int id, String name) {
        System.out.println(
                "Model["
                        + "id=" + id
                        + ", name=" + name
                        + "]"
        );
    }
}

/**
 * Controllers
 */

/**
 * управляет запросами пользователя (получаемые в виде запросов HTTP GET или POST, когда пользователь нажимает на элементы интерфейса для выполнения различных действий)
 * его функция — вызывать и координировать действие необходимых ресурсов и объектов, нужных для выполнения действий, задаваемых пользователем
 * связывает представление и модель
 * всё взаимодействие между представлением и моделью происходит через контроллер, не имея доступа к друг другу
 */

class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public int getModelId() {
        return model.getId();
    }

    public void setModelId(int id) {
        model.setId(id);
    }

    public String getModelName() {
        return model.getName();
    }

    public void setModelName(String name) {
        model.setName(name);
    }

    public void view1() {
        int id = model.getId();
        String name = model.getName();

        view.view1(id, name);
    }

    public void view2() {
        int id = model.getId();
        String name = model.getName();

        view.view2(id, name);
    }

    public void view3() {
        int id = model.getId();
        String name = model.getName();

        view.view3(id, name);
    }
}
