package com.aquilla.ludumdare;

import com.aquilla.ludumdare.screen.LoadingScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class LudumDare extends Game {

	public static final String TITLE = "Ludum Dare 51";
	public static final int WIDTH = 256;
	public static final int HEIGHT = 224;
	public static final int SCALE = 3;

	public enum Mode { DESKTOP, HTML }
	public static Mode mode;

	private SpriteBatch batch;
	private ShapeRenderer sr;

	@Override
	public void create() {
		batch = new SpriteBatch();
		sr = new ShapeRenderer();
		sr.setAutoShapeType(true);
		setScreen(new LoadingScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public ShapeRenderer getShapeRenderer() {
		return sr;
	}

	@Override
	public void dispose() {
		batch.dispose();
		sr.dispose();
	}
}
