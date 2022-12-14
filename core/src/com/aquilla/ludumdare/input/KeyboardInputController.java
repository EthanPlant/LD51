package com.aquilla.ludumdare.input;

import com.aquilla.ludumdare.level.Level;
import com.aquilla.ludumdare.screen.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class KeyboardInputController extends InputController implements InputProcessor {

    private boolean left;
    private boolean right;
    private boolean jump;

    @Override
    public void update(float delta) {
        left = Gdx.input.isKeyPressed(Input.Keys.A);
        right = Gdx.input.isKeyPressed(Input.Keys.D);
        jump = Gdx.input.isKeyPressed(Input.Keys.SPACE);
    }

    @Override
    public boolean left() {
        return left;
    }

    @Override
    public boolean right() {
        return right;
    }

    @Override
    public boolean jump() {
        return jump;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (enabled) {
            switch (keycode) {
                case Input.Keys.A:
                    left = true;
                    break;
                case Input.Keys.D:
                    right = true;
                    break;
                case Input.Keys.SPACE:
                    jump = true;
                    break;
                default:
                    break;
            }
        }

        return false;
    }
}
