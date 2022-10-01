package com.aquilla.ludumdare.screen;

import com.aquilla.ludumdare.LudumDare;
import com.aquilla.ludumdare.assets.Assets;
import com.aquilla.ludumdare.util.Palette;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class GameScreen extends Screen {
    // Map variables
    private OrthogonalTiledMapRenderer renderer;
    public GameScreen(LudumDare game) {
        super(game);
        renderer = new OrthogonalTiledMapRenderer(Assets.get().getTiledMap("maps/testmap.tmx"));
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(SpriteBatch sb, ShapeRenderer sr) {
        Gdx.gl.glClearColor(Palette.DEEP.r, Palette.DEEP.g, Palette.DEEP.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.setProjectionMatrix(getCam().combined);
        renderer.setView(getCam());

        renderer.render();
    }
}
