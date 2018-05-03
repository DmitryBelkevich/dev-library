package com.hard;

public class Main {
    public static void main(String[] args) {

    }
}

/**
 * Before
 */

//class Computer {
//    public void showImage() {
//        System.out.println("show image");
//    }
//
//    public void playMusic() {
//        System.out.println("play music");
//    }
//}

/**
 * After
 */

class Display {
    public void showImage() {
        System.out.println("show image");
    }
}

class Player {
    public void playMusic() {
        System.out.println("play music");
    }
}

class Computer {
    private Display display;
    private Player player;

    public void showImage() {
        display.showImage();
    }

    public void playMusic() {
        player.playMusic();
    }
}
