package com.hard;

public class Main {
    public static void main(String[] args) {
        FrontController frontController = new FrontController();

        frontController.dispatchRequest("Main");
        frontController.dispatchRequest("Page1");
        frontController.dispatchRequest("Page2");
    }
}

/**
 * Views
 */

class MainView {
    public void show() {
        System.out.println("displaying Main Page");
    }
}

class Page1View {
    public void show() {
        System.out.println("displaying Page1");
    }
}

class Page2View {
    public void show() {
        System.out.println("displaying Page2");
    }
}

/**
 * Dispatcher
 */

class Dispatcher {
    private MainView mainView;
    private Page1View page1View;
    private Page2View page2View;

    public Dispatcher() {
        mainView = new MainView();
        page1View = new Page1View();
        page2View = new Page2View();
    }

    public void dispatch(String request) {
        if (request.equalsIgnoreCase("Page1"))
            page1View.show();
        else if (request.equalsIgnoreCase("Page2"))
            page2View.show();
        else
            mainView.show();
    }
}

/**
 * Front Controller
 */

class FrontController {
    private Dispatcher dispatcher;

    public FrontController() {
        dispatcher = new Dispatcher();
    }

    private boolean isAuthorizedUser() {
        System.out.println("User is authorized");
        return true;
    }

    private void trackRequest(String request) {
        System.out.println("Page requested: " + request);
    }

    public void dispatchRequest(String request) {
        trackRequest(request);

        if (isAuthorizedUser())
            dispatcher.dispatch(request);
    }
}
