package com.hard.my_chat.server;

import com.hard.my_chat.server.views.FrameView;
import com.hard.my_chat.server.views.View;

import java.util.ArrayList;
import java.util.Collection;

public class Server {
    private Collection<View> views;

    public Server() {
        views = new ArrayList<>();

        views.add(new FrameView());
    }

    public void run() {
        for (View view : views)
            view.run();
    }
}
