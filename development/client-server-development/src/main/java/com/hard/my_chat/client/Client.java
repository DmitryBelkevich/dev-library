package com.hard.my_chat.client;

import com.hard.my_chat.client.views.ConsoleView;
import com.hard.my_chat.client.views.FrameView;
import com.hard.my_chat.client.views.View;

import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private Collection<View> views;

    public Client() {
        views = new ArrayList<>();

        views.add(new ConsoleView(this));
        views.add(new FrameView(this));
    }

    public void run() {
        for (View view : views)
            view.run();
    }

    public void notifyAllViews(String str) {
        for (View view : views)
            view.getMessage(str);
    }
}
