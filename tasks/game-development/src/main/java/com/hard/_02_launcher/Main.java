package com.hard._02_launcher;

public class Main {
    public static void main(String[] args) {
        Launcher launcher = new DesktopLauncher();
        launcher.run();
    }
}

/**
 * Launcher
 */

interface Launcher {
    void run();
}

class DesktopLauncher implements Launcher {
    @Override
    public void run() {
        System.out.println("App has launched on Desktop");
    }
}

class AndroidLauncher implements Launcher {
    @Override
    public void run() {
        System.out.println("App has launched on Android");
    }
}

class HtmlLauncher implements Launcher {
    @Override
    public void run() {
        System.out.println("App has launched on Html");
    }
}
