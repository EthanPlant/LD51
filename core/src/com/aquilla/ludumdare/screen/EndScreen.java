package com.aquilla.ludumdare.screen;

import com.aquilla.ludumdare.LudumDare;
import com.aquilla.ludumdare.assets.Assets;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;

public class EndScreen extends Screen {
    public EndScreen(LudumDare game) {
        super(game);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(SpriteBatch sb, ShapeRenderer sr) {
        // Reset camera projection matrix
        Matrix4 uiMatrix = getCam().combined.cpy();
        uiMatrix.setToOrtho2D(0, 0, LudumDare.WIDTH, LudumDare.HEIGHT);
        sr.setProjectionMatrix(uiMatrix);
        sb.setProjectionMatrix(uiMatrix);

        sb.begin();
        sb.draw(Assets.get().getTexture("textures/endscreen.png"), 0, 0, LudumDare.WIDTH, LudumDare.HEIGHT);
        sb.end();
    }
}
