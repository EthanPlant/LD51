package com.aquilla.ludumdare.screen;

import com.aquilla.ludumdare.LudumDare;
import com.aquilla.ludumdare.assets.Assets;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameScreen extends Screen{
    public GameScreen(LudumDare game) {
        super(game);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(SpriteBatch sb, ShapeRenderer sr) {
        sb.setProjectionMatrix(getCam().combined);

        sb.begin();
        sb.draw(Assets.get().getTexture("badlogic.jpg"), 0, 0);
        sb.end();
    }
}
