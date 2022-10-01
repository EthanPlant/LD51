package com.aquilla.ludumdare.screen;

import com.aquilla.ludumdare.LudumDare;
import com.aquilla.ludumdare.util.Palette;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class Screen implements com.badlogic.gdx.Screen {
    protected LudumDare game;
    private Viewport viewport;
    private OrthographicCamera cam;

    abstract public void update(float delta);
    abstract public void draw(SpriteBatch sb, ShapeRenderer sr);

    public Screen(LudumDare game) {
        this.game = game;

        cam = new OrthographicCamera(LudumDare.WIDTH, LudumDare.HEIGHT);
        viewport = new FitViewport(LudumDare.WIDTH, LudumDare.HEIGHT, cam);
        cam.position.set(LudumDare.WIDTH / 2, LudumDare.HEIGHT / 2, 0);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(Palette.INK.r, Palette.INK.g, Palette.INK.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        draw(game.getBatch(), game.getShapeRenderer());
    }

    public void transitionToScreen(Screen next) {
        game.setScreen(next);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public LudumDare getGame() {
        return game;
    }

    public OrthographicCamera getCam() {
        return cam;
    }

    public Viewport getViewport() {
        return viewport;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
