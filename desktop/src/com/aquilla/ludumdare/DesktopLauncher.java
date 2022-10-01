package com.aquilla.ludumdare;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import static com.aquilla.ludumdare.LudumDare.*;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		config.setTitle(TITLE);
		config.setWindowedMode(WIDTH * SCALE, HEIGHT * SCALE);
		config.setResizable(false);

		LudumDare.mode = Mode.DESKTOP;

		new Lwjgl3Application(new LudumDare(), config);
	}
}
