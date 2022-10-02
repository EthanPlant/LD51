package com.aquilla.ludumdare.camera;

import com.aquilla.ludumdare.LudumDare;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class SmoothCamera extends OrthographicCamera {
    private static final float SMOOTHING = 10F;

    private Vector2 targetPos;
    private Vector2 lastPos;

    public SmoothCamera(float x, float y) {
        super(LudumDare.WIDTH, LudumDare.HEIGHT);
        position.x = x;
        position.y = y;
        lastPos = new Vector2(x, y);
        targetPos = new Vector2(x, y);
    }

    public void setTargetPos(Vector2 targetPos) {
        this.targetPos = targetPos;
    }

    public void update(float delta) {
        position.x = lastPos.x;
        position.y = lastPos.y;

        position.x += (targetPos.x - position.x) * SMOOTHING * delta;
        position.y += (targetPos.y - position.y) * SMOOTHING * delta;

        lastPos.x = position.x;
        lastPos.y = position.y;

        super.update();
    }
}
