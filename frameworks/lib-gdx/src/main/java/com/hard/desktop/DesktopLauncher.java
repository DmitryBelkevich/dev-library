package com.hard.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hard.Game1;
import com.hard.config.Screen;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Game 1";
		config.width = Screen.WIDTH;
		config.height = Screen.HEIGHT;
		config.samples = 4;

		new LwjglApplication(new Game1(), config);
	}
}
