package com.aquilla.ludumdare.screen;

import com.aquilla.ludumdare.LudumDare;
import com.aquilla.ludumdare.assets.Assets;
import com.aquilla.ludumdare.util.Shader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;

public class TitleScreen extends Screen implements InputProcessor {

    private Animation<TextureRegion> animation;
    private float frameCount;

    public TitleScreen(LudumDare game) {
        super(game);

        animation = new Animation<TextureRegion>(0.2F, Assets.get().getTextureAtlas("textures/titlescreen.atlas").findRegions("titlescreen"), Animation.PlayMode.LOOP_PINGPONG);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void update(float delta) {
        frameCount += delta;
    }

    @Override
    public void draw(SpriteBatch sb, ShapeRenderer sr) {
        // Reset camera projection matrix
        Matrix4 uiMatrix = getCam().combined.cpy();
        uiMatrix.setToOrtho2D(0, 0, LudumDare.WIDTH, LudumDare.HEIGHT);
        sr.setProjectionMatrix(uiMatrix);
        sb.setProjectionMatrix(uiMatrix);

        sb.begin();
        TextureRegion currentFrame = animation.getKeyFrame(frameCount, true);
        sb.draw(currentFrame, 0, 0, LudumDare.WIDTH, LudumDare.HEIGHT);
        sb.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        transitionToScreen(new GameScreen(game));
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
