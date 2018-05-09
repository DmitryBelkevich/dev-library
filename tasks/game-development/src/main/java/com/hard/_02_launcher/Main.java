package com.hard._02_launcher;

public class Main {
    public static void main(String[] args) {
        DesktopLauncher desktopLauncher = new DesktopLauncher();
        desktopLauncher.main();
    }
}

/**
 * Launcher
 */

class DesktopLauncher {
    public void main() {
        System.out.println("App has launched on Desktop");
    }
}

class AndroidLauncher {
    public void onCreate() {
        System.out.println("App has launched on Android");
    }
}

class HtmlLauncher {
    public void create() {
        System.out.println("App has launched on Html");
    }
}
