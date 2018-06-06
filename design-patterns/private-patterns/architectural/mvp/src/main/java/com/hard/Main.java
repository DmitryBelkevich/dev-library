package com.hard;

public class Main {
    public static void main(String[] args) {
        View view = new View();

        view.onCreate();
        view.onClickButton();
    }
}

/**
 * Model
 */

class Model {
    private String str;

    public Model(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}

/**
 * View
 */

interface IView {
    void setContent();

    void setStr(String str);
}

class View implements IView {
    private IPresenter presenter;

    public View() {
        Model model = new Model("Hello World");
        this.presenter = new Presenter(this, model);
    }

    public void onCreate() {
        presenter.onCreate();
    }

    public void onClickButton() {
        presenter.onClickButton();
    }

    @Override
    public void setContent() {
        System.out.println("set Content");
    }

    @Override
    public void setStr(String str) {
        System.out.println("set String to layout");
    }
}

/**
 * Presenter
 */

interface IPresenter {
    void onCreate();

    void onClickButton();
}

class Presenter implements IPresenter {
    private IView view;
    private Model model;

    public Presenter(IView view, Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onCreate() {
        view.setContent();
    }

    @Override
    public void onClickButton() {
        String str = model.getStr();
        view.setStr(str);
    }
}
