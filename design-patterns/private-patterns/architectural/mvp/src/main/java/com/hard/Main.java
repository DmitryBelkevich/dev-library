package com.hard;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        view.onCreate();
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
 * View / Activity
 */

interface IView {
    void showHeader();

    void showContent();

    void showFooter();

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

    public void onResume() {
        presenter.onResume();
    }

    public void onDestroy() {
        presenter.onDestroy();
    }

    @Override
    public void showHeader() {
        System.out.println("show Header");
    }

    @Override
    public void showContent() {
        System.out.println("show Content");
    }

    @Override
    public void showFooter() {
        System.out.println("show Footer");
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

    void onResume();

    void onDestroy();
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
        view.showHeader();
        view.showContent();
        view.showFooter();

        String str = model.getStr();
        view.setStr(str);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }
}
