package com.aquilla.ludumdare.screen;

import com.aquilla.ludumdare.LudumDare;
import com.aquilla.ludumdare.camera.SmoothCamera;
import com.aquilla.ludumdare.util.Palette;
import com.aquilla.ludumdare.util.Shader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.HdpiUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class Screen implements com.badlogic.gdx.Screen {
    protected LudumDare game;
    private Viewport viewport;
    private SmoothCamera cam;
    private OrthographicCamera viewportCam;
    private FrameBuffer fbo;

    abstract public void update(float delta);
    abstract public void draw(SpriteBatch sb, ShapeRenderer sr);

    public Screen(LudumDare game) {
        this.game = game;

        fbo = new FrameBuffer(Pixmap.Format.RGBA8888, LudumDare.WIDTH, LudumDare.HEIGHT, false);
        fbo.getColorBufferTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        cam = new SmoothCamera(LudumDare.WIDTH / 2, LudumDare.HEIGHT / 2);

        viewportCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewportCam.position.set(0.5f * viewportCam.viewportWidth, 0.5f * viewportCam.viewportHeight, 0);
        viewportCam.update();

        viewport = new FitViewport(LudumDare.WIDTH, LudumDare.HEIGHT, viewportCam);
        viewport.apply();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        fbo.begin();
        Gdx.gl.glClearColor(Palette.INK.r, Palette.INK.g, Palette.INK.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);


        draw(game.getBatch(), game.getShapeRenderer());
        game.getBatch().setShader(null);

        fbo.end();

        game.getBatch().begin();
        game.getBatch().setProjectionMatrix(viewportCam.combined);
        game.getBatch().setShader(Shader.SCANLINE);
        game.getBatch().draw(fbo.getColorBufferTexture(), (LudumDare.SCALE * LudumDare.WIDTH - LudumDare.WIDTH) / 2, (LudumDare.SCALE * LudumDare.HEIGHT - LudumDare.HEIGHT) / 2, LudumDare.WIDTH, LudumDare.HEIGHT, 0, 0, 1f, 1f);
        game.getBatch().end();
        game.getBatch().setShader(null);

        HdpiUtils.glScissor(0, 0, LudumDare.WIDTH * LudumDare.SCALE, LudumDare.HEIGHT * LudumDare.SCALE);
    }

    public void transitionToScreen(Screen next) {
        game.setScreen(next);
    }

    @Override
    public void resize(int width, int height) {
        Shader.SCANLINE.bind();
        Shader.SCANLINE.setUniformf("u_screenHeight", height);
        viewport.update(width, height);
    }

    public LudumDare getGame() {
        return game;
    }

    public SmoothCamera getCam() {
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
