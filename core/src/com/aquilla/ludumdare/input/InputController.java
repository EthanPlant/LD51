package com.aquilla.ludumdare.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public abstract class InputController implements InputProcessor {
    protected boolean enabled;

    abstract public void update(float delta);
    abstract public boolean left();
    abstract public boolean right();
    abstract public boolean jump();

    public void enable() {
        enabled = true;
        Gdx.input.setInputProcessor(this);
    }

    public void disable() {
        enabled = false;
    }

    @Override
    public boolean keyDown(int keycode) {
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
